public class BinOpNode extends ASTNode {
    private ASTNode leftChild;
    private ASTNode rightChild;

    public BinOpNode(ASTNode leftChild, Token token, ASTNode rightChild){
        super(token);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public ASTNode getLeftChild() { return leftChild; }
    public ASTNode getRightChild() { return rightChild; }
}
