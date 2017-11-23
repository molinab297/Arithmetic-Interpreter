public class Token {
    private type tokenType;
    private int value;
    public static enum type{INTEGER, ADD, SUBTRACT, MULTIPLY, DIVIDE, LPAREN, RPAREN, EOF};

    public Token(type tokenType, int value){
        this.tokenType = tokenType;
        this.value = value;
    }

    public int getValue(){ return value; }
    public type getType(){ return tokenType; }
}
