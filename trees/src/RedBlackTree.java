import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayDeque;
import java.util.Collection;
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
        if (isRed(n.right) && !isRed(n.left)) n = rotateLeft(n);
        if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
        if (isRed(n.left) && isRed(n.right)) flipColors(n);
        n.count = 1 + size(n.left) + size(n.right);
        return n;
    }

    private boolean isRed(Node n) {
        return n != null && n != root && n.color == RED;
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
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private void inOrderTraversal(Node n, Traverser<Node> t) {
        if (n == null) return;
        inOrderTraversal(n.left, t);
        t.traverse(n);
        inOrderTraversal(n.right, t);
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