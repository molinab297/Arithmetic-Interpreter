/**
 * Represents a single token entity for {@link Parser} to parse.
 */
public class Token {

    /**
     * The type of the token
     */
    private type tokenType;

    /**
     * The value of the token
     */
    private int value;

    /**
     * An arithmetic expression can contain any of these token types
     */
    public static enum type{INTEGER, ADD, SUBTRACT, MULTIPLY, DIVIDE, LPAREN, RPAREN, EOF};


    /**
     * Initializes the token
     *
     * @param tokenType the type of token
     * @param value the integer value of the token
     */
    public Token(type tokenType, int value){
        this.tokenType = tokenType;
        this.value = value;
    }


    /**
     * Returns the integer value of the token
     */
    public int getValue(){ return value; }

    /**
     * Returns the token type
     */
    public type getType(){ return tokenType; }
}
