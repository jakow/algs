/**
 * Created by jakub on 19/01/2018.
 */
public class RotateMatrix {
    public static int[][] rotateMatrix(int[][] mat) {
        int N = mat.length - 1;
        int M = mat[0].length - 1;
        if (M != N) {
            throw new IllegalArgumentException("Not a square matrix");
        }
        for (int x = 0; x < (N + 1) / 2; ++x) {
            for (int y = 0; y < (N + 1) / 2; ++y) {
                int a = mat[x][y];
                int b = mat[N - y][x];
                int c = mat[N - x][N - y];
                int d = mat[y][N - x];
                mat[N - y][x] = a;
                mat[N - x][N - y] = b;
                mat[y][N - x] = c;
                mat[x][y] = d;
            }
        }
        return mat;
    }
}
