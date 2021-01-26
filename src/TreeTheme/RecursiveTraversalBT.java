package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-01-26 16:15
 * @description :
 * 递归实现二叉树的前、中、后序遍历
 **/

public class RecursiveTraversalBT {

    public static void main(String[] args) {
        // 创建一棵简单的二叉树
        BiTreeNode head = BiTreeNode.createBiTree();
        System.out.print("先序遍历：");
        pre(head);
        System.out.println();
        System.out.print("中序遍历：");
        mid(head);
        System.out.println();
        System.out.print("后序遍历：");
        aft(head);
    }

    // 先序遍历
    public static void pre(BiTreeNode head){
        if (head==null) return;
        System.out.print(head+", ");
        pre(head.left);
        pre(head.right);
    }

    // 中序遍历
    public static void mid(BiTreeNode head){
        if (head==null) return;
        mid(head.left);
        System.out.print(head+", ");
        mid(head.right);
    }

    // 后序遍历
    public static void aft(BiTreeNode head){
        if (head==null) return;
        aft(head.left);
        aft(head.right);
        System.out.print(head+", ");
    }

}
