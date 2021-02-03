package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-03 17:06
 * @description :
 * 面试题 04.10. 检查子树 & 剑指 Offer 26. 树的子结构（略有区别）
 * 检查子树。你有两棵非常大的二叉树：T1，有几万个节点；T2，有几万个节点。设计一个算法，判断 T2 是否为 T1 的子树。
 *
 * 如果 T1 有这么一个节点 n，其子树与 T2 一模一样，则 T2 为 T1 的子树，也就是说，从节点 n 处把树砍断，得到的树与 T2 完全相同。
 *
 * 示例1:
 *
 *  输入：t1 = [1, 2, 3], t2 = [2]
 *  输出：true
 * 示例2:
 *
 *  输入：t1 = [1, null, 2, 4], t2 = [3, 2]
 *  输出：false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/check-subtree-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class CheckSubTree {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        // 三种明确的 base case
        if(t1==null && t2==null){
            return true;
        }
        if(t1==null){
            return false;
        }
        if(t1!=null && t2==null){
            return false;
        }
        // 当t1, t2都不为null时
        // 如果t1==t2，那么希望他们的左右子树也相等
        if(t1.val==t2.val && checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right)){
            return true;
        }
        // 如果t1!=t2，那么希望t1的左子树或者右子树和t2相等
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }
}
