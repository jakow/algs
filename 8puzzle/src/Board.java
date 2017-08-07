import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {
    // construct a board from an n-by-n array of blocks
    private int[][] blocks;
    private boolean hasHamming = false;
    private int hammingDistance = 0;
    private boolean hasManhattan = false;
    private int manhattanDistance = 0;

    public Board(int[][] blocks) {
        if (blocks == null) {
            throw new NullPointerException("Blocks is null");
        }
        this.blocks = clone2d(blocks);
    }
    public int dimension() { // board dimension n
        return blocks.length;
    }

    // number of blocks out of place
    public int hamming() {
        // has a cached hamming distance value? If so, do not recompute it
        if (hasHamming) {
            return hammingDistance;
        }
        hammingDistance = 0;
        for (int row = 0; row < dimension(); ++row) {
            for (int col = 0; col < dimension(); ++col) {
                int correctValue = row * dimension() + col + 1;
                if (correctValue != dimension()*dimension() && blocks[row][col] != correctValue)
                    hammingDistance++;
            }
        }
        hasHamming = true;
        return hammingDistance;
    }
    // sum of Manhattan distances between blocks and goal
    public int manhattan() {
        if (hasManhattan) {
            return manhattanDistance;
        }
        manhattanDistance = 0;
        for (int row = 0; row < dimension(); ++row) {
            for (int col = 0; col < dimension(); ++col) {
                int value = blocks[row][col];
                // exclude the empty block
                if (value != 0) {
                    // calculate the correct spot on the board for the value
                    int correctCol = (value - 1) % dimension();
                    int correctRow = (value - 1) / dimension();
                    manhattanDistance += ( Math.abs(col - correctCol) + Math.abs(row - correctRow));
                }
            }
        }
        hasManhattan = true;
        return manhattanDistance;
    }
    // is this board the goal board?
    public boolean isGoal()  {
        return hamming() == 0;
    }
    // a board that is obtained by exchanging any pair of blocks
    public Board twin() {
        int dest, src;
        int aRow, aCol, bRow, bCol;
        do {
            src = StdRandom.uniform(dimension()*dimension()); // in range [0:dimension^2)
            aRow = src / dimension();
            aCol = src % dimension();
        } while (blocks[aRow][aCol] == 0);

        do {
            dest = StdRandom.uniform(dimension()*dimension());
            bRow = dest / dimension();
            bCol = dest % dimension();
        } while (dest == src || blocks[bRow][bCol] == 0);

        return new Board(blocks, aRow, aCol, bRow, bCol);
    }

    public boolean equals(Object y) {
        if (y == null) {
            return false;
        } else if (!(y instanceof Board)) {
            return false;
        } else {
            Board other = (Board) y;
            if (this.dimension() != other.dimension()) {
                return false;
            }
            for (int row = 0; row < dimension(); ++row) {
                if (!Arrays.equals(this.blocks[row], other.blocks[row])) {
                    return false;
                }
            }
            return true;
        }
    }
    // all neighboring boards
    public Iterable<Board> neighbors() {
        List<Board> neighbors = new ArrayList<>();
        int[] coordinatesOf0 = findZero();
        if (coordinatesOf0 == null) {
            throw new RuntimeException("Board object does not contain an empty square (0) and thus is invalid");
        }
        int row0 = coordinatesOf0[0];
        int col0 = coordinatesOf0[1];
        // top neighbor
        if (row0 > 0) {
            neighbors.add(new Board(blocks, row0, col0, row0 - 1, col0));
        }
        // bottom neighbor
        if (row0 < dimension() - 1) {
            neighbors.add(new Board(blocks, row0, col0, row0 + 1, col0));
        }
        // left neighbor
        if (col0 > 0) {
            neighbors.add(new Board(blocks, row0, col0, row0, col0 - 1));
        }
        // right neighbor
        if (col0 < dimension() -1) {
            neighbors.add(new Board(blocks, row0, col0, row0, col0+ 1));
        }
        return neighbors;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(dimension()).append("\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }
    // unit tests (not graded)
    public static void main(String[] args) {

    }

    private int[] findZero() {
        int[] coords = new int[2];
        for (int row = 0; row < dimension(); ++row) {
            for (int col = 0; col < dimension(); ++col) {
                int value = blocks[row][col];
                if (value == 0) {
                    coords[0] = row;
                    coords[1] = col;
                    return coords;
                }
            }
        }
        return null;
    }

    // string representation of this board



    private int[][] clone2d(int[][] src) {
        int[][] dest = new int[src.length][];
        for (int i = 0; i < src.length; ++i) {
            dest[i] = Arrays.copyOf(src[i], src[i].length);
        }
        return dest;
    }


    private void swap(int aRow, int aCol, int bRow, int bCol) {
        int temp = blocks[aRow][aCol];
        blocks[aRow][aCol] = blocks[bRow][bCol];
        blocks[bRow][bCol] = temp;
    };

    /**
     * Swapping constructor to easily create twins and neighbors.
     * @param b
     * @param aRow
     * @param aCol
     * @param bRow
     * @param bCol
     */
    private Board(int[][] b, int aRow, int aCol, int bRow, int bCol) {
        blocks = clone2d(b);
        swap(aRow, aCol, bRow, bCol);
    }
}