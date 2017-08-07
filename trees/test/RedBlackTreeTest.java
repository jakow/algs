
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class RedBlackTreeTest {
    @Test
    public void sizeTest() {
        RedBlackTree<Integer, String> b = new RedBlackTree<>();
        b.put(1, "Hello");
        System.out.println(b.keys());
        b.put(2, "World!");
        System.out.println(b.keys());

        assertEquals(2, b.size());
    }

    @Test
    public void getTest() {
        RedBlackTree<Integer, String> b = new RedBlackTree<>();
        b.put(1, "Hello");
        b.put(2, "World!");
        assertEquals(null, b.get(3));
        assertEquals("World!", b.get(2));
    }

    @Test
    public void floorCeilMinMaxTest() {
        RedBlackTree<Character, Integer> b = new RedBlackTree<>();
        b.put('B', 12);
        b.put('Q', 1);
        b.put('G', 124);
        b.put('S', 12344);
        assertEquals(new Character('Q'), b.floor('Q'));
        assertEquals(new Character('B'), b.floor('D'));
        assertEquals(new Character('S'), b.ceil('R'));
        assertEquals(new Character('B'), b.ceil('A'));
        assertEquals(new Character('B'), b.min());
        assertEquals(new Character('S'), b.max());
    }

    @Test
    public void keysAndValuesTest() {
        OrderedMap<Character, Integer> b = new RedBlackTree<>();
        b.put('B', 1);
        b.put('G', 3);
        b.put('Q', 2);
        b.put('S', 4);
        Queue<Character> keys = new ArrayDeque<>();
        keys.add('B');
        keys.add('G');
        keys.add('Q');
        keys.add('S');
        Queue<Integer> values = new ArrayDeque<>();
        values.add(1);
        values.add(3);
        values.add(2);
        values.add(4);
        assertArrayEquals(keys.toArray(),  b.keys().toArray());
        assertArrayEquals(values.toArray(),  b.values().toArray());

    }
}
