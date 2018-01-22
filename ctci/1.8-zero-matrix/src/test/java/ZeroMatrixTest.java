import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by jakub on 19/01/2018.
 */
public class ZeroMatrixTest {

    @Test
    public void test1() {
        int[][] matrix = new int[][] {
                new int[] {1,2,3,0,5,6,7},
                new int[] {1,2,3,4,5,6,7},
                new int[] {1,2,3,4,5,0,7},
                new int[] {1,2,3,4,5,6,7},
                new int[] {0,2,3,4,5,6,7},
                new int[] {1,2,3,4,5,6,7},
                new int[] {1,0,3,4,5,6,7},
        };

        int[][] expected = new int[][] {
                new int[] {0,0,0,0,0,0,0},
                new int[] {0,0,3,0,5,0,7},
                new int[] {0,0,0,0,0,0,0},
                new int[] {0,0,3,0,5,0,7},
                new int[] {0,0,0,0,0,0,0},
                new int[] {0,0,3,0,5,0,7},
                new int[] {0,0,0,0,0,0,0},
        };

        ZeroMatrix.zeroMatrix(matrix);
        Assert.assertArrayEquals(expected, matrix);
    }
}
