
/**
 * Created by jakub on 23/12/2017.
 */
public class Job {
    final int weight;
    final int duration;

    Job(int weight, int duration) {
        this.weight = weight;
        this.duration = duration;
    }

    @Override
    public String toString() {
        return String.format("Job {weight: %d, duration %d}", weight, duration);
    }
}
