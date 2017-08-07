import static java.lang.System.out;
//import static java.lang.System.in;
import java.text.NumberFormat;
import java.text.DecimalFormat;
public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        out.println("Union find");
        int N = StdIn.readInt();
        UnionFind uf = new QuickUnionUF(N);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if(!uf.connected(p,q)) {
                uf.union(p,q);
//                StdOut.println(p + " " + q);
            }
        }
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        NumberFormat formatter = new DecimalFormat("#0.00000");
        System.out.println("Running time: " + totalTime/1000d + " seconds");
    }
}
