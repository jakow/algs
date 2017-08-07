/**
 * Created by jakub on 09/02/2017.
 * Selection in two sorted arrays.
 * Given two sorted arrays a[] and b[], of sizes n1 and n2, respectively, design an algorithm to find
 * the kth largest key. The order of growth of the worst case running time of your algorithm
 * should be log n, where n=n1+n2.
 * Version 1: n1=n2 and k=n/2
 * Version 2: k=n/2
 * Version 3: no restrictions
 */
public class Selection {
    public boolean isKth(int[] a, int[] b, int k, int i) {
        // for given i and a[i], there are i larger elements in i.
        // So, there must be exactly k-i-1 larger elements in b
        if (i > k-1) return false;



    }
    public int kthLargest(int[] a, int[] b, int k) {
        return  kthLargest(a, 0, a.length, b, 0, b.length);
    }

    public int kthLargest(int[] a, int aLo, int aHi, int[] b, int bLo, int bHi) {

    }
}
