import java.util.LinkedList;
import java.util.Queue;

public class Parser {
    private Lexer lexer;
    private Token currentToken;
    private ASTNode root;

    public Parser(Lexer lexer){
        assert(lexer != null);
        this.lexer = lexer;
        currentToken = lexer.getNextToken();
        root = null;
    }

    void eatToken(Token.type tokenType){
        assert(currentToken.getType() == tokenType);
        currentToken = lexer.getNextToken();
    }



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

    // Returns the root node of an Abstract Syntax Tree (AST)
    ASTNode parse(){
        root = expr();
        return root;
    }

    // Prints out the Abstract Syntax Tree (AST) using a Breadth-First Search -- * For debugging purposes *
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
