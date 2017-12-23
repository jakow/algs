import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Job[] j = readInput();
        Arrays.sort(j, byDecreasingDifference());
        long differenceSum = weightedSumOfCompletionTimes(j);
        Arrays.sort(j, byDecreasingRatio());
        long ratioSum = weightedSumOfCompletionTimes(j);
        System.out.println(Arrays.toString(j));
        System.out.format(
                "Weighted sum of completion times:\nDifference: %d\nRatio: %d\n",
                differenceSum,
                ratioSum
        );
        System.out.format("ratio < difference: %b", ratioSum < differenceSum);

    }

    public static long weightedSumOfCompletionTimes(Job[] jobs) {
        long sum = 0;
        long completionTime = 0;
        for (Job j : jobs) {
            completionTime += j.duration;
            sum += completionTime * j.weight;
        }
        return sum;
    }

    public static Job[] readInput() {
        File f = new File("data/jobs.txt");
        Scanner s;
        try {
            s = new Scanner(f);
        } catch (IOException e) {
            throw new RuntimeException("File not found");
        }
        int jobCount = s.nextInt();
        Job[] jobs = new Job[jobCount];
        int i = 0;
        JobBuilder jb = new JobBuilder();
        while (s.hasNextInt()) {
            int weight = s.nextInt();
            int duration = s.nextInt();
            Job j = jb.setWeight(weight).setDuration(duration).createJob();
            jobs[i++] = j;
        }
        return jobs;
    }

    public static Comparator<Job> byDecreasingDifference() {
        return (j1, j2) -> {
            int leftScore = j1.weight - j1.duration;
            int rightScore = j2.weight - j2.duration;
            // higher score first
            if (leftScore != rightScore) return rightScore - leftScore;
            return j2.weight - j1.weight;
        };
    }

    public static Comparator<Job> byDecreasingRatio() {
        return (j1, j2) -> {
            double leftScore = ((double) j1.weight) / j1.duration;
            double rightScore = ((double) j2.weight) / j2.duration;
            return Double.compare(rightScore, leftScore);
        };
    }
}
