/**
 * Created by jakub on 27/01/2018.
 */
public class TripleStep {
    static int tripleStep(int steps) {
        if (steps < 0) {
            return 0;
        }
        if (steps == 0 || steps == 1) {
            return 1;
        } else if (steps  == 2) {
            return 2;
        } else if (steps == 3) {
            return 4;
        } else {
            return tripleStep(steps - 1) + tripleStep(steps - 2) + tripleStep(steps - 3);
        }
    }

    static int tripleStepDp(int steps) {
        int[] dp = new int[steps + 1];
        dp[0] = 1;
        for (int i = 1; i <= steps; ++i) {
            int curr = 0;
            if (i - 1 >= 0) {
                curr += dp[i - 1];
            }
            if (i - 2 >= 0) {
                curr += dp[i - 2];
            }
            if (i - 3 >= 0) {
                curr += dp[i - 3];
            }
            dp[i] = curr;
        }
        return dp[steps];
    }
}
