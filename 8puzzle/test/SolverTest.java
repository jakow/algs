import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class SolverTest {
    @Test
    public void testOnePuzzle() {
        File[] files = readFiles();
        readAndSolve(files[0]);
    }

    @Test
    public void testAllPuzzles() {
        File[] files = readFiles();
        for (File f : files) {
            StdOut.println(f.getName());
            readAndSolve(f);
        }
    }
    public File[] readFiles()  {
        File folder = new File("input");
        StdOut.println(folder.getAbsolutePath());
        File[] files = folder.listFiles();
        if (files == null) throw new RuntimeException("No files to run");
        return files;
    }

    public void readAndSolve(File f) {
        In in = new In(f);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocks[i][j] = in.readInt();
            }
        }
        Board initial = new Board(blocks);
        // solve the puzzle
        Solver solver = new Solver(initial);
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
