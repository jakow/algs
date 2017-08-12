import edu.princeton.cs.algs4.*;

import java.awt.Color;
import java.util.*;

public class KdTree {
    private static boolean X_COORD = true;
    private static boolean Y_COORD = false;

    private Node root;
    private int sz = 0;

    public boolean isEmpty() {
        return sz == 0;
    }

    public int size() {
        return sz;
    }

    public boolean contains(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        Node n = root;
        boolean coordinate = X_COORD;
        while (n != null) {
            double cmp = keyOf(p, coordinate) - keyOf(n);
            if (cmp < 0) {
                n = n.left;
            } else if (cmp > 0) {
                n = n.right;
            } else if (valueOf(n) == valueOf(p, coordinate)) {
                return true;
            } else {
                n = n.right;
            }
            coordinate = !coordinate;
        }
        return false;
    }

    public void insert(Point2D p) {
        if (p == null) throw new IllegalArgumentException();
        root = insert(root, p, X_COORD);
    }

    private Node insert(Node n, Point2D point, boolean coordinate) {
        if (n == null) {
            sz++;
            return new Node(point, coordinate);
        }
        double key = keyOf(point, coordinate);
        int comp = Double.compare(key, keyOf(n));
        if (comp < 0) {
            n.left = insert(n.left, point, !coordinate);
        } else { // assume no duplicates
            if (n.point.equals(point)) throw new IllegalArgumentException("Duplicate point detected");
            n.right = insert(n.right, point, !coordinate);
        }
        return n;
    }

    public Point2D nearest(Point2D query) {
        if (query == null) throw new IllegalArgumentException();
        if (root == null) return null;
        return nearest(root, query, root.point);
    }

    private Point2D nearest(Node node, Point2D query, Point2D best) {
        if (node == null) return best;
        // distance to null is positive infinity
        double bestDist = best.distanceSquaredTo(query);
        double newDist = distance(node, query);
        if (newDist < bestDist) {
            best = node.point;
        }
        // now
        double diff = node.coordinate == X_COORD ? query.x() - node.point.x() : query.y() - node.point.y();
        int cmp = (int) Math.signum(diff);
        if (diff < 0) {
            // go left/bottom first
            best = nearest(node.left, query, best);
        } else {
            best = nearest(node.left, query, best);
            // go right first
        }
        return best;

    }

    private static double distance(Node node, Point2D query) {
        if (node == null || query == null) {
            return Double.POSITIVE_INFINITY;
        } else {
            return node.point.distanceSquaredTo(query);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Set<Point2D> points =  new TreeSet<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node node, RectHV rect, Set<Point2D> points) {
        if (node == null) return;
//        StdOut.println("Range " + rect + " on point " + node.point);
        if (rect.contains(node.point)) {
            points.add(node.point);
        }
        // does the rectangle lie (fully or partially)  on the left/bottom side of this partition?
        if (rectMin(rect, node) < keyOf(node.point, node.coordinate)) {
            range(node.left, rect, points);
        }
        // does the rectangle lie on the right/top side of this partition?
        if (rectMax(rect, node) > keyOf(node.point, node.coordinate)) {
          range(node.right, rect, points);
        }
    }

    private static double keyOf(Point2D point, boolean coordinate) {
        return coordinate == X_COORD ? point.x() : point.y();
    }

    private static double keyOf(Node n) {
        return keyOf(n.point, n.coordinate);
    }

    private static double valueOf(Point2D point, boolean coordinate) {
        return coordinate == X_COORD ? point.y() : point.x();
    }

    private static double valueOf(Node n) {
        return valueOf(n.point, n.coordinate);
    }

    private static double rectMax(RectHV rect, Node n) {
        return n.coordinate == X_COORD ? rect.xmax() : rect.ymax();
    }

    private static double rectMin(RectHV rect, Node n) {
        return n.coordinate == X_COORD ? rect.xmin() : rect.ymin();
    }

    public void draw() {
        drawNode(root, 0, 0, 1, 1);
    }

    /**
     *
     * @param node node to draw
     */
    private void drawNode(Node node, double minX, double minY, double maxX, double maxY) {
        if (node == null) return; // nothing to draw left
        // first draw the point
        StdDraw.setPenRadius(0.008);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.point(node.point.x(), node.point.y());
        // then draw a line
        StdDraw.setPenRadius(0.002);
        double key = node.key();
        if (node.coordinate == X_COORD) { // draw a vertical line
            StdDraw.setPenColor(Color.RED);
            StdDraw.line(key, minY, key, maxY);
            // draw horizontal left and right constrained by this nodes x coordinate on the right and left respectively
            drawNode(node.left, minX, minY, key, maxY);
            drawNode(node.right, key, minY, maxX, maxY);

        } else { // draw a horizontal line
            StdDraw.setPenColor(Color.BLUE);
            StdDraw.line(minX, key, maxX, key);
            // draw vertical left and right nodes constrained by this nodes y coordinate on the top and bottom respectively
            drawNode(node.left, minX, minY, maxX, key);
            drawNode(node.right, minX, key, maxX, maxY);
        }
    }

    private static class Node {
        Node left, right;
        boolean coordinate;
        Point2D point;

        Node(Point2D point, boolean coordinate) {
            this.point = point;
            this.coordinate = coordinate;
        }

        double key() {
            return coordinate == X_COORD ? point.x() : point.y();
        }

    }

    public static void main(String[] args) {

    }
}
