import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 20/01/2018.
 */
public class ThreeStacksTest {
    @Test
    public void createTest() {
        ThreeStacks<Integer> stacks = new ThreeStacks<>(8);
        Assert.assertEquals(0, stacks.size(0));
        Assert.assertEquals(0, stacks.size(1));
        Assert.assertEquals(0, stacks.size(2));
    }

    @Test
    public void insertTest() {
        ThreeStacks<Integer> stacks = new ThreeStacks<>(4);
        stacks.push(0, 1);
        stacks.push(0, 2);
        stacks.push(0, 3);
        stacks.push(0, 4);
        Assert.assertEquals(Integer.valueOf(4), stacks.peek(0));
    }

    @Test
    public void insertSecondTest() {
        ThreeStacks<Integer> stacks = new ThreeStacks<>(2);
        stacks.push(1, 1);
        stacks.push(1, 2);
        stacks.push(1, 3);
        stacks.push(1, 4);
        Assert.assertEquals(Integer.valueOf(4), stacks.peek(1));
    }

    @Test
    public void insertMultipleTest() {
        ThreeStacks<Integer> stacks = new ThreeStacks<>(2);
        stacks.push(0, 1);
        stacks.push(0, 2);
        stacks.push(1, 6);
        stacks.push(1, 7);
        stacks.push(0, 3);
        stacks.push(1, 8);
        stacks.push(2, 11);
        stacks.push(0, 4);
        stacks.push(1, 9);
        stacks.push(1, 10);
        stacks.push(2, 12);
        stacks.push(2, 13);
        stacks.push(2, 14);
        stacks.push(0, 5);
        stacks.push(2, 15);

        Assert.assertEquals(Integer.valueOf(5), stacks.peek(0));
        Assert.assertEquals(Integer.valueOf(10), stacks.peek(1));
        Assert.assertEquals(Integer.valueOf(15), stacks.peek(2));

        stacks.pop(0);
        stacks.pop(1);
        stacks.pop(2);
        stacks.pop(2);
        stacks.pop(0);
        stacks.pop(1);
        stacks.pop(2);
        stacks.pop(1);
        stacks.pop(0);

        Assert.assertEquals(Integer.valueOf(2), stacks.peek(0));
        Assert.assertEquals(Integer.valueOf(7), stacks.peek(1));
        Assert.assertEquals(Integer.valueOf(12), stacks.peek(2));
        Assert.assertEquals(2, stacks.size(0));
        Assert.assertEquals(2, stacks.size(1));
        Assert.assertEquals(2, stacks.size(2));
    }
 }
