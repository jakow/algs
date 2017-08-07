import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

public class Solver {
    // two solution trees, one for a root
    private Node solutionA;
    private Node solutionB;
    private MinPQ<Node> treeA;
    private MinPQ<Node> treeB;
    private boolean solvable;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new NullPointerException("The starting board must not be null");
        }

        treeA = new MinPQ<>();
        treeB = new MinPQ<>();
        Node initialNodeA = new Node(initial, null, 0);
        Node initialNodeB = new Node(initial.twin(), null, 0);
        treeA.insert(initialNodeA);
        treeB.insert(initialNodeB);
        do {
            solutionA = step(treeA);
            solutionB = step(treeB);
        } while(solutionA == null && solutionB == null); // at least one succeeds
        solvable = solutionA != null;
        // recycle the trees after running the algorithms
        treeA = null;
        treeB = null;
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        // known at construction time when the B tree finds a solution
        return solvable;
    }
    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return solutionA != null ? solutionA.moves : -1;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        if (solutionA == null) {
            return null;
        }
        // moves are placed on a stack since they are inserted in reverse order, i.e. walking back through the solution
        Stack<Board> s = new Stack<>();
        Node node = solutionA;
        do {
            s.push(node.board);
            node = node.parent;
        }
        while (node != null);
        return s;
    }

    private Node step(MinPQ<Node> tree) {
        Node node = tree.delMin();
        Board parentBoard = node.parent != null ? node.parent.board : null;
        boolean foundParent = false; // avoid recomputing .equals() since there is only one parent
        if (node.board.isGoal()) {
            return node;
        } else {
            for (Board b : node.board.neighbors()) {
                if (!foundParent && b.equals(parentBoard)) {
                    foundParent = true;
                } else {
                    tree.insert(new Node(b, node, node.moves+1));
                }
            }
        }
        return null;
    }

    private class Node implements Comparable<Node> {
        private Node parent; // need to have a reference to the parent somehow
        private Board board;
        private int moves;

        Node(Board board, Node parent, int moves) {
            this.board = board;
            this.moves = moves;
            this.parent = parent;
        }

        int priority() {
            return board.manhattan() + moves;
        }

        @Override
        public int compareTo(Node o) {
            return this.priority() - o.priority();
        }
    }
}
