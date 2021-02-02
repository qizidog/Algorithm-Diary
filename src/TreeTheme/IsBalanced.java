package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-02 21:32
 * @description :
 * 给定一棵二叉树的头节点head，返回这棵二叉树是不是平衡二叉树
 **/

public class IsBalanced {

    public static boolean isBalanced(BiTreeNode head){
        Info info = proceed(head);
        return info.isBalanced;
    }

    public static Info proceed(BiTreeNode head){
        if (head==null) return new Info(true, 0);

        Info leftInfo = proceed(head.left);
        Info rightInfo = proceed(head.right);

        Info info = new Info();
        info.level = Math.max(leftInfo.level, rightInfo.level) + 1;
        info.isBalanced = false;

        // 当且仅当左右树均平衡，且左右数高度差不超过1时，当前子树平衡
        if (leftInfo.isBalanced &&
                rightInfo.isBalanced &&
                Math.abs(leftInfo.level-rightInfo.level)<=1){
            info.isBalanced = true;
        }
        return info;
    }

    static class Info{
        boolean isBalanced;  // 是否为平衡二叉树
        int level;  // 树的高度

        public Info(){}
        public Info(boolean isBalanced, int level) {
            this.isBalanced = isBalanced;
            this.level = level;
        }
    }

    public static void main(String[] args) {
        BiTreeNode head = BiTreeNode.generateRandomTree(3, 100, 1);
        BiTreeNode.printTree(head);
        System.out.println(isBalanced(head));
    }


}
