import java.util.Collection;
import java.util.Iterator;

public interface OrderedMap<K, V> {
    boolean isEmpty();
    int size();
    V get(K key);
    void put(K key, V value);
    V delete(K key);
    void clear();
    K floor(K key);
    K ceil(K key);
    K min();
    K max();
    Collection<K> keys();
    Collection<V> values();
    Collection<Entry<K,V>> entries();
}
