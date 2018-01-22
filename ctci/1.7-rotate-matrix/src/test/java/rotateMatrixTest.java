import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jakub on 19/01/2018.
 */
public class rotateMatrixTest {
    @Test
    public void rotateMatrixTest() {
        int[][] matrix = new int[][] {
                new int[] { 1,2,3,4 },
                new int[] { 5,6,7,8 },
                new int[] { 9,10,11,12 },
                new int[] { 13,14,15,16 },
        };
        int[][] expected = new int[][] {
                new int[] { 4,8,12,16 },
                new int[] { 3,7,11,15 },
                new int[] { 2,6,10,14 },
                new int[] { 1,5,9,13 },
        };
        RotateMatrix.rotateMatrix(matrix);
        Assert.assertArrayEquals(expected, matrix);
    }
}
