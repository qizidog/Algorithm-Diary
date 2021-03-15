package LinkedListTheme;

import java.util.HashMap;

/**
 * @author : qizidog
 * @date : 2021-03-15 22:37
 * @description :
 * 剑指 Offer 35. 复杂链表的复制
 *
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fu-za-lian-biao-de-fu-zhi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class CopyRandomList {
    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if(head==null) return null;

        HashMap<Node, Node> map = new HashMap<>();

        Node root = new Node(head.val);
        map.put(head, root);

        Node cur = root;
        Node p = head.next;
        while(p!=null){
            cur.next = new Node(p.val);

            map.put(p, cur.next);
            p = p.next;
            cur = cur.next;
        }
        cur = root;
        p = head;
        while(cur!=null){
            cur.random = map.get(p.random);
            cur = cur.next;
            p = p.next;
        }
        return root;
    }

}
