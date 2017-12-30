/**
 * Created by jakub on 29/12/2017.
 */
public class Distance {
    final int from;
    final int to;
    final int distance;

    Distance(int from, int to, int distance) {
        this.from = Math.min(from, to);
        this.to = Math.max(from, to);
        this.distance = distance;
    }

    int other(int point) {
        return point == from ? to : from;
    }

    @Override
    public String toString() {
        return String.format("Dist %d from %d to %d", distance, from, to);
    }
}
