import java.util.*;

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

    public static <T> TreeNode<T> firstCommonAncestor(TreeNode<T> root, TreeNode<T> p, TreeNode<T> q) {
        if (root == null || p == root || q == root) {
            return root;
        }
        CommonAncestorResult<T> result = firstCommonAncestorHelper(root, p, q);
        if (result.isAncestor) {
            return result.node;
        }
        return null;
    }

    public static <T extends Comparable<T>> List<List<T>> bstSequences(TreeNode<T> root) {
        List<List<T>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        if (root.left == null && root.right == null) {
            // return the leaf list itself
            List<T> l = new LinkedList<>();
            l.add(root.val);
            result.add(l);
            return result;
        }

        List<List<T>> leftSequences = bstSequences(root.left);
        List<List<T>> rightSequences = bstSequences(root.right);
        if (!leftSequences.isEmpty() && !rightSequences.isEmpty()) {
            List<List<T>> interleaved = interleave(leftSequences, rightSequences);
            for (List<T> l : interleaved) {
                l.add(0, root.val);
            }
            return interleaved;
        } else if (!leftSequences.isEmpty()) {
            for (List<T> l : leftSequences) {
                l.add(0, root.val);
            }
            return leftSequences;
        } else {
            for (List<T> l : rightSequences) {
                l.add(0, root.val);
            }
            return leftSequences;
        }
    }

    private static <T extends Comparable<T>> List<List<T>> interleave(List<List<T>> leftSequences, List<List<T>> rightSequences) {
        List<List<T>> result = new ArrayList<>();
        for (List<T> left : leftSequences) {
            for (List<T> right : rightSequences) {
                Deque<T> current = new ArrayDeque<>();
                List<List<T>> interleavings = new ArrayList<>();
                interleaveLists(left, 0, right, 0, current, interleavings);
                result.addAll(interleavings);
            }
        }
        return result;
    }

    private static <T extends Comparable<T>> void interleaveLists(List<T> left, int leftIdx, List<T> right, int rightIdx, Deque<T> current, List<List<T>> interleavings) {
        if (leftIdx == left.size() && rightIdx == right.size()) {
            interleavings.add(new ArrayList<>(current));
            return;
        }

        if (leftIdx != left.size()) {
            current.addLast(left.get(leftIdx));
            interleaveLists(left, leftIdx + 1, right, rightIdx, current, interleavings);
            current.removeLast();
        }

        if (rightIdx != right.size()) {
            current.addLast(right.get(rightIdx));
            interleaveLists(left, leftIdx, right, rightIdx + 1, current, interleavings);
            current.removeLast();
        }
    }

    public static <T extends Comparable<T>> boolean isSubtree(TreeNode<T> t1, TreeNode<T> t2) {
        List<T> l1 = preOrderTraversalWithNulls(t1);
        List<T> l2 = preOrderTraversalWithNulls(t2);

        return Utils.find(l1, l2) != -1;
    }

    private static <T extends Comparable<T>> List<T> preOrderTraversalWithNulls(TreeNode<T> node) {
        List<T> result = new ArrayList<>();
        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(node);
        while (!stack.isEmpty()) {
            node = stack.removeLast();
            if (node == null) {
                result.add(null);
            } else {
                result.add(node.val);
                stack.addLast(node.left);
                stack.addLast(node.right);
            }
        }
        return result;
    }


    private static <T extends Comparable<T>> List<T> preOrderTraversal(TreeNode<T> node) {
        List<T> result = new ArrayList<>();
        if (node == null) {
            return result;
        }
        Deque<TreeNode<T>> stack = new ArrayDeque<>();
        stack.addLast(node);
        while (!stack.isEmpty()) {
            node = stack.removeLast();
            result.add(node.val);
            if (node.left != null) {
                stack.addLast(node.left);
            }
            if (node.right != null) {
                stack.addLast(node.right);
            }
        }
        return result;
    }


    /* implementation details */

    private static <T> CommonAncestorResult<T> firstCommonAncestorHelper(TreeNode<T> root, TreeNode<T> p, TreeNode<T> q) {
        if (root == null) {
            return new CommonAncestorResult<>(false,null);
        }
        if (root == p && root == q) {
            return new CommonAncestorResult<>(true, root);
        }

        CommonAncestorResult<T> resL = firstCommonAncestorHelper(root.left, p, q);
        if (resL.isAncestor) {
            return resL;
        }

        CommonAncestorResult<T> resR = firstCommonAncestorHelper(root.right, p, q);
        if (resR.isAncestor) {
            return resR;
        }

        if (resL.node != null && resR.node != null) {
            // this is the ancestor
            return new CommonAncestorResult<>(true, root);
        } else if (root == p || root == q) {
            boolean isAncestor = resL.node != null || resR.node != null;
            return new CommonAncestorResult<>(isAncestor, root);
        } else {
            return new CommonAncestorResult<>(false, resL.node != null ? resL.node : resR.node);
        }

    }

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

    private static class CommonAncestorResult<T> {
        boolean isAncestor;
        TreeNode<T> node;

        CommonAncestorResult(boolean isAnc, TreeNode<T> n) {
            isAncestor = isAnc;
            node = n;
        }
    }
}
