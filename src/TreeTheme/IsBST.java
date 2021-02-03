package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-03 14:58
 * @description :
 * 给定一棵二叉树的头节点head，返回这棵二叉树是不是二叉搜索树
 **/

public class IsBST {
    // 左神套路求解
    public static boolean isBST(BiTreeNode head){
        if (head==null) return true;
        return proceed(head).isBST;
    }

    static class Info{
        int min;  // 树上的最小值
        int max;  // 树上的最大值
        boolean isBST;  // 是否为二叉搜索树

        public Info(){}

        public Info(int min, int max, boolean isBST) {
            this.min = min;
            this.max = max;
            this.isBST = isBST;
        }
    }

    public static Info proceed(BiTreeNode head){
        if (head==null){
            return null;
        }

        Info leftInfo = proceed(head.left);
        Info rightInfo = proceed(head.right);

        Info info = new Info();
        info.isBST = false;

        info.min = head.val;
        info.max = head.val;
        if (leftInfo!=null){
            info.min = Math.min(leftInfo.min, info.min);
            info.max = Math.max(leftInfo.max, info.max);
        }
        if (rightInfo!=null){
            info.min = Math.min(rightInfo.min, info.min);
            info.max = Math.max(rightInfo.max, info.max);
        }

        if ((leftInfo==null || leftInfo.isBST)
            && (rightInfo==null || rightInfo.isBST)
            && (leftInfo==null || leftInfo.max<head.val)
            && (rightInfo==null || head.val<rightInfo.min)
        ){
            info.isBST = true;
        }
        return info;
    }

    // 中序遍历递归求解
    static int pre = Integer.MIN_VALUE;
    public static boolean isBST2(BiTreeNode head){
        if (head==null) return true;
        boolean l = isBST2(head.left);

        // 如果当前值比左树的值小，直接返回false
        if (head.val<=pre) return false;
        pre = head.val;

        boolean r = isBST2(head.right);

        return l&&r;  // 左右子树都是BST
    }

    // 对数器测试
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            BiTreeNode head = BiTreeNode.generateRandomTree(6, 20, 1);
            pre = Integer.MIN_VALUE;
            if (isBST(head)!=isBST2(head)){
                System.out.println("error!");
            }
        }
    }
}
