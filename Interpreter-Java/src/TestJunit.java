import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Class for running unit tests on the Interpreter
 */
public class TestJunit {

    @Test
    public void testExpr1() {
        int result = new Interpreter(new Parser(new Lexer("10 +  (5-3)"))).interpret();
        assertEquals(12,result);
    }

    @Test
    public void testExpr2() {
        int result = new Interpreter(new Parser(new Lexer("7*7/7*7"))).interpret();
        assertEquals(49,result);
    }

    @Test
    public void testExpr3() {
        int result = new Interpreter(new Parser(new Lexer("(1+2+3+4+5)*10/3"))).interpret();
        assertEquals(50,result);
    }

    @Test
    public void testExpr4() {
        int result = new Interpreter(new Parser(new Lexer("5*4*3*2+1"))).interpret();
        assertEquals(121,result);
    }


    @Test
    public void testExpr5() {
        int result = new Interpreter(new Parser(new Lexer("1-1+1*100"))).interpret();
        assertEquals(100,result);
    }


    @Test
    public void testExpr6() {
        int result = new Interpreter(new Parser(new Lexer("0+0-100+80*2"))).interpret();
        assertEquals(60,result);
    }

    @Test
    public void testExpr() {
        int result = new Interpreter(new Parser(new Lexer("7+3*(10/(64/(1+(2+5))))"))).interpret();
        assertEquals(10,result);
    }

    @Test
    public void testExpr8() {
        int result = new Interpreter(new Parser(new Lexer("77/11*(11/(20/(1+2*70-86+(4+3))-11)) + 55-40"))).interpret();
        assertEquals(8,result);
    }
}
