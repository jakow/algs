import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Queue;

class RedBlackTree<K extends Comparable<K>, V> implements OrderedMap<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        return x == null ? 0 : x.count;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public V get(K key) {
        int cmp;
        Node n = root;
        while (n != null) {
            cmp = key.compareTo(n.key);
            if (cmp < 0) n = n.left;
            else if (cmp > 0) n = n.right;
            else return n.value;
        }
        return null;
    }

    @Override
    public K min() {
        Node n = root;
        if (n == null) return null;
        while (n.left != null) {
            n = n.left;
        }
        return n.key;
    }

    @Override
    public K max() {
        Node n = root;
        if (n == null) return null;
        while (n.right != null) {
            n = n.right;
        }
        return n.key;
    }

    @Override
    public Collection<K> keys() {
        Queue<K> q = new ArrayDeque<>();
        inOrderTraversal(root, (node) -> q.add(node.key));
        return q;
    }

    @Override
    public Collection<V> values() {
        Queue<V> q = new ArrayDeque<>();
        inOrderTraversal(root, (node) -> q.add(node.value));
        return q;
    }

    @Override
    public Collection<Entry<K, V>> entries() {
        Queue<Entry<K, V>> q = new ArrayDeque<>();
        inOrderTraversal(root, (node) -> q.add(new TreeEntry(node.key, node.value)));
        return q;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);

        System.out.println("Root color: " + (root.color ? "red" : "black"));
    }


    public void deleteMin() {
        throw new NotImplementedException();
    }


    private Node deleteMin(Node node) {
        throw new NotImplementedException();
    }


    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Tree underflow");
        root = deleteMax(root);
    }

    private Node deleteMax(Node node) {
        // borrow red node from left to right, temporarily destroying the left leaning invariant
        if (isRed(node.left)) {
            node = rotateRight(node);
        }
        // reached max node, delete it
        if (node.right == null) {
            return null;
        }
        // borrow red from siblings
        if (!isRed(node.right) && !isRed(node.right.left)) {
            node = moveRedRight(node);
        }

        // move down the tree to delete max
        node.right = deleteMax(node.right);
        // restore left leaning property
        return balance(node);

    }

    @Override
    public V delete(K key) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        root = null;
    }

    @Override
    public K floor(K key) {
        Node n = floor(root, key);
        if (n == null) return null;
        else return n.key;
    }

    private Node floor(Node n, K key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp == 0) return n;
        // given key smaller, floor is on the left
        if (cmp < 0) return floor(n.left, key);
        else {
            Node x = floor(n.right, key);
            return x == null ? n : x;
        }
    }

    @Override
    public K ceil(K key) {
        Node n = ceil(root, key);
        if (n == null) return null;
        else return n.key;
    }

    private Node ceil(Node n, K key) {
        if (n == null) return null;
        int cmp = key.compareTo(n.key);
        if (cmp == 0) return n;
            // need to go right to find a larger key
        else if (cmp > 0) return ceil(n.right, key);
        else {
            Node x = ceil(n.left, key);
            return x != null ? x : n;
        }
    }

    private Node put(Node n, K key , V value) {
        // First, standard bst insert...
        if (n == null) return new Node(key, value);
        int cmp = key.compareTo(n.key);
        if (cmp < 0) n.left = put(n.left, key, value);
        else if (cmp > 0) n.right = put(n.right, key, value);
        else n.value = value;
        // ...then, adjust R-B colors
        n = balance(n);
        return n;
    }

    /* HELPER METHODS */

    private boolean isRed(Node n) {
        return n != null && n.color == RED;
    }

    private Node rotateLeft(Node h) {
        assert isRed(h.right);
        Node x = h.right;
        System.out.println("Rotate left: " +  h.key + ',' + x.key);
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        // adjust sizes - order important!
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
        assert isRed(h.left);
        Node x = h.left;
        System.out.println("Rotate right: " +  h.key + ',' + x.key);
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.count = h.count;
        h.count = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
        System.out.println("Flip colors");
        assert (isRed(h) && !isRed(h.left) && !isRed(h.right)) || (!isRed(h) && isRed(h.left) && isRed(h.right));
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    private Node balance(Node h) {
        // assert (h != null);
        if (isRed(h.right)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);

        h.count = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedRight(Node h) {
        flipColors(h);
        System.out.println("Left is red:" + isRed(h.left) + " Right is red: " + isRed(h.right));
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            System.out.println("Left is red:" + isRed(h.left) + " Right is red: " + isRed(h.right));
            flipColors(h);
        }
        return h;
    }

    private void inOrderTraversal(Node n, Traverser<Node> t) {
        if (n == null) return;
        inOrderTraversal(n.left, t);
        t.traverse(n);
        inOrderTraversal(n.right, t);
    }

    /* Integrity verification methods */

    public boolean is23() {
        return is23(root);
    }

    private boolean is23(Node n) {
        if (n == null) return true;
        boolean notRightLeaning = !isRed(n.right);
        boolean noTwoRedLinks = !(n.color == RED && isRed(n.left));
        return notRightLeaning && noTwoRedLinks && is23(n.left) && is23(n.right);
    }

    public boolean isBalanced() {
        // find min node to count its number of black links
        if (isEmpty()) return true;
        Node n = root;
        int blackCount = 0;
        while(n.left != null) {
            if (!isRed(n.left)) blackCount++;
            n = n.left;
        }
        return isBalanced(root, blackCount);
    }

    private boolean isBalanced(Node n, int blackCount) {
        // reached null so parent is a leaf and it called isBalanced
        if (n == null) return blackCount == -1;
        boolean leftBalanced =  isBalanced(n.left,  isRed(n.left)  ? blackCount : blackCount - 1);
        boolean rightBalanced = isBalanced(n.right, isRed(n.right) ? blackCount : blackCount - 1);
        return leftBalanced && rightBalanced;
    }

    public boolean isBST() {
        return isBST(root);
    }

    private boolean isBST(Node n) {
        if (n == null) return true;
        boolean is = true;
        if (n.left != null) is = n.key.compareTo(n.left.key) > 0;
        if (n.right != null) is = is && n.key.compareTo(n.right.key) < 0;
        return is;
    }

    public boolean isRedBlackTree() {
        return isBST() && isBalanced() && is23();
    }





    private class Node {
        Node left, right;
        K key;
        V value;
        boolean color;
        int count;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            count = 1;
            color = RED;
        }
    }

    private class TreeEntry implements Entry<K, V> {
        private final K key;
        private final V value;

        TreeEntry (K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }
}