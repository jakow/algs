import org.junit.Assert;
import org.junit.Test;

import java.util.NoSuchElementException;

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

}
