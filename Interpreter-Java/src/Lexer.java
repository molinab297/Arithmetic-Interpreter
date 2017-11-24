/**
 * Responsible for creating tokens from the input string, so that {@link Parser}
 * can process the text and then have {@link Interpreter} interpret and produce
 * the correct output.
 */
public class Lexer {

    /**
     * The arithmetic expression
     */
    private String inputText;

    /**
     * The current index of the input text that's being processed
     */
    private int pos;

    /**
     * The current character of the input text that's being processed
     */
    private char currentChar;


    /**
     * Initialize the lexer to start at the first index of the input text
     *
     * @param inputText the arithmetic expression
     */
    public Lexer(String inputText){
        assert(!inputText.isEmpty());
        this.inputText = inputText;
        pos = 0;
        currentChar = inputText.charAt(pos);
    }


    /**
     * Moves the character pointer to the right of the input string by 1
     */
    void advance(){
        pos++;
        if(pos > inputText.length()-1){
            currentChar = '\0';
        } else{
            currentChar = inputText.charAt(pos);
        }
    }

    /**
     * Skips over whitespace characters starting from the current character
     * up until a non whitespace character is encountered.
     */
    void consumeWhitespace(){
        while(currentChar != '\0' && currentChar == ' '){
            advance();
        }
    }

    /**
     * Converts a single digit (possibly a multiple digit) integer substring
     * to an actual int.
     *
     * @return the integer result of the integer substring
     */
    int consumeInteger(){
        String result = "";
        while(currentChar != '\0' && Character.isDigit(currentChar)){
            result += currentChar;
            advance();
        }
        return Integer.parseInt(result);
    }

    /**
     * Generates the next token to be processed
     *
     * @return the next token
     */
    Token getNextToken(){
        while(currentChar != '\0'){
            try{
                if(currentChar == ' '){
                    consumeWhitespace();
                }
                if(Character.isDigit(currentChar)){
                    return new Token(Token.type.INTEGER, consumeInteger());
                }
                if(currentChar == '*'){
                    advance();
                    return new Token(Token.type.MULTIPLY, '*');
                }
                if(currentChar == '/'){
                    advance();
                    return new Token(Token.type.DIVIDE, '/');
                }
                if(currentChar == '+'){
                    advance();
                    return new Token(Token.type.ADD, '+');
                }
                if(currentChar == '-'){
                    advance();
                    return new Token(Token.type.SUBTRACT, '-');
                }
                if(currentChar == '('){
                    advance();
                    return new Token(Token.type.LPAREN, '(');
                }
                if(currentChar == ')'){
                    advance();
                    return new Token(Token.type.RPAREN, ')');
                }

                throw new InvalidChar("Lexer: Found an invalid character");

            } catch(Exception e){
                System.out.println(e.getMessage());
                System.exit(1);
            }
        }
        return new Token(Token.type.EOF, '\0');
    }

    public class InvalidChar extends Exception {
        public InvalidChar(String message) {
            super(message);
        }
    }

}
