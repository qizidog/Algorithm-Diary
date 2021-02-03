package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-03 16:59
 * @description :
 * 给定一棵二叉树的头节点head，返回这棵二叉树中最深搜索二叉树的头结点
 **/

public class MaxSubBSTHead {
    public static BiTreeNode maxSubBSTHead(BiTreeNode head){
        if (head==null) return head;
        return proceed(head).head;
    }

    static class Info{
        boolean isBST;  // 是否为二叉搜索树
        BiTreeNode head;  // 如果是二叉搜索树，记录其头节点
        int level;  // 当前二叉树的高度

        public Info(){}

        public Info(BiTreeNode head, boolean isBST, int level) {
            this.head = head;
            this.isBST = isBST;
            this.level = level;
        }
    }

    public static Info proceed(BiTreeNode head){
        if (head==null){
            return new Info(null, true, 0);
        }

        Info leftInfo = proceed(head.left);
        Info rightInfo = proceed(head.right);

        Info info = new Info();
        info.head = leftInfo.level>rightInfo.level?leftInfo.head:rightInfo.head;
        info.isBST = false;
        info.level = Math.max(leftInfo.level, rightInfo.level);
        if (leftInfo.isBST && rightInfo.isBST && leftInfo.head.val<head.val && head.val<rightInfo.head.val){
            info.head = head;
            info.isBST = true;
            info.level += 1;
        }

        return info;
    }
}
