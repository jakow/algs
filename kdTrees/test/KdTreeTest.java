import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class KdTreeTest {

    @Test
    public void test1() {
        KdTree tree = new KdTree();
        In in  = new In("data/input100K.txt");
        int count = 20;
        while(count-- > 0) {
            Point2D p = new Point2D(in.readDouble(), in.readDouble());
            tree.insert(p);
        }
        double xmin = 0.3, ymin = 0.3, xmax = 0.7, ymax = 0.7;
        RectHV range = new RectHV(xmin, ymin, xmax, ymax);

        StdOut.println(tree.range(range));
//        StdDraw.setPenColor(Color.LIGHT_GRAY);
//        StdDraw.rectangle((xmax + xmin)/2, (ymax + ymin) / 2, (xmax - xmin) / 2, (ymax - ymin) /2 );
//        tree.draw();

        // simple test
        range = new RectHV(0.15, 0.35, 0.4, 0.45);
        KdTree allPoints = new KdTree();
        Set<Point2D> inside = new TreeSet<>();
        // data that is in the rect
        Point2D[] insidePoints = new Point2D[] {
                new Point2D(0.3, 0.4),
                new Point2D(0.4, 0.4),
                new Point2D(0.2, 0.4),
        };


        for (Point2D p : insidePoints) {
            assertTrue(range.contains(p));
            allPoints.insert(p);
            inside.add(p);
        }

        // data not inside
        Point2D[] outsidePoints = new Point2D[] {
                new Point2D(0.5, 0.5),
                new Point2D(0.1, 0.1),
                new Point2D(0.7, 0.9),
        };

        for (Point2D p : outsidePoints) {
            assertFalse(range.contains(p));
            allPoints.insert(p);
        }

        assertEquals(inside, allPoints.range(range));
        assertEquals(6, allPoints.size());
        assertFalse(allPoints.isEmpty());


        for (Point2D p : insidePoints) assertTrue(allPoints.contains(p));
        for (Point2D p : outsidePoints) assertTrue(allPoints.contains(p));

//        assertEquals(new Point2D(0.1, 0.1), allPoints.nearest(new Point2D(0.12, 0.12)));
//        assertEquals(new Point2D(0.4, 0.4), allPoints.nearest(new Point2D(0.4, 0.4)));
//        assertEquals(new Point2D(0.4, 0.4), allPoints.nearest(new Point2D(0.35, 0.4)));
//        assertEquals(new Point2D(0.5, 0.5), allPoints.nearest(new Point2D(0.55, 0.4)));


    }

    @Test
    public void sizeTest() {
        KdTree tree = new KdTree();
        List<Point2D> list = new ArrayList<>();
        In in  = new In("data/input100K.txt");
        while(!in.isEmpty()) {
            Point2D p = new Point2D(in.readDouble(), in.readDouble());
            list.add(p);
            tree.insert(p);
        }
        assertEquals(100000, tree.size());
        for (Point2D p : list) {
            assertTrue(tree.contains(p));
        }
    }
}
