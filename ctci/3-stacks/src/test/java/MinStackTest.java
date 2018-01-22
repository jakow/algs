import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 20/01/2018.
 */
public class MinStackTest {
    @Test
    public void minStackTest() {
        MinStack<Integer> s = new MinStack<>();
        s.push(12); // 12
        Assert.assertEquals(Integer.valueOf(12), s.min());
        s.push(10); //12, 10
        s.push(18); // 12, 10, 18
        Assert.assertEquals(Integer.valueOf(10), s.min());
        s.push(10); // 12, 10, 18, 10
        Assert.assertEquals(Integer.valueOf(10), s.min());
        s.push(24); // 12, 10, 18, 10, 24
        Assert.assertEquals(Integer.valueOf(10), s.min());
        s.pop(); // 12, 10, 18, 10
        s.pop(); // 12, 10, 18
        s.pop();//12, 10
        Assert.assertEquals(Integer.valueOf(10), s.min());
        s.push(6);
        s.push(8);
        Assert.assertEquals(Integer.valueOf(6), s.min());


    }
}
