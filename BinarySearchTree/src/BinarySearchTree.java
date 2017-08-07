import jdk.nashorn.internal.runtime.arrays.ArrayData;

import java.util.*;

/**
 * Created by jakub on 17/03/2017.
 */

public class BinarySearchTree<K extends Comparable<K>, V> {
    private Node root;
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node node, K key, V value) {
        if (node == null) return new Node(key, value);
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = put(node.left, key, value);
        else if (cmp > 0) node.right = put(node.right, key, value);
        else node.value = value;
        node.count =  1 + size(node.left) + size(node.right);
        return node;
    }

    public V get(K key) {
        Node x = root;
        int cmp;
        while (x != null) {
            cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    public V min() {
        Node x = root;
        while (x != null && x.left != null) {
            x = x.left;
        }
        if (x == null) return null;
        else return x.value;
    }

    public V max() {
        Node x = root;
        while (x != null && x.right != null) {
            x = x.right;
        }
        if (x == null) return null;
        else return x.value;
    }



    // floor - largest node smaller than key
    public V floor(K key) {
        Node floorNode = floor(root, key);
        if (floorNode == null) return null;
        else return floorNode.value;
    }

    private Node floor(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return floor(node.left, key);
        else if (cmp == 0) return node;
        else {
            Node t = floor(node.right, key);
            if (t == null) return node;
            else return t;
        }
    }

    // ceiling - smallest node bigger than key
    public V ceil(K key) {
        Node ceilNode = ceil(root, key);
        if (ceilNode == null) return null;
        else return ceilNode.value;
    }

    private Node ceil(Node node, K key) {
        if (node == null) return null;
        int cmp = key.compareTo(node.key);
        // if input key is smaller than the node key, the node might be floor
        if (cmp < 0) {
            // but there might be a smaller key on the left that is the ceil
            // try and find smaller node.key on the left that is not bigger than the input key
            Node t = ceil(node.left, key);
            if (t == null) return node;
            else return t;
        }
        else if (cmp == 0) return node;
            // if node.key is bigger, ceiling is on the right
        else return ceil(node.right, key);
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        return (n == null) ? 0 : n.count;
    }

    public int rank(K key) {
        return rank(key, root);
    }
    public int rank(K key, Node node) {
        if (node == null) return 0;
        int cmp = key.compareTo(node.key);
        // if node.key is smaller, there might be some bigger keys on the left
        // that are still smaller than the input key
        if (cmp < 0) return rank(key, node.left);
        // if key > node.key, then all keys on the left, the, node and
        // all the keys on the right smaller than key
        else if (cmp > 0) return 1 + size(node.left) + rank(key, node.right);
        // otherwise the entire left subtree is smaller
        else return size(node.left);
    }
    public Iterable<K> levelOrderKeys() {
        Queue<Node> q = new ArrayDeque<>();
        List<K> keys = new ArrayList<>();
        if (root != null) {
            q.add(root);
        }
        while(!q.isEmpty()) {
            Node n = q.remove();
            keys.add(n.key);
            if (n.left != null) {
                q.add(n.left);
            }
            if (n.right != null) {
                q.add(n.right);
            }
        }
        return keys;
    }

    public Iterable<K> keys() {
        Queue<K> q = new ArrayDeque<>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node n, Queue<K> q) {
        if (n == null) return;
        inorder(n.left, q);
        q.add(n.key);
        inorder(n.right, q);
    }
    public boolean isBst() {
        return isBst(root);
    }
    private K prev;
    private boolean isBst(Node n) {
        if (n != null) {
            if (!isBst(n.left))
                return false;
            if (prev != null && n.key.compareTo(prev) < 0)
                return false;
            prev = n.key;
            return isBst(n.right);
        } else {
            return true;
        }
    }

    public boolean isBst2() {
        // as isBst() but does not use extra member variables
//        Queue<K> q = new ArrayDeque<K>();
//        inorder(root, q);
        K prev = null;
        for (K curr : keys()) {
            if (prev != null && curr.compareTo(prev) < 0)
                return false;
            prev = curr;
        }
        return true;
    }
    private class Node {
        Node left, right;
        K key;
        V value;
        int count;
        Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.count = 1;
        }
    }


    public static void main(String[] args) {
        // testing
        BinarySearchTree<Integer, String> bst = new BinarySearchTree<>();
        System.out.println("Ceil 10: " + bst.ceil(10));
        System.out.println("put: 0, \"Hello\"");

        bst.put(4, "Hello");
        System.out.println("put: 17, \"seventeen\"");
        bst.put(17, "seventeen");
        System.out.println("put: 1, \"worlds\"");
        bst.put(1, "worlds!");

        System.out.println("Min: " + bst.min());
        System.out.println("Max: " + bst.max());
        System.out.println("Floor 20: " + bst.floor(20));
        System.out.println("Ceil 10: " + bst.ceil(10));
        System.out.println("Size is 3: " + (bst.size() == 3));
        System.out.println("Rank 1 is 1: " + (bst.rank(1) == 1));
        System.out.println("Rank 20 is 3: " + (bst.rank(20) == 3));
        System.out.println(bst.get(4) + " " + bst.get(17) + " " + bst.get(1));
        System.out.println(bst.get(4) + " " + bst.get(87) + " " + bst.get(1));
        System.out.println(bst.levelOrderKeys().toString());
        System.out.println(bst.keys().toString());
        System.out.println("is bst? " + bst.isBst());
        System.out.println("is bst2? " + bst.isBst2());
    }
}
