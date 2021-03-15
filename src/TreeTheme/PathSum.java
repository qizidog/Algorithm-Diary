package TreeTheme;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : qizidog
 * @date : 2021-02-03 14:06
 * @description :
 * 面试题 04.12. 求和路径
 * 给定一棵二叉树，其中每个节点都含有一个整数数值(该值或正或负)。
 * 设计一个算法，打印节点数值总和等于某个给定值的所有路径的数量。
 * 注意，路径不一定非得从二叉树的根节点或叶节点开始或结束，但是其方向必须向下(只能从父节点指向子节点方向)。
 *
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
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
 * 3
 * 解释：和为 22 的路径有：[5,4,11,2], [5,8,4,5], [4,11,7]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/paths-with-sum-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class PathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int count = 0;
    private ArrayList<ArrayList<Integer>> list = new ArrayList<>();

    public int pathSum(TreeNode root, int sum) {
        if(root==null) return 0;
        proceed(root, sum, 0, new ArrayList<Integer>());
        // 以左右子树作为根节点递归调用
        pathSum(root.left, sum);
        pathSum(root.right, sum);
        return count;
    }

    public void proceed(TreeNode root, int sum, int curSum, ArrayList<Integer> arr){
        curSum = root.val+curSum;
        arr.add(root.val);
        if(curSum==sum){
            count++;
            list.add(new ArrayList<Integer>(arr));
        }
        // 等于的时候还可以往下找
        if(root.left!=null)
            proceed(root.left, sum, curSum, arr);
        if(root.right!=null)
            proceed(root.right, sum, curSum, arr);

        // 不能这样调用，会重复计算
        // if(root.left!=null)
        //     proceed(root.left, sum, 0, new ArrayList<Integer>());
        // if(root.right!=null)
        //     proceed(root.right, sum, 0, new ArrayList<Integer>());
    }

    public static void main(String[] args) {
        // [1,null,2,null,3,null,4,null,5]
        // 3
        TreeNode head = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        head.right = node2;
        node2.right = node3;
        node3.right = node4;
        node4.right = node5;

        PathSum ps = new PathSum();
        System.out.println(ps.pathSum(head, 3));
        System.out.println(ps.list);
    }


    /**
     * 通过前缀和来实现
     * @param root 树的根节点
     * @param sum 指定个求和值
     * @return 满足指定求和值的组合个数
     */
    //todo 学习该解法
    public int pathSum2(TreeNode root, int sum) {
        // key是前缀和, value是大小为key的前缀和出现的次数
        Map<Integer, Integer> prefixSumCount = new HashMap<>();
        // 前缀和为0的一条路径
        prefixSumCount.put(0, 1);
        // 前缀和的递归回溯思路
        return recursionPathSum(root, prefixSumCount, sum, 0);
    }

    /**
     * 前缀和的递归回溯思路
     * 从当前节点反推到根节点(反推比较好理解，正向其实也只有一条)，有且仅有一条路径，因为这是一棵树
     * 如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
     * 所以前缀和对于当前路径来说是唯一的，当前记录的前缀和，在回溯结束，回到本层时去除，保证其不影响其他分支的结果
     * @param node 树节点
     * @param prefixSumCount 前缀和Map
     * @param target 目标值
     * @param currSum 当前路径和
     * @return 满足题意的解
     */
    private int recursionPathSum(TreeNode node, Map<Integer, Integer> prefixSumCount, int target, int currSum) {
        // 1.递归终止条件
        if (node == null) {
            return 0;
        }
        // 2.本层要做的事情
        int res = 0;
        // 当前路径上的和
        currSum += node.val;

        //---核心代码
        // 看看root到当前节点这条路上是否存在节点前缀和加target为currSum的路径
        // 当前节点->root节点反推，有且仅有一条路径，如果此前有和为currSum-target,而当前的和又为currSum,两者的差就肯定为target了
        // currSum-target相当于找路径的起点，起点的sum+target=currSum，当前点到起点的距离就是target
        res += prefixSumCount.getOrDefault(currSum - target, 0);
        // 更新路径上当前节点前缀和的个数
        prefixSumCount.put(currSum, prefixSumCount.getOrDefault(currSum, 0) + 1);
        //---核心代码

        // 3.进入下一层
        res += recursionPathSum(node.left, prefixSumCount, target, currSum);
        res += recursionPathSum(node.right, prefixSumCount, target, currSum);

        // 4.回到本层，恢复状态，去除当前节点的前缀和数量
        prefixSumCount.put(currSum, prefixSumCount.get(currSum) - 1);
        return res;
    }
}
