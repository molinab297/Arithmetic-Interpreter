public class Main {
    public static void main(String[]args){
        Lexer lexer = new Lexer("1+2+3*6+(2*10)");
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        System.out.println(interpreter.interpret());
    }
}
