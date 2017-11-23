/**
 * An AST node that has a left and right child, both of which
 * are operands i.e.:     ( + )
 *                       /     \
 *                     (1)     (2)    = 3
 *
 * The value of this node is a binary operator
 */
public class BinOpNode extends ASTNode {

    /**
     * The left operand
     */
    private ASTNode leftChild;

    /**
     * The right operand
     */
    private ASTNode rightChild;


    /**
     * A binary operator node has exactly 2 children (two operands to perform an operation on),
     * and the value of the operator itself.
     *
     * @param leftChild A reference to the left operand
     * @param token A token specifying the of binary operator
     * @param rightChild A reference to the right operand
     */
    public BinOpNode(ASTNode leftChild, Token token, ASTNode rightChild){
        super(token);
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    /**
     * @return a reference to the left operand
     */
    public ASTNode getLeftChild() { return leftChild; }

    /**
     * @return a reference to the right operand
     */
    public ASTNode getRightChild() { return rightChild; }
}
