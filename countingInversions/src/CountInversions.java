import java.util.Comparator;

/**
 * Created by jakub on 09/02/2017.
 */
public class CountInversions {
    public static int countingMerge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {

        int count = 0;
        // copy array
        for (int i = 0; i < a.length; ++i) {
            aux[i] = a[i];
        }
        int i = lo;
        int j = mid+1;

        for (int k = lo; k <= hi; ++k ) {
            if (i > mid) // i is exhausted
                a[k] = aux[j++]; // so no inversions to count
            else if (j > hi) // j is exhausted, all inversions counted
                a[k] = aux[i++];
            else if (aux[j].compareTo(aux[i]) < 0) {
                count += mid + 1 - i; // MOST IMPORTANT LINE
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
        return count;
    }
    public static int countingSort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        int mid = (lo+hi) / 2;
        int count = 0;
        if (hi == lo) return count;
        count += countingSort(a, aux, lo, mid);
        count += countingSort(a, aux, mid+1, hi);
        count += countingMerge(a, aux, lo, mid, hi);
        return count;
    }

    public static int countInversions(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        return countingSort(a, aux, 0, a.length-1);
    }

}
