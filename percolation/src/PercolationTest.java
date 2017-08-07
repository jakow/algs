import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * Created by jakub on 24/01/2017.
 */
public class PercolationTest {
//    @Before
//    public void setUp() throws Exception {
//
//    }
//
//    @After
//    public void tearDown() throws Exception {
//
//    }

    @Test
    public void opened() {
        Percolation p = new Percolation(8);
        p.open(1,1);
        assertEquals(true, p.isOpen(1,1));
        assertEquals(true, p.isFull(1,1));
        assertEquals(1, p.numberOfOpenSites());
    }

    @Test
    public void percolatesSingle() {
        Percolation p = new Percolation(1);
        assertEquals(false, p.isFull(1,1));
        p.open(1,1);

        assertEquals(p.percolates(), true);
    }

    @Test
    public void isFullTop() {
        Percolation p = new Percolation(3);
        assertEquals(false, p.isFull(1,1));
        p.open(1,1);
        assertEquals(true, p.isFull(1,1));
    }
    @Test
    public void isFullAny() {
        Percolation p = new Percolation(8);
        assertEquals(false, p.isFull(2,2));
        p.open(1,2);
        p.open(2,2);
        p.open(3,2);
        assertEquals(true, p.isFull(3,2));
    }

    @Test
    public void percolates1() {
        Percolation p = new Percolation(3);
        p.open(1,1);
        p.open(2,1);
        p.open(3,1);
        assertEquals(p.percolates(), true);
    }

    @Test
    public void percolates2() {
        Percolation p = new Percolation(3);
        p.open(1,1);
        p.open(2,1);
        p.open(2,2);
        p.open(3,2);
        assertEquals(true, p.isFull(2,2));
        assertEquals(true, p.percolates());
        assertEquals(p.numberOfOpenSites(),4);
    }

    @Test
    public void doesNotPercolate() {
        Percolation p = new Percolation(3);
        p.open(1,1);
        p.open(2,1);
        assertEquals(false, p.percolates());
    }
}