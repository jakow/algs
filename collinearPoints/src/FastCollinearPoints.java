import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdIn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jakub on 25/02/2017.
 */
public class FastCollinearPoints {
    private List<LineSegment> segmentList;
    private List<Point> existingEndPoints; // to detect duplicates
    private List<Double> existingSlopes; // as above; should probably use a hashmap but not introduced in the course
    public FastCollinearPoints(Point[] points) {


        if (points == null) throw new NullPointerException("Argument is null");
        for (Point p : points) {
            if (p == null) throw new NullPointerException("Point p in Point[] points is null");
        }
        if (hasDuplicates(points)) throw new IllegalArgumentException("Duplicate or null points");


        // make a copy of the input argument
        segmentList = new ArrayList<>();
        existingEndPoints = new ArrayList<>();
        existingSlopes = new ArrayList<>();
        if (points.length < 4) return; // you cannot have a
        Point[] pts = Arrays.copyOf(points, points.length);
        Point p;


        Arrays.sort(pts);

        for (int i = 0; i < pts.length-3; ++i) {
            /* Sort with natural order (i.e. by coordinates). This will result in the maximum length segment when
             * iterating through points, as long as the sort is stable (which it is -- Java uses mergesort on objects)
             * A natural order sort will need to be repeated after each iteration, in order to maintain this invariant.
             */
            Arrays.sort(pts, i, pts.length); // sort with the natural order in order to get longest lines later
            p = pts[i];
            /* partial sort wrt slope of Point p. Java system sort (Mergesort) is stable and will result in the pts being
            * ordered according to slope and then to distance from p
            */
            Arrays.sort(pts, i+1, pts.length,  p.slopeOrder());

            int start = i+1;  // start with the current slope between i-th and (i+1)-th point.
            int count;
            for (int end = start+1; end < pts.length; ++end) {
                /* point p + number of items from start to end included */
                count = 1 + end - start + 1;
                if ( p.slopeTo(pts[start]) == p.slopeTo(pts[end])) {
                    if (end == pts.length-1 && count >= 4) {
                        // handle the case where we reached the end of the array
                        Point endPoint = pts[end];
                        addSegmentIfNew(p, endPoint, p.slopeTo(endPoint));
                    }
                } else {
                    start = end;
                    // otherwise found an unequal slope so the previous point is the last one
                    if (count-1 >= 4) { // count-1 to exclude current item which is unequal
                        Point endPoint = pts[end-1];
                        addSegmentIfNew(p, endPoint, p.slopeTo(endPoint)); //add line to previous point
                    }
                }
            }
        }
    }

    private void addSegmentIfNew(Point start, Point end, double slope) {
        // lines that share slope and end point are sub-segments
        for (int i = 0; i < segmentList.size(); ++i) {
            if (end == existingEndPoints.get(i) && slope == existingSlopes.get(i))
                // endpoints and slopes are common when the algorithm reaches a forming a sub-segment
                return;
        }
        segmentList.add(new LineSegment(start, end));
        existingEndPoints.add(end);
        existingSlopes.add(slope);

    }

    private boolean hasDuplicates(Point[] points) {
        for (int i = 0; i < points.length; ++i) {
            for (int j = 0; j < points.length; ++j) {
                if (j != i && points[i].compareTo(points[j]) == 0)
                    return true;
            }
        }
        return false;
    }

    public int numberOfSegments() {
        return segmentList.size();
    }

    public LineSegment[] segments() {
        return segmentList.toArray(new LineSegment[numberOfSegments()]);
    }

    public static void main(String[] args) {
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
        FastCollinearPoints fcp = new FastCollinearPoints(points);
        LineSegment[] segments = fcp.segments();
//        StdOut.println(fcp.numberOfSegments());
        for (LineSegment segment : segments) {
            segment.draw();
//            StdOut.println(segment.toString());
        }
        StdDraw.show();
    }

}
