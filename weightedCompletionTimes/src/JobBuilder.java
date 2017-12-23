public class JobBuilder {
    private int weight;
    private int duration;

    public JobBuilder setWeight(int weight) {
        this.weight = weight;
        return this;
    }

    public JobBuilder setDuration(int duration) {
        this.duration = duration;
        return this;
    }

    public Job createJob() {
        return new Job(weight, duration);
    }
}