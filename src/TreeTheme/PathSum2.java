package TreeTheme;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-03-15 22:12
 * @description :
 * 面试题34. 二叉树中和为某一值的路径
 *
 * 输入一棵二叉树和一个整数，打印出二叉树中节点值的和为输入整数的所有路径。
 * 从树的根节点开始往下一直到叶节点所经过的节点形成一条路径。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 target = 22，
 *
 *               5
 *              / \
 *             4   8
 *            /   / \
 *           11  13  4
 *          /  \    / \
 *         7    2  5   1
 * 返回:
 *
 * [
 *    [5,4,11,2],
 *    [5,8,4,5]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class PathSum2 {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> results = new LinkedList<List<Integer>>();
        if(root==null) return results;
        process(root, target, results, new LinkedList<Integer>(), 0);
        return results;
    }

    public void process(TreeNode root, int target, List<List<Integer>> results,
                        List<Integer> re, int sum){
        if(root.left==null && root.right==null && target==root.val+sum){
            re.add(root.val);
            results.add(new LinkedList<>(re));
            re.remove(re.size()-1);
            return;
        }

        if (null!=root.left) {
            re.add(root.val);
            process(root.left, target, results, re, sum + root.val);
            re.remove(re.size()-1);
        }
        if (null!=root.right) {
            re.add(root.val);
            process(root.right, target, results, re, sum + root.val);
            re.remove(re.size()-1);
        }
    }
}
