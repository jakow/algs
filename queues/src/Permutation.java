import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by jakub on 04/02/2017.
 */
public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        while(!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        for (; k > 0; --k) {
            StdOut.println(rq.dequeue());
        }

    }
}
