public class Tree extends Node {
    Node[] children;

    Tree(Node left, Node right) {
        children = new Node[] { left, right };
        frequency = left.frequency + right.frequency;
    }

    @Override
    public String toString() {
        return String.format("Tree(%f) { %s, %s }", frequency, children[0].toString(), children[1].toString());
    }
}
