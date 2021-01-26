package TreeTheme;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-01-26 17:34
 * @description :
 * 实现树的序列化和反序列化（先序遍历和层次遍历两种方式）
 **/

public class SerializeAndReconstructTree {

    public static void main(String[] args) {
        BiTreeNode head = BiTreeNode.createBiTree2();
        LinkedList<String> serialized = preSerialize(head);
        for (String e : serialized) {
            System.out.print(e+" ");
        }
        System.out.println();
        System.out.println(buildByPreQueue(serialized));
    }

    // 通过先序遍历序列化成链表队列
    public static LinkedList<String> preSerialize(BiTreeNode head){
        LinkedList<String> queue = new LinkedList<>();
        pre(head, queue);
        return queue;
    }

    // 将通过先序遍历序列化的结果反序列化成二叉树
    public static BiTreeNode buildByPreQueue(LinkedList<String> preList) {
        String value = preList.poll();
        if (value == null) {
            return null;
        }
        BiTreeNode head = new BiTreeNode(Integer.parseInt(value));
        head.left = buildByPreQueue(preList);
        head.right = buildByPreQueue(preList);
        return head;
    }

    // 先序遍历
    public static void pre(BiTreeNode head, LinkedList<String> queue){
        if (head==null) {
            queue.add(null);
            return;
        }
        queue.add(String.valueOf(head.val));
        pre(head.left, queue);
        pre(head.right, queue);
    }

    // 通过层次遍历序列化成链表队列
    public static LinkedList<String> levelSerial(BiTreeNode head) {
        LinkedList<String> ans = new LinkedList<>();  // 这个队列用来记录序列化的结果
        if (head == null) {
            ans.add(null);
            return ans;
        }
        ans.add(String.valueOf(head.val));
        // 这个队列用来做层次遍历
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        queue.add(head);
        while (!queue.isEmpty()) {
            head = queue.poll();
            if (head.left != null) {
                ans.add(String.valueOf(head.left.val));
                queue.add(head.left);
            } else {
                ans.add(null);
            }
            if (head.right != null) {
                ans.add(String.valueOf(head.right.val));
                queue.add(head.right);
            } else {
                ans.add(null);
            }
        }
        return ans;
    }

    // 将通过层次遍历序列化的结果反序列化成二叉树
    public static BiTreeNode buildByLevelQueue(LinkedList<String> levelList) {
        if (levelList == null || levelList.size() == 0) {
            return null;
        }
        BiTreeNode head = generateNode(levelList.poll());
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        if (head != null) {
            queue.add(head);
        }
        BiTreeNode node = null;
        while (!queue.isEmpty()) {
            node = queue.poll();
            node.left = generateNode(levelList.poll());
            node.right = generateNode(levelList.poll());
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return head;
    }

    public static BiTreeNode generateNode(String val) {
        if (val == null) {
            return null;
        }
        return new BiTreeNode(Integer.parseInt(val));
    }
}
