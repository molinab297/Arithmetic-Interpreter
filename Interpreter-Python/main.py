import unittest
from interpreter import Interpreter, Lexer, Parser


# Unit tests for the arithmetic expression interpreter
class InterpreterTests(unittest.TestCase):
    def testExpr1(self):
        interpreter = Interpreter(Parser(Lexer("2+4-6")))
        self.assertEqual(interpreter.interpret(), 0)

    def testExpr2(self):
        interpreter = Interpreter(Parser(Lexer("8*10/8+1")))
        self.assertEqual(interpreter.interpret(), 11)

    def testExpr3(self):
        interpreter = Interpreter(Parser(Lexer("8/2+40")))
        self.assertEqual(interpreter.interpret(), 44)

    def testExpr4(self):
        interpreter = Interpreter(Parser(Lexer("1+2+3+4+5/5")))
        self.assertEqual(interpreter.interpret(), 11)

    def testExpr5(self):
        interpreter = Interpreter(Parser(Lexer("1+2*3+4*5")))
        self.assertEqual(interpreter.interpret(), 27)

    def testExpr6(self):
        interpreter = Interpreter(Parser(Lexer("100/10*10/10*10+1")))
        self.assertEqual(interpreter.interpret(), 101)

    def testExpr7(self):
        interpreter = Interpreter(Parser(Lexer("100-100+100/10*8")))
        self.assertEqual(interpreter.interpret(), 80)

    def testExpr8(self):
        interpreter = Interpreter(Parser(Lexer("(2)")))
        self.assertEqual(interpreter.interpret(), 2)

    def testExpr9(self):
        interpreter = Interpreter(Parser(Lexer("(2-7)+6*3")))
        self.assertEqual(interpreter.interpret(), 13)

    def testExpr10(self):
        interpreter = Interpreter(Parser(Lexer("(2+3)")))
        self.assertEqual(interpreter.interpret(), 5)

    def testExpr11(self):
        interpreter = Interpreter(Parser(Lexer("(2*2)+1")))
        self.assertEqual(interpreter.interpret(), 5)

    def testExpr12(self):
        interpreter = Interpreter(Parser(Lexer("7+3*(10/(12/(3+1)-1))")))
        self.assertEqual(interpreter.interpret(), 22)


def main():
    unittest.main()


if __name__ == '__main__':
    main()
