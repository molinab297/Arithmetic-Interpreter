/* The arithmetic expression interpreter conforms to the following grammar:
   expr: term((ADD | SUBTRACT)term)*
   term: factor((MULTIPLY | DIVIDE)factor)*
   factor: INTEGER | LPAREN expr RPAREN
*/

public class Main {
    public static void main(String[]args){
        System.out.println(new Interpreter(new Parser(new Lexer("1+2+3*6+(2*10)"))).interpret()); // One liner because why not?
    }
}
