import java.util.LinkedList;
import java.util.Queue;


/**
 * Responsible for analyzing tokens from {@link Lexer}
 */
public class Parser {

    /**
     * Responsible for feeding tokens to be processed by this class
     */
    private Lexer lexer;

    /**
     * The current token being processed
     */
    private Token currentToken;

    /**
     * The start node of an Abstract Syntax Tree
     */
    private ASTNode root;

    /**
     * Get the first token from the lexer
     *
     * @param lexer The lexer used to generate the tokens
     */
    public Parser(Lexer lexer){
        assert(lexer != null);
        this.lexer = lexer;
        currentToken = lexer.getNextToken();
        root = null;
    }

    /**
     * Verifies that the current token matches the expected type
     *
     * @param tokenType The type of token we expect the current token to be
     */
    void eatToken(Token.type tokenType){
        assert(currentToken.getType() == tokenType);
        currentToken = lexer.getNextToken();
    }



    /**
     * Returns either an integer token or the result of an expression between a left
     * and right parenthesis
     *
     * @return The integer result
     */
    ASTNode factor(){
        ASTNode result = null;
        Token token = currentToken;
        if(token.getType() == Token.type.INTEGER){
            eatToken(Token.type.INTEGER);
            result = new IntNode(token);
        }
        else if(token.getType() == Token.type.LPAREN){
            eatToken(Token.type.LPAREN);
            result = expr();
            eatToken(Token.type.RPAREN);
        }
        return result;
    }

    /**
     * Returns the result of an inner arithmetic expression
     *
     * @return The integer result
     */
    ASTNode term(){
        ASTNode result = factor();
        while(currentToken.getType() == Token.type.MULTIPLY || currentToken.getType() == Token.type.DIVIDE){
            Token token = currentToken;
            if(token.getType() == Token.type.MULTIPLY){
                eatToken(Token.type.MULTIPLY);
            }
            else if(token.getType() == Token.type.DIVIDE){
                eatToken(Token.type.DIVIDE);
            }
            result = new BinOpNode(result, token, factor());
        }
        return result;
    }

    /**
     * Returns the result of an arithmetic expression
     *
     * @return The integer result
     */
    ASTNode expr(){
        ASTNode result = term();
        while(currentToken.getType() == Token.type.ADD || currentToken.getType() == Token.type.SUBTRACT){
            Token token = currentToken;
            if(token.getType() == Token.type.ADD){
                eatToken(Token.type.ADD);
            }
            else if(token.getType() == Token.type.SUBTRACT) {
                eatToken(Token.type.SUBTRACT);
            }
            result = new BinOpNode(result, token, term());
        }
        return result;
    }


    /**
     * Returns the root node of an Abstract Syntax Tree (AST)
     *
     * @return The start node
     */
    ASTNode parse(){
        root = expr();
        return root;
    }

    /**
     * Prints out the Abstract Syntax Tree (AST) using a Breadth-First Search, for debugging purposes.
     */
    void printAST(){
        BinOpNode curr = (BinOpNode)root;
        Queue<ASTNode> queue = new LinkedList<>();
        queue.add(curr);
        while(!queue.isEmpty()){
            ASTNode top = queue.remove();
            System.out.println(top.getToken().getType());
            if(top instanceof BinOpNode){
                if(((BinOpNode) top).getLeftChild() != null){
                    queue.add(((BinOpNode) top).getLeftChild());
                }
                if(((BinOpNode) top).getRightChild() != null){
                    queue.add(((BinOpNode) top).getRightChild());
                }
            }
        }
    }

}
