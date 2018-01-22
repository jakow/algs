/**
 * Created by jakub on 20/01/2018.
 */
public class TreeNode<T> {
    T val;
    TreeNode<T> left;
    TreeNode<T> right;


    TreeNode(T val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return String.format("%s -> (%s, %s)",
                val.toString(),
                left == null ? "null" : left.toString(),
                right == null ? "null" : right.toString()
        );
    }
}
