public class IntNode extends ASTNode{
    public IntNode(Token token){
        super(token);
    }
    public int getValue(){
        return super.getToken().getValue();
    }
}
