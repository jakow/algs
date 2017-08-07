/**
 * Created by jakub on 23/01/2017.
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int n;
    private boolean[] opened; // node opened
    private boolean[] connectedToTop; // root connected to top?
    private boolean[] connectedToBottom; // root connected to bottom?
    private int openCount;
    private boolean percolatesFlag;

    public Percolation(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("<= 0 grid size");
        }
        n = size;
        openCount = 0;
        percolatesFlag = false;

        uf = new WeightedQuickUnionUF(n * n);
        opened = new boolean[n * n]; // track open sites
        connectedToTop = new boolean[n * n]; // track ROOTS connected to the top
        connectedToBottom = new boolean[n * n]; // track ROOTS connected to the bottom

        for (int i = 0; i < n * n; ++i) {
            opened[i] = false;
            if (i < n) { // is first row?
                connectedToTop[i] = true;
            } else {
                connectedToTop[i] = false;
            }

            if (i >= n * (n - 1)) { // is last row?
                connectedToBottom[i] = true;
            } else {
                connectedToBottom[i] = false;
            }

        }
//        System.out.println(Arrays.toString(connectedToTop));
//        System.out.println(Arrays.toString(connectedToBottom));

    }

    public void open(int row, int col) {
        validate(row, col); // technically this is not needed - isOpen also calls validate
        // don't do the work if already open
        if (isOpen(row, col)) {
            return;
        }
        // get and update the opened status of the cell
        int index = toIndex(row, col);
        opened[index] = true; // avoid calling find in isOpen later on
        openCount++;
        // make a union with the 4 neighbors if they are open
        int[] otherRow = {row, row, row - 1, row + 1}; // left, right, top, bottom
        int[] otherCol = {col - 1, col + 1, col, col};
        int otherIndex;
        boolean ctb = connectedToBottom[index]; // is the newly opened cell connected to top?
        boolean ctt = connectedToTop[index]; // is the newly opened cell connected to bottom?
        for (int i = 0; i < otherRow.length; ++i) {
            if (!isValid(otherRow[i], otherCol[i])) {
                continue;
            }
            if (isOpen(otherRow[i], otherCol[i])) {
                otherIndex = uf.find(toIndex(otherRow[i], otherCol[i]));
                ctt |= connectedToTop[otherIndex]; // are other open cells connected to top?
                ctb |= connectedToBottom[otherIndex]; // are other open cells connected to bottom?
                uf.union(toIndex(row, col), otherIndex);
            }
        }
        // finally, update the status of the root
        index = uf.find(index); // find the index of root
        connectedToTop[index] |= ctt;
        connectedToBottom[index] |= ctb;
        if (connectedToBottom[index] && connectedToTop[index]) {
            percolatesFlag = true;
        }
//        System.out.println("row, col: ("+ row + "," + col +"), root:" + index +
//                ", isOpen: " + isOpen(row,col) +
//                ", ctt: " + connectedToTop[index] +
//                ", ctb: " + connectedToBottom[index]);


    }

    public boolean isOpen(int row, int col) {
        validate(row, col);
        return opened[toIndex(row, col)];
    }

    public boolean isFull(int row, int col) {
        validate(row, col);
        int idx = uf.find(toIndex(row, col));
        return isOpen(row, col) && connectedToTop[idx];
    }


    public int numberOfOpenSites() {
        return openCount;
    }

    public boolean percolates() {
        return percolatesFlag;
    }

    private boolean isValid(int row, int col) {
        // non-throwing validator for internal use
        return row > 0 && row <= n && col > 0 && col <= n;
    }

    private void validate(int row, int col) {
        // throwing validator for public methods
        if (row <= 0 || col <= 0 || row > n || col > n) {
            throw new IndexOutOfBoundsException("cell address is out of bounds of" + n + "-by-" + n + " grid");
        }
    }

    private int toIndex(int row, int col) {
        return (row - 1) * n + col - 1;
    }
}
