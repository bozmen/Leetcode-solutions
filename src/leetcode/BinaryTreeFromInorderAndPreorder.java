package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * Created by burak on 11/27/2016.
 */
public class BinaryTreeFromInorderAndPreorder {
    Map<Integer, Integer> preorderHash = new HashMap<>();
    Map<Integer, Integer> inorderHash = new HashMap<>();
    Map<Integer, TreeNode> nodeHash = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < preorder.length; i++) {
            preorderHash.put(preorder[i], i);
            inorderHash.put(inorder[i], i);
        }
        TreeNode root = new TreeNode(preorder[0]);
        nodeHash.put(preorder[0], root);
        TreeNode curNode;
        for (int i = 0; i < preorder.length; i++) {
            int curValueI = preorder[i];
            curNode = checkAndGet(curValueI);
            boolean rightFound = false;
            boolean leftFound = false;
            for (int j = i + 1; j < preorder.length; j++) {
                int curValueJ = preorder[j];
                if (rightFound && leftFound) {
                    continue;
                }
                if (!rightFound && compareIndex(curValueI, curValueJ) < 0) {
                    rightFound = true;
                    curNode.right = checkAndGet(curValueJ);
                }
                if (!leftFound && compareIndex(curValueI, curValueJ) > 0) {
                    leftFound = true;
                    curNode.left = checkAndGet(curValueJ);
                }
            }
        }
        return root;
    }

    private TreeNode checkAndGet(int value) {
        TreeNode node;
        if (nodeHash.containsKey(value)) {
            node = nodeHash.get(value);
        } else {
            node = new TreeNode(value);
            nodeHash.put(value, node);
        }
        return node;
    }

    public static void main(String[] args) {
        BinaryTreeFromInorderAndPreorder b = new BinaryTreeFromInorderAndPreorder();
        int[] preorder = {2,1,4,7,3,8,6};
        int[] inorder  = {4,1,7,2,8,3,6};
        TreeNode root = b.buildTree(preorder, inorder);
    }
    
    public int compareIndex(int firstVal, int secondVal) {
        int firstIndex = inorderHash.get(firstVal);
        int secondIndex = inorderHash.get(secondVal);
        if (firstIndex < secondIndex) {
            return -1;
        } else {
            return 1;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
