/**
 * Created by jakub on 29/12/2017.
 */
abstract public class Node implements Comparable<Node> {
    double frequency;

    public int compareTo(Node that) {
        return Double.compare(this.frequency, that.frequency);
    }
}
