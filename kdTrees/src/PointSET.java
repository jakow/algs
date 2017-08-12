import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Brute force range search and nearest-neighbour search solution.
 */
public class PointSET {
    private Set<Point2D> set;
    public PointSET() {
        set = new TreeSet<>();
    }

    public int size() {
        return set.size();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        set.add(p);
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        return set.contains(p);
    }

    public Iterable<Point2D> range(RectHV rect) {
        if (rect == null) throw new IllegalArgumentException();
        List<Point2D> list = new ArrayList<>();
        for (Point2D p : set) {
            if (rect.contains(p)) {
                list.add(p);
            }
        }
        return list;
    }

    public Point2D nearest(Point2D p) {
        Point2D nearestPoint = null;
        double minDistance = Double.POSITIVE_INFINITY;
        for (Point2D point : set) {
            if (nearestPoint == null) {
                nearestPoint = point;
                minDistance = nearestPoint.distanceTo(p);
            } else {
                double distance = point.distanceTo(p);
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestPoint = point;
                }
            }
        }
        return nearestPoint;
    }

    public void draw() {
        for (Point2D p : set) {
            StdDraw.point(p.x(), p.y());
        }
    }

    public static void main(String[] args) {
        PointSET s = new PointSET();
        s.insert(new Point2D(0.25, 0.25));
        s.insert(new Point2D(0.5, 0.5));
        StdOut.println(s.nearest(new Point2D(0.1, 0.1)));

        s = new PointSET();
        In in  = new In("data/input100K.txt");
        int count = 20;
        while(count-- > 0) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y);
            s.insert(p);
            StdOut.print(p + ", ");
        }
        StdOut.println("Nearest point in 100k points is " + s.nearest(new Point2D(0.45, 0.45)));
        RectHV range = new RectHV(0.5, 0.5, 0.7, 0.7);
        StdOut.println("Points in range " + range + " are " + s.range(range));
    }
}
