public class Lexer {
    private String inputText;
    private int pos;
    private char currentChar;

    public Lexer(String inputText){
        assert(!inputText.isEmpty());
        this.inputText = inputText;
        pos = 0;
        currentChar = inputText.charAt(pos);
    }

    // Moves the character pointer to the right of the input string by 1
    void advance(){
        pos++;
        if(pos > inputText.length()-1){
            currentChar = '\0';
        } else{
            currentChar = inputText.charAt(pos);
        }
    }

    void consumeWhitespace(){
        while(currentChar != '\0' && currentChar == ' '){
            advance();
        }
    }

    int consumeInteger(){
        String result = "";
        while(currentChar != '\0' && Character.isDigit(currentChar)){
            result += currentChar;
            advance();
        }
        return Integer.parseInt(result);
    }

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
