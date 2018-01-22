import java.util.NoSuchElementException;

/**
 * Created by jakub on 20/01/2018.
 */
public class Trees {
    public static <T> TreeNode<T> fromArray(T[] arr) {
        return buildFromArray(arr, 0);
    }

    public static <T extends Comparable<T>> TreeNode<T> fromSortedArray(T[] arr) {
        if (!isSorted(arr)) {
            throw new IllegalArgumentException("Array not sorted");
        }
        return fromSortedBuilder(arr, 0, arr.length - 1);
    }

    public static boolean isBalanced(TreeNode t) {
        return checkHeight(t) != Integer.MIN_VALUE;
    }

    public static <T extends Comparable<T>> boolean isBinary(TreeNode<T> t) {
        if (t == null) {
            return true;
        }
        return checkIsBinary(t) != null;
    }


    public static <T extends Comparable<T>> UndirectedTreeNode<T> successor(UndirectedTreeNode<T> root, T val) {
        UndirectedTreeNode<T> curr = (UndirectedTreeNode<T>) find(root, val);
        return successor(curr);
    }

    public static <T extends Comparable<T>> UndirectedTreeNode<T> successor(UndirectedTreeNode<T> curr) {
        if (curr.right != null) {
            return (UndirectedTreeNode<T>) min(curr.right);
        }
        UndirectedTreeNode<T> succ = curr.parent;
        while (succ != null && succ.val.compareTo(curr.val) < 0) {
            succ = succ.parent;
        }
        if (succ == null) {
            throw new NoSuchElementException("no successor found");
        }
        return succ;
    }

    public static <T> UndirectedTreeNode<T> undirectedTreeFromDirectedTree(TreeNode<T> root) {
        return undirectedTreeFromDirectedTree(root, null);
    }

    /* implementation details */

    private static <T> UndirectedTreeNode<T> undirectedTreeFromDirectedTree(TreeNode<T> root, UndirectedTreeNode<T> parent) {
        if (root == null) {
            return null;
        }
        UndirectedTreeNode<T> undirectedRoot = new UndirectedTreeNode<>(root.val);
        undirectedRoot.parent = parent;
        undirectedRoot.left = undirectedTreeFromDirectedTree(root.left, undirectedRoot);
        undirectedRoot.right = undirectedTreeFromDirectedTree(root.right, undirectedRoot);
        return undirectedRoot;
    }




    private static <T extends Comparable<T>> TreeNode<T> min(TreeNode<T> root) {
        TreeNode<T> curr = root;
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
    }

    private static <T extends Comparable<T>> TreeNode<T> find(TreeNode<T> root, T val) {
        TreeNode<T> curr = root;
        while (curr != null) {
            if (val.compareTo(curr.val) < 0) {
                curr = curr.left;
            } else if (val.compareTo(curr.val) > 0) {
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }



    private static <T extends Comparable<T>> IsBinaryResult<T> checkIsBinary(TreeNode<T> t) {
        IsBinaryResult<T> result = new IsBinaryResult<>();
        result.min = t.val;
        result.max = t.val;
        if (t.left != null) {
            IsBinaryResult<T> left = checkIsBinary(t.left);
            if (left == null || left.max.compareTo(t.val) > 0) {
                return null;
            }
            result.min = left.min;
        }
        if (t.right != null) {
            IsBinaryResult<T> right = checkIsBinary(t.right);
            if (right == null || right.min.compareTo(t.val) <= 0) {
                return null;
            }
            result.max = right.max;
        }
        return result;
    }

    private static int checkHeight(TreeNode t) {
        if (t == null) {
            return 0;
        }
        int leftHeight = checkHeight(t.left);
        if (leftHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        int rightHeight = checkHeight(t.right);
        if (rightHeight == Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        if (Math.abs(rightHeight - leftHeight) > 1) {
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    private static <T extends Comparable<T>> TreeNode<T> fromSortedBuilder(T[] arr, int start, int end) {
        if (end < start) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode<T> root = new TreeNode<>(arr[mid]);
        root.left = fromSortedBuilder(arr, start, mid - 1);
        root.right = fromSortedBuilder(arr, mid + 1, end);
        return root;
    }

    private static <T extends Comparable<T>> boolean isSorted(T[] arr) {
        if (arr.length < 1) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; ++i) {
            if (arr[i].compareTo(arr[i + 1]) > 0) {
                return false;
            }
        }
        return true;
    }

    private static <T> TreeNode<T> buildFromArray(T[] arr, int idx) {
        if (arr.length <= idx) {
            return null;
        }
        if (arr[idx] == null) {
            return null;
        }
        TreeNode<T> root = new TreeNode<>(arr[idx]);
        root.left = buildFromArray(arr, leftChild(idx));
        root.right = buildFromArray(arr, leftChild(idx) + 1);
        return root;
    }

    private static int leftChild(int idx) {
        return 2 * idx + 1;
    }


    private static class IsBinaryResult<T extends Comparable<T>> {
        T min;
        T max;
    }
}
