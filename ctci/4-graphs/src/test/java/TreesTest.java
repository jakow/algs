import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class TreesTest {
    @Test
    public void isBalancedForNullTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {});
        Assert.assertEquals(true, Trees.isBalanced(root));
    }

    @Test
    public void isBalancedForLeaf() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 1 });
        Assert.assertEquals(true, Trees.isBalanced(root));
    }

    @Test
    public void isBalancedForPerfectTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {1,2,3,4,5,6,7});
        Assert.assertEquals(true, Trees.isBalanced(root));
    }

    @Test
    public void isBalancedForBalancedTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {1,2,3,4,5});
        Assert.assertEquals(true, Trees.isBalanced(root));
    }

    @Test
    public void isBalancedForUnbalancedTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 1, 2, null, 4, 5});
        Assert.assertEquals(false, Trees.isBalanced(root));
    }

    @Test
    public void isBinaryForNull() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {});
        Assert.assertEquals(true, Trees.isBinary(root));

    }

    @Test
    public void isBinaryForLeaf() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {1});
        Assert.assertEquals(true, Trees.isBinary(root));
    }

    @Test
    public void isBinaryForRootEqualToLeft() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {2, 2});
        Assert.assertEquals(true, Trees.isBinary(root));
    }

    @Test
    public void isBinaryForRootEqualToRight() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {2, null, 2});
        Assert.assertEquals(false, Trees.isBinary(root));
    }

    @Test
    public void isBinaryForLargeBinaryTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {6,4,8,3,5,7,10,2,null,null,null,null,null,9,11});
        Assert.assertEquals(true, Trees.isBinary(root));
    }

    @Test
    public void isBinaryForLargeNonBinaryTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {6,4,8,3,5,7,10,2,null,null,null,null,null,9,1});
        Assert.assertEquals(false, Trees.isBinary(root));
    }

    @Test
    public void successorTest1() {
        TreeNode<Integer> root = Trees.fromSortedArray(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        UndirectedTreeNode<Integer> undirectedRoot = Trees.undirectedTreeFromDirectedTree(root);
        Assert.assertEquals(Integer.valueOf(8), Trees.successor(undirectedRoot, 7).val);
    }

    @Test
    public void successorTest2() {
        TreeNode<Integer> root = Trees.fromSortedArray(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        UndirectedTreeNode<Integer> undirectedRoot = Trees.undirectedTreeFromDirectedTree(root);
        Assert.assertEquals(Integer.valueOf(6), Trees.successor(undirectedRoot, 5).val);
    }

    @Test
    public void successorTest3() {
        TreeNode<Integer> root = Trees.fromSortedArray(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        UndirectedTreeNode<Integer> undirectedRoot = Trees.undirectedTreeFromDirectedTree(root);
        Assert.assertEquals(Integer.valueOf(12), Trees.successor(undirectedRoot, 11).val);
    }

    @Test
    public void successorTest4() {
        TreeNode<Integer> root = Trees.fromSortedArray(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        UndirectedTreeNode<Integer> undirectedRoot = Trees.undirectedTreeFromDirectedTree(root);
        Assert.assertEquals(Integer.valueOf(15), Trees.successor(undirectedRoot, 14).val);
    }

    @Test(expected = NoSuchElementException.class)
    public void successorTest5() {
        TreeNode<Integer> root = Trees.fromSortedArray(new Integer[] {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16});
        UndirectedTreeNode<Integer> undirectedRoot = Trees.undirectedTreeFromDirectedTree(root);
        Trees.successor(undirectedRoot, 16);
    }

    @Test
    public void commonAncestorTestDifferentSubtrees() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {6,4,8,3,5,7,10,2,1,4,7,6,12,13,14,5,9,1});
        TreeNode<Integer> child1 = root.left.left.right;
        TreeNode<Integer> child2 = root.left.right.left;
        Assert.assertEquals(root.left, Trees.firstCommonAncestor(root, child1, child2));
    }

    @Test
    public void commonAncestorTestSameSubtree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {6,4,8,3,5,7,10,2,1,4,7,6,12,13,14,5,9,1});
        TreeNode<Integer> child1 = root.left.left;
        TreeNode<Integer> child2 = root.left.left.left.left;
        Assert.assertEquals(root.left.left, Trees.firstCommonAncestor(root, child1, child2));

    }

    @Test
    public void commonAncestorTestNullEmpty() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {});
        TreeNode<Integer> in1 = new TreeNode<>(4);
        TreeNode<Integer> in2 = new TreeNode<>(8);
        Assert.assertNull(Trees.firstCommonAncestor(root, in1, in2));
    }

    @Test
    public void commonAncestorTestRootOfTree() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] {6,4,8,3,5,7,10,2,1,4,7,6,12,13,14,5,9,1});
        TreeNode<Integer> child1 = root.right.left.right;
        TreeNode<Integer> child2 = root.left.left.left.left;
        Assert.assertEquals(root, Trees.firstCommonAncestor(root, child1, child2));
    }

    @Test
    public void commonAncestorTestForest() {
        TreeNode<Integer> tree1 = Trees.fromArray(new Integer[] {6,4,8,3,5,7,10,2,1,4,7,6,12,13,14,5,9,1});
        TreeNode<Integer> tree2 = Trees.fromArray(new Integer[] {44,44,128,133,54,75,5106,27,11,42,37,65,132,1123,2134,35,9,1});
        TreeNode<Integer> child1 = tree1.right.left.right;
        TreeNode<Integer> child2 = tree2.left.left.left.left;
        Assert.assertNull(Trees.firstCommonAncestor(tree1, child1, child2));
    }

    @Test
    public void bstSequencesTest1() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 4, 2, 6, 1 });
        System.out.println(Trees.bstSequences(root));
    }

//    @Test
//    public void bstSequencesTest2() {
//        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 8, 4, 12, 2, 6, 10, 14 });
//
//    }

    @Test
    public void bstSequencesAssertUnique() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9 });
        Set<List<Integer>> resultSet = new HashSet<>();
        List<List<Integer>> resultList = Trees.bstSequences(root);
        resultSet.addAll(resultList);
        Assert.assertTrue(resultList.size() == resultSet.size());
    }


    @Test
    public void isSubtreeTest1() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 8, 4, 12, null, 6, 10, 14, null, null, 5, 7, 9, 11, 13, 15 });
        TreeNode<Integer> subtr = Trees.fromArray(new Integer[] { 4, null, 6, null, null, 5, 7 });
        Assert.assertTrue(Trees.isSubtree(root, subtr));
    }

    @Test
    public void isSubtreeTest2() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 8, 4, 12, null, 6, 12, 14, null, null, 5, 7, 9, 11, 13, 15 });
        TreeNode<Integer> subtr = Trees.fromArray(new Integer[] { 12, 12, 14,9,11,13,15 });
        Assert.assertTrue(Trees.isSubtree(root, subtr));
    }

    @Test
    public void isNotSubtreeTest1() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 8, 4, 12, null, 6, 10, 14, null, null, 5, 7, 9, 11, 13, 15 });
        TreeNode<Integer> subtr = Trees.fromArray(new Integer[] { 4, null, 6 });
        Assert.assertFalse(Trees.isSubtree(root, subtr));

    }

    @Test
    public void isNotSubtreeTest2() {
        TreeNode<Integer> root = Trees.fromArray(new Integer[] { 3, null, 4 });
        TreeNode<Integer> subtr = Trees.fromArray(new Integer[] { 3, 4 });
        Assert.assertFalse(Trees.isSubtree(root, subtr));

    }
}
