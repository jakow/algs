import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jakub on 23/02/2017.
 */
public class BruteCollinearPoints {
    private List<LineSegment> segments;
    public BruteCollinearPoints(Point[] points) {

        segments = new ArrayList<>();
        if (points == null) throw new NullPointerException("Constructor argument: points");
        for (Point p : points) {
            if (p == null) throw new NullPointerException("Point p in Points[] points");
        }
        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {
                if (j != i && points[i].compareTo(points[j]) == 0)
                    throw new IllegalArgumentException("Duplicate points");
            }
        }
        if (points.length < 4) return;

        Point[] pts = Arrays.copyOf(points, points.length);
        Arrays.sort(pts);

        Point p, q, r, s;
        for (int a = 0; a < pts.length - 3; ++a) {
            p = pts[a];
            for (int b = a + 1; b < pts.length - 2; ++b) {
                q = pts[b];
                for (int c = b + 1; c < pts.length - 1; ++c) {
                    r = pts[c];
                    for (int d = c + 1; d < pts.length; ++d) {
                        s = pts[d];
                        if (collinear(p, q, r, s)) {
                            segments.add(new LineSegment(p, s));
                        }
                    }
                }
            }
        }

    }

    private boolean collinear(Point p, Point q, Point r, Point s) {
        double slope1 = p.slopeTo(q);
        double slope2 = p.slopeTo(r);
        double slope3 = p.slopeTo(s);
        return (slope1 == slope2) && (slope1 == slope3);
    }


    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        return segments.toArray(new LineSegment[0]);
    }

    public static void main(String[] args) {

        // read the n points from a file

        int n = StdIn.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = StdIn.readInt();
            int y = StdIn.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    private static Point[] readPoints(int n) {
        Point[] points = new Point[n];
        int x, y;
        for(int i = 0; i < n; i++) {
           x = StdIn.readInt();
           y = StdIn.readInt();
            points[i] = new Point(x,y);
        }
        return points;
    }


}
