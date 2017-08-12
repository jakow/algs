
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assert(b.is23());
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

    @Test
    public void validationTest() {
        RedBlackTree<Integer, Integer> b = new RedBlackTree<>();
        b.put(2, 2); // root
        b.put(1, 1); // left, will be red first
        assertTrue(b.isBalanced());
        b.put(3, 3); // right, will be also red but will be fixed by flipping colors
        assertTrue(b.isRedBlackTree());
        b.put(-1, -1); // will become a red node on the very left and will still be balanced
        assertTrue(b.isRedBlackTree());
        b.put(-2, -2); // will add another minimum, get rotated right and colors fliped
        assertTrue(b.isRedBlackTree());
        b.deleteMax(); // try a delete!
        assertTrue(b.isRedBlackTree());
        b.deleteMax(); // try again
        assertTrue(b.isRedBlackTree());
        b.deleteMax(); // ..and again
        assertTrue(b.isRedBlackTree());

    }
}
