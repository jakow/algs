/**
 * Created by jakub on 26/02/2017.
 */
public class Event implements Comparable<Event> {
    public final Particle a, b;
    private final int countA, countB;
    public final double time;
    public Event(double time, Particle a, Particle b) {
        this.time = time;
        this.a = a;
        this.b = b;
        countA = (a != null) ? a.getCount() : -1;
        countB = (b != null) ? b.getCount() : -1;
    }
    public boolean isValid() {
        // if null then particle event valid, otherwise no collisions must have occurred before.
        // int overflow is not important.
        if (a != null && a.getCount() != countA) return false;
        if (b != null && b.getCount() != countB) return false;
        return true;
    }

    @Override
    public int compareTo(Event that) {
        return Double.compare(this.time, that.time);
    }

    public String toString() {
        String eventType;
        if (a == null && a == null)
            eventType = "Redraw event";
        else if (a == null)
            eventType = "Horizontal wall hit event";
        else if (b == null)
            eventType = "Vertical wall hit event";
        else
            eventType = String.format("Particle collision %s -> %s", a.toString(), b.toString());

        return String.format("%s at %.2f", eventType, time);
    }
}
