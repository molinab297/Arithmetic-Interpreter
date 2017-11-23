public class Interpreter {

    private Parser parser;
    private ASTNode root;

    public Interpreter(Parser parser){
        assert(parser != null);
        this.parser = parser;
        root = null;
    }

    int interpret(){
        root = parser.parse();
        return visit(root);
    }

    int visit(ASTNode node){
        if(node instanceof BinOpNode){
            return visitBinOpNode((BinOpNode)node);
        }
        else if(node instanceof IntNode){
            return visitIntNode((IntNode)node);
        }
        return 0;
    }

    int visitBinOpNode(BinOpNode node){
        Token.type type = node.getToken().getType();
       switch(type){
           case ADD: return visit(node.getLeftChild()) + visit(node.getRightChild());
           case SUBTRACT: return visit(node.getLeftChild()) - visit(node.getRightChild());
           case MULTIPLY: return visit(node.getLeftChild()) * visit(node.getRightChild());
           case DIVIDE: return visit(node.getLeftChild()) / visit(node.getRightChild());
           default:
               System.out.println("Interpreter: Something very strange happened...");
               System.exit(1);
       }
       return 0;
    }

    int visitIntNode(IntNode node){
        return node.getValue();
    }
}
