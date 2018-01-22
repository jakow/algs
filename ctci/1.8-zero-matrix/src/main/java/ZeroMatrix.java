import java.util.Arrays;

/**
 * Created by jakub on 19/01/2018.
 */
public class ZeroMatrix {
    public static void zeroMatrix(int[][] matrix) {
        int M = matrix.length;
        if (M == 0) {
            return;
        }
        int N = matrix[0].length;
        boolean[] isZeroRow = new boolean[M];
        boolean[] isZeroCol = new boolean[N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (matrix[i][j] == 0) {
                    isZeroRow[i] = true;
                    isZeroCol[j] = true;
                }
            }
        }

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (isZeroRow[i] || isZeroCol[j]) {
                    matrix[i][j] = 0;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {
                new int[] {1,2,3,0,5,6,7},
                new int[] {1,2,3,4,4,5,7},
                new int[] {1,2,3,4,5,0,7},
                new int[] {1,2,3,4,5,6,7},
                new int[] {0,2,3,4,5,6,7},
                new int[] {1,2,3,4,5,6,7},
                new int[] {1,0,3,4,5,6,7},
        };
        zeroMatrix(matrix);
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

