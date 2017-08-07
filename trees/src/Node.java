
class Node<K, V> {
    K key;
    V value;
    Node<K, V> left, right;
    int count;

    Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.count = 1;
    }
}
