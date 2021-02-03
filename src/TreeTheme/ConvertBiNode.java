package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-03 12:07
 * @description :
 * 面试题 17.12. BiNode
 * 二叉树数据结构TreeNode可用来表示单向链表（其中left置空，right为下一个链表节点）。
 * 实现一个方法，把二叉搜索树转换为单向链表，要求依然符合二叉搜索树的性质，转换操作应是原址的，也就是在原始的二叉搜索树上直接修改。
 *
 * 返回转换后的单向链表的头节点。
 *
 * 注意：本题相对原题稍作改动
 *
 *  
 *
 * 示例：
 *
 * 输入： [4,2,5,1,3,null,6,0]
 * 输出： [0,null,1,null,2,null,3,null,4,null,5,null,6]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binode-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class ConvertBiNode {
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode convertBiNode(TreeNode root) {
        return proceed(root).head;
    }

    static class Info{
        TreeNode head;
        TreeNode tail;
    }

    // 解法一：利用左神套路求解
    public Info proceed(TreeNode root){
        if(root==null){
            return new Info();
        }

        Info leftInfo = proceed(root.left);
        Info rightInfo = proceed(root.right);

        Info info = new Info();
        info.head = leftInfo.head!=null?leftInfo.head:root;
        info.tail = rightInfo.tail!=null?rightInfo.tail:root;

        if(leftInfo.head!=null)
            leftInfo.tail.right = root;
        if(rightInfo.head!=null)
            root.right = rightInfo.head;
        root.left = null;

        return info;
    }


    /**
     * 解法二：利用中序遍历求解
     * @param root 本次处理的节点
     */
    public void proceed2(TreeNode root){
        if (root==null) return;

        proceed2(root.left);  // 先处理左边

        root.left = null;
        if (pre==null){
            thisHead = root;
            pre=root;
        }else {
            pre.right = root;
            pre = root;
        }

        proceed2(root.right);  // 再处理右边

    }

    public TreeNode doP2(TreeNode head){
        proceed2(head);
        return thisHead;
    }

    // @param pre 上一个完成调整的节点
    TreeNode pre = null;
    TreeNode thisHead = null;
}
