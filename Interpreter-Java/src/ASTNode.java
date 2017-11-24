/**
 * Represents a single node in an Abstract Syntax Tree (AST)
 */
public class ASTNode {

    /**
     * The value of the node, either an integer or a binary operator.
     */
    private Token token;


    /**
     * An AST node contains either an integer or a binary operator value, and may
     * contain a left and right child.
     *
     * @param token
     *      The value of the node
     */
    public ASTNode(Token token){ this.token = token; }


    /**
     * @return The node's token
     */
    public Token getToken() { return token; }

}
