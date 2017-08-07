import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BoardTest {
    @Test
    public void testDistances() {
        int[][] blocks = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        };
        Board board = new Board(blocks);
        assertEquals(0, board.manhattan());
        assertEquals(0, board.hamming());


        blocks[0][0] = 2;
        blocks[0][1] = 1;
        board = new Board(blocks);
        assertEquals(2, board.manhattan());
        assertEquals(2, board.hamming());

        blocks = new int[][] {
                {6, 2, 3},
                {4, 5, 1},
                {7, 8, 0},
        };

        board = new Board(blocks);
        assertEquals(2, board.hamming());
        assertEquals(6, board.manhattan());

        blocks = new int[][] {
                {6, 0, 3},
                {4, 5, 1},
                {7, 8, 2},
        };
        board = new Board(blocks);
        assertEquals(3, board.hamming());
        assertEquals(9, board.manhattan());


    }

    @Test
    public void equality() {
        int[][] blocks = new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0},
        };
        Board board1 = new Board(blocks);
        Board board2 = new Board(blocks);
        assertTrue(board1.equals(board2));
    }

    @Test
    public void neighbors() {
        int[][] boardBlocks = new int[][] {
                {1, 2, 3},
                {4, 0, 6},
                {7, 8, 5},
        };

        int[][] topBlocks = new int[][] {
                {1, 0, 3},
                {4, 2, 6},
                {7, 8, 5},
        };
        int[][] bottomBlocks = new int[][] {
                {1, 2, 3},
                {4, 8, 6},
                {7, 0, 5},
        };

        int[][] leftBlocks = new int[][] {
                {1, 2, 3},
                {0, 4, 6},
                {7, 8, 5},
        };

        int[][] rightBlocks = new int[][] {
                {1, 2, 3},
                {4, 6, 0},
                {7, 8, 5},
        };
        Board board = new Board(boardBlocks);
        List<Board> neighbors = new ArrayList<>();
        neighbors.add(new Board(topBlocks));
        neighbors.add(new Board(bottomBlocks));
        neighbors.add(new Board(leftBlocks));
        neighbors.add(new Board(rightBlocks));
        assertTrue(neighbors.equals(board.neighbors()));
    }

}
