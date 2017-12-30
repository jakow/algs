import org.junit.Assert;
import org.junit.Test;

public class UnionFindTest {
    @Test
    public void testWithoutUnions() {
        UnionFind uf = new UnionFind(8);
        Assert.assertEquals(4, uf.find(4));
    }

    @Test
    public void unionTest() {
        UnionFind uf = new UnionFind(8);
        uf.union(3, 5);
        Assert.assertEquals(3, uf.find(5));
        Assert.assertEquals(3, uf.find(3));
    }

    @Test
    public void multipleUnionTest() {
        UnionFind uf = new UnionFind(8);
        uf.union(2, 4);
        uf.union(6, 4);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(1, 4);
        uf.union(1, 2);
        Assert.assertEquals(2, uf.find(7));
        Assert.assertEquals(2, uf.find(4));
    }

    @Test
    public void connectedTest() {
        UnionFind uf = new UnionFind(8);
        uf.union(2, 4);
        uf.union(6, 4);
        uf.union(5, 6);
        uf.union(6, 7);
        uf.union(1, 4);
        uf.union(1, 2);
        Assert.assertTrue(uf.connected(7, 2));
    }
}
