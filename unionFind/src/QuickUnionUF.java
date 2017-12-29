/**
 * Created by jakub on 22/01/2017.
 */
public class QuickUnionUF implements UnionFind {
    private int[] parent;
    private int[] size;

    public QuickUnionUF(int N) {
        parent = new int[N];
        size = new int[N];
        for (int i = 0; i < N; ++i) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public void union(int p, int q) {
        // new root of P is the root of Q
        int pRoot = root(p);
        int qRoot = root(q);
        if (pRoot == qRoot) return;
        if (size[pRoot] < size[qRoot]) {
            parent[pRoot] = qRoot;
            size[qRoot] += size[pRoot];
        } else {
            parent[qRoot] = pRoot;
            size[pRoot] += size[qRoot];
        }
        parent[root(p)] = root(q);
    }

    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }
    private int root(int p) {
        while (p != parent[p]) {
            // path compression - after getting the root of a node, set the root of visited node to its parent
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }
}
