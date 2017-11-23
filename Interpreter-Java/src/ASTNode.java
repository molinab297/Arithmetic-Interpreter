public class ASTNode {

    private Token token;

    public ASTNode(Token token){
        this.token = token;
    }

    public void setToken(Token t){ this.token = t; }

    public Token getToken() { return token; }

}
