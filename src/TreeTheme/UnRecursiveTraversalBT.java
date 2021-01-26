package TreeTheme;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-01-26 16:37
 * @description :
 * 非递归实现二叉树的前、中、后序遍历，以及层次遍历
 **/

public class UnRecursiveTraversalBT {
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
        System.out.println();
        System.out.print("后序遍历：");
        aft2(head);
        System.out.println();
        System.out.print("层次遍历：");
        level(head);
    }

    // 先序遍历
    public static void pre(BiTreeNode head){
        Stack<BiTreeNode> stack = new Stack<>();
        stack.push(head);
        while (!stack.isEmpty()){
            BiTreeNode cur = stack.pop();
            System.out.print(cur+", ");
            if (cur.right!=null) stack.push(cur.right);
            if (cur.left!=null) stack.push(cur.left);
        }
    }

    // 中序遍历
    public static void mid(BiTreeNode head){
        Stack<BiTreeNode> stack = new Stack<>();
        BiTreeNode cur = head;
        while (cur!=null || !stack.isEmpty()){
            if (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }else{
                cur = stack.pop();
                System.out.print(cur+", ");
                cur = cur.right;
            }
        }
    }

    // 后序遍历（使用两个栈实现）
    public static void aft(BiTreeNode head){
        Stack<BiTreeNode> stack1 = new Stack<>();
        Stack<BiTreeNode> stack2 = new Stack<>();
        stack1.push(head);
        while (!stack1.isEmpty()){
            BiTreeNode cur = stack1.pop();
            stack2.push(cur);
            if (cur.left!=null) stack1.push(cur.left);
            if (cur.right!=null) stack1.push(cur.right);
        }
        while (!stack2.isEmpty()){
            System.out.print(stack2.pop()+", ");
        }
    }

    // 使用一个栈实现
    public static void aft2(BiTreeNode h){
        // h记录上一个访问的节点
        // c记录当前访问的节点
        if (h != null) {
            Stack<BiTreeNode> stack = new Stack<BiTreeNode>();
            stack.push(h);
            BiTreeNode c = null;
            while (!stack.isEmpty()) {
                c = stack.peek();
                if (c.left != null && h != c.left && h != c.right) {
                    stack.push(c.left);
                } else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                } else {
                    System.out.print(stack.pop().val + ", ");
                    h = c;
                }
            }
        }
    }

    // 层次遍历
    public static void level(BiTreeNode head){
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        BiTreeNode cur = head;
        queue.offer(cur);
        while (!queue.isEmpty()){
            cur = queue.poll();
            System.out.print(cur+", ");
            if (cur.left!=null) queue.offer(cur.left);
            if (cur.right!=null) queue.offer(cur.right);
        }
    }
}
