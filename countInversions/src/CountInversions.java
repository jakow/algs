import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by jakub on 16/09/2017.
 */
public class CountInversions {
    public static long countInversions(int[] array) {
        int[] copy = Arrays.copyOf(array, array.length);
        return sortAndCount(copy, 0, array.length, new int[array.length]);
    }

    public static long sortAndCount(int[] arr, int left, int right, int[] aux) {
        if (right - left == 1) {
            return 0;
        }
        int mid = (left + right) / 2;
        long leftInversions = sortAndCount(arr, left, mid, aux);
        long rightInversions = sortAndCount(arr, mid, right, aux);
        long splitInversions = mergeAndCount(arr, left, mid, right, aux);
        return leftInversions + rightInversions + splitInversions;

    }

    public static long mergeAndCount(int[] arr, int left, int mid, int right, int aux[]) {
        long inversionCount = 0;
        int i = left, j = mid, k = left;
        while (k < right) {
            if (i < mid && j < right) {
                if (arr[i] <= arr[j]) {
                    aux[k++] = arr[i++];
                } else {
                    // number of elements left in left array
                    inversionCount += mid - i;
                    aux[k++] = arr[j++];
                }
            } else if (i < mid) { // no right elems left
                aux[k++] = arr[i++];
            } else { // no left elems left
                aux[k++] = arr[j++];
            }
        }

        // copy back
        for (i = left; i < right; ++i) {
            arr[i] = aux[i];
        }
        return inversionCount;
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length, new int[arr.length]);
    }

    private static void mergeSort(int[] arr, int left, int right, int[] aux) {
        if (right - left == 1) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSort(arr, left, mid, aux);
        mergeSort(arr, mid, right, aux);
        merge(arr, left, mid, right, aux);
    }

    public static void merge(int[] arr, int left, int mid, int right, int[] aux) {
        int i = left, j = mid, k = left;
        while (k < right) {
            if (i < mid && j < right) {
                if (arr[i] <= arr[j]) {
                    aux[k++] = arr[i++];
                } else {
                    aux[k++] = arr[j++];
                }
            } else if (i < mid) {
                // right list exhausted
                aux[k++] = arr[i++];
            } else {
                aux[k++] = arr[j++];
            }
        }

        // copy back from aux array to sorted array;
        for (int l = left; l < right; ++l) {
            arr[l] = aux[l];
        }



    }

    public static void main(String[] args) {
        System.out.println("Testing count inversions");
        // inversions
        int[] arr = new int[] {1,3,5,2,4,6, 0};
        System.out.println(Arrays.toString(arr));
        System.out.println(countInversions(arr));

        File f = new File("data/IntegerArray.txt");
        List<Integer> integers = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(f);
            while (scanner.hasNextInt()) {
                integers.add(scanner.nextInt());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not readable");
            System.exit(1);
        }
        Integer[] integerArray = integers.toArray(new Integer[integers.size()]);
        int[] intArray = new int[integerArray.length];
        for (int i = 0; i < integerArray.length; ++i) {
            intArray[i] = integerArray[i];
        }

        System.out.println(countInversions(intArray));


    }
}
