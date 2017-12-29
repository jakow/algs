import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        System.out.println("Union find");
        Scanner s;
        try {
            s = new Scanner(new File("inFiles/largeUF.txt"));
        } catch (IOException e) {
            return;
        }
        UnionFind uf = new QuickUnionUF(s.nextInt());
        while(s.hasNextInt()) {
            int p = s.nextInt();
            int q = s.nextInt();
            uf.union(p,q);
        }
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Running time: " + totalTime/1000d + " seconds");
    }
}
