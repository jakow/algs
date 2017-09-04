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
        root = insert(root, p, X_COORD, 0, 0, 1, 1);
    }

    private Node insert(Node n, Point2D point, boolean coordinate, double xmin, double ymin, double xmax, double ymax) {
        if (n == null) {
            sz++;
            return new Node(point, coordinate, new RectHV(xmin, ymin, xmax, ymax));
        }
        double key = keyOf(point, coordinate);
        int comp = Double.compare(key, keyOf(n));
        if (comp < 0) {
            // restrict the rectangle to the left
            xmax = coordinate == X_COORD ? n.point.x() : xmax;
            ymax = coordinate == Y_COORD ? n.point.y() : ymax;
            n.left = insert(n.left, point, !coordinate, xmin, ymin, xmax, ymax);
        } else { // assume no duplicates
            xmin = coordinate == X_COORD ? n.point.x() : xmin;
            ymin = coordinate == Y_COORD ? n.point.y() : ymin;
            n.right = insert(n.right, point, !coordinate, xmin, ymin, xmax, ymax);
        }
        return n;
    }


    public Point2D nearest(Point2D query) {
        if (query == null) throw new IllegalArgumentException("Nearest neighbour query is null");
        return nearest2(root, query);
    }

    private Point2D nearest(Node n, Point2D query, Point2D best) {
        if (n == null) return best;
        double currentDist = distance(best, query);
        double newDist = distance(n.point, query);
        if (newDist <= currentDist) {
            best = n.point;
            currentDist = newDist;
        }
        // which node to search first?
        double cmp = keyOf(query, n.coordinate) - keyOf(n);
        Node first, second;
        if (cmp < 0) {
            // search bottom/left first
            first = n.left;
            second = n.right;
        } else {
            // search top/right first
            first = n.right;
            second = n.left;
        }
        if (first != null && first.rect.distanceSquaredTo(query) < currentDist) {
            best = nearest(first, query, best);
            currentDist = distance(best, query);
        }
        if (second != null && second.rect.distanceSquaredTo(query) < currentDist) {
            best = nearest(first, query, best);
        }
        return best;
    }

    private Point2D nearest2(Node n, Point2D query) {
//        if (n == null) return null;
        Point2D best = n.point;
        double bestDistance = distance(query, best);
        // go left or right of the tree depending on the splitting coordinate
        Node first, second;
        // is the query left/bottom (<0) or right/top (>0) of the splitting point?
        double cmp = keyOf(query, n.coordinate) - keyOf(n);
        if (cmp < 0) { // query is less
            first = n.left;
            second = n.right;
        } else {
            first = n.right;
            second = n.left;
        }
        if (first != null) {
            Point2D firstBest = nearest2(first, query);
            if (distance(firstBest, query) < distance(best, query)) {
                best = firstBest;
                bestDistance = distance(query, best);
            }
        }
        // From wiki: comparison to see whether the distance between the splitting coordinate of the query point
        // and current node is lesser than the distance (overall coordinates) from the query point to the current best
        double splitDistance = Math.pow(cmp, 2);
        if (second != null && splitDistance < bestDistance) {
            Point2D secondBest = nearest2(second, query);
            if (distance(secondBest, query) < distance(best, query)) {
                best = secondBest;
            }
        }
        return best;
    }

    private static double distance(Point2D p1, Point2D p2) {
        if (p1 == null || p2 == null) {
            return Double.POSITIVE_INFINITY;
        } else {
            return p1.distanceSquaredTo(p2);
        }
    }

    public Iterable<Point2D> range(RectHV rect) {
        Set<Point2D> points =  new TreeSet<>();
        range(root, rect, points);
        return points;
    }

    private void range(Node node, RectHV query, Set<Point2D> points) {
        if (node == null) return;
//        StdOut.println("Range " + rect + " on point " + node.point);
        if (query.contains(node.point)) {
            points.add(node.point);
        }
        // does the rectangle lie (fully or partially)  on the left/bottom side of this partition?
        if (node.left != null && node.left.rect.intersects(query)) {
            range(node.left, query, points);
        }
        // does the rectangle lie on the right/top side of this partition?
        if (node.right != null && node.right.rect.intersects(query)) {
          range(node.right, query, points);
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
        RectHV rect;

        Node(Point2D point, boolean coordinate, RectHV rect) {
            this.point = point;
            this.coordinate = coordinate;
            this.rect = rect;
        }

        double key() {
            return coordinate == X_COORD ? point.x() : point.y();
        }

    }

    public static void main(String[] args) {

    }
}
