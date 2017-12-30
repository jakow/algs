/**
 * Created by jakub on 29/12/2017.
 */
public class UnionFind {
    private int[] parent;
    private int[] rank;
    private int n;

    public UnionFind(int size) {
        n = size;
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; ++i) {
            parent[i] = i;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
            // path compression - after getting the root of a node, set the root of visited node to its parent
            parent[element] = parent[parent[element]];
            element = parent[element];
        }
        return element;
    }

    public void union(int p, int q) {
        int pParent = find(p);
        int qParent = find(q);
        if (pParent == qParent) {
            return;
        }
        if (rank[pParent] != rank[qParent]) {
            if (rank[pParent] > rank[qParent]) {
              parent[qParent] = pParent;
            } else {
                parent[pParent] = qParent;
            }
        } else {
            parent[qParent] = pParent;
            rank[pParent] += 1;
        }
        n -= 1;
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    public int count() {
        return n;
    }
}
