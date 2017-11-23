INTEGER, ADD, SUBTRACT, MULTIPLY, DIVIDE, LPAREN, RPAREN, EOF = (
    'INTEGER', 'PLUS', 'MINUS', 'MULTIPLY', 'DIVIDE', '(', ')', 'EOF'
)


class Token:
    def __init__(self, type, value):
        self.type = type
        self.value = value

    '''
    Performs the tokenization of an arithmetic expression

        :param input_text: a string containing the expression
        :return: a tokenized string
    '''


class Lexer:
    def __init__(self, input_text):
        self.input_text = input_text
        self.pos = 0
        self.current_char = self.input_text[self.pos]

    def error(self):
        raise Exception('Error parsing input')

    # Advances the 'pos' pointer and 'current_char' variables
    def advance(self):
        self.pos += 1
        if self.pos > len(self.input_text) - 1:
            self.current_char = None
        else:
            self.current_char = self.input_text[self.pos]

    def consume_whitespace(self):
        while self.current_char is not None and self.current_char.isspace():
            self.advance()

    # Consumes multi-digit numbers
    def consume_integer(self):
        result = ''
        while self.current_char is not None and self.current_char.isdigit():
            result += self.current_char
            self.advance()
        return int(result)

    # Tokenize the current character from the input string
    def get_next_token(self):

        while self.current_char is not None:

            if self.current_char.isspace():
                self.consume_whitespace()

            if self.current_char.isdigit():
                return Token(INTEGER, self.consume_integer())

            if self.current_char == '*':
                self.advance()
                return Token(MULTIPLY, '*')

            if self.current_char == '/':
                self.advance()
                return Token(DIVIDE, '/')

            if self.current_char == '+':
                self.advance()
                return Token(ADD, '+')

            if self.current_char == '-':
                self.advance()
                return Token(SUBTRACT, '-')

            if self.current_char == '(':
                self.advance()
                return Token(LPAREN, '(')

            if self.current_char == ')':
                self.advance()
                return Token(RPAREN, ')')

            self.error()

        return Token(EOF, None)

    '''
    Builds the Abstract Syntax Tree (AST) for the Interpreter

        :param input_text: a string containing the expression
        :return: a tokenized string
    '''


class Parser:
    def __init__(self, lexer):
        self.lexer = lexer
        # set current token to the first token taken from the input
        self.current_token = self.lexer.get_next_token()

    def error(self):
        raise Exception('Invalid syntax')

    # Verify that the current token matches token_type
    def eat(self, token_type):
        if self.current_token.type == token_type:
            self.current_token = self.lexer.get_next_token()
        else:
            self.error()

    def factor(self):
        token = self.current_token
        if token.type == INTEGER:
            self.eat(INTEGER)
            return IntNode(token)
        elif token.type == LPAREN:
            self.eat(LPAREN)
            node = self.expr()
            self.eat(RPAREN)
            return node

    def term(self):
        result = self.factor()
        while self.current_token.type in (MULTIPLY, DIVIDE):
            token = self.current_token
            if token.type == MULTIPLY:
                self.eat(MULTIPLY)
            elif token.type == DIVIDE:
                self.eat(DIVIDE)

            result = BinOpNode(left=result, operator=token, right=self.factor())

        return result

    # Parses and interprets tokens from the lexer
    def expr(self):
        result = self.term()

        while self.current_token.type in (ADD, SUBTRACT):
            token = self.current_token
            if token.type == ADD:
                self.eat(ADD)
            elif token.type == SUBTRACT:
                self.eat(SUBTRACT)

            result = BinOpNode(left=result, operator=token, right=self.term())

        return result

    def parse(self):
        return self.expr()


# Base class for an AST node
class AST:
    pass


# An AST node that contains a binary operator (i.e.: +, -, *, /) token
class BinOpNode(AST):
    def __init__(self, left, operator, right):
        self.left = left
        self.token = self.operator = operator
        self.right = right


# An AST node that contains an integer token
class IntNode(AST):
    def __init__(self, token):
        self.token = token
        self.value = token.value

    '''
    Dispatches calls to the appropriate method based on the node type passed to it. 

        :param node: an AST node
        :return: the result of the corresponding AST node method
    '''


class NodeVisitor:
    def visit(self, node):
        method_name = 'visit_' + type(node).__name__
        visitor_method = getattr(self, method_name, self.visit_error)
        return visitor_method(node)

    def visit_error(self, node):
        raise Exception('No visit_{} method'.format(type(node).__name__))

    '''
    Given a Parser, this class walks and interprets the Abstract Syntax Tree (AST) and evaluates the expression

        :param parser: A parser object containing the AST
        :return: The result of an arithmetic expression
    '''


class Interpreter(NodeVisitor):
    def __init__(self, parser):
        self.parser = parser

    def visit_BinOpNode(self, node):
        if node.operator.type == ADD:
            return self.visit(node.left) + self.visit(node.right)
        elif node.operator.type == SUBTRACT:
            return self.visit(node.left) - self.visit(node.right)
        elif node.operator.type == MULTIPLY:
            return self.visit(node.left) * self.visit(node.right)
        elif node.operator.type == DIVIDE:
            return self.visit(node.left) / self.visit(node.right)

    def visit_IntNode(self, node):
        return node.value

    def interpret(self):
        ast = self.parser.parse()
        return self.visit(ast)