/**
 * An AST node that contains only the value of the operand. Note that an
 * integer node cannot have any children.
 */
public class IntNode extends ASTNode{

    /**
     * @param token An integer token
     */
    public IntNode(Token token){
        super(token);
    }

    /**
     * @return the value of the token
     */
    public int getValue(){
        return super.getToken().getValue();
    }
}
