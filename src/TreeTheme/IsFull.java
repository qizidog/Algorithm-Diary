package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-02 22:22
 * @description :
 * 给定一棵二叉树的头节点head，返回这棵二叉树是不是满二叉树
 **/

public class IsFull {
    static class Info{
        boolean isFull;
        int level;

        public Info() {
        }
        public Info(boolean isFull, int level){
            this.isFull = isFull;
            this.level = level;
        }
    }

    public static Info proceed(BiTreeNode head){
        if (head==null) return new Info(true, 0);

        Info leftInfo = proceed(head.left);
        Info rightInfo = proceed(head.right);

        Info info = new Info();
        info.level = Math.max(leftInfo.level, rightInfo.level)+1;
        info.isFull = false;
        if (leftInfo.isFull && rightInfo.isFull && leftInfo.level == rightInfo.level) {
            info.isFull = true;
        }
        return info;
    }

    public static boolean isFull(BiTreeNode head){
        return proceed(head).isFull;
    }

    public static void main(String[] args) {
        BiTreeNode head = BiTreeNode.generateRandomTree(3, 100, 1);
        BiTreeNode.printTree(head);
        System.out.println(isFull(head));
    }
}
