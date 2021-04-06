package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-04-06 20:33
 * @description :
 * 328. 奇偶链表
 * 给定一个单链表，把所有的奇数节点和偶数节点分别排在一起。
 * 请注意，这里的奇数节点和偶数节点指的是节点编号的奇偶性，而不是节点的值的奇偶性。
 *
 * 请尝试使用原地算法完成。你的算法的空间复杂度应为 O(1)，时间复杂度应为 O(nodes)，nodes 为节点总数。
 *
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 1->3->5->2->4->NULL
 *
 * 示例 2:
 * 输入: 2->1->3->5->6->4->7->NULL
 * 输出: 2->3->6->7->1->5->4->NULL
 * 说明:
 *
 * 应当保持奇数节点和偶数节点的相对顺序。
 * 链表的第一个节点视为奇数节点，第二个节点视为偶数节点，以此类推。
 *
 * https://leetcode-cn.com/problems/odd-even-linked-list/
 **/

public class OddEvenList {
    // 写法一，一次走一步，用一个boolean判断奇偶
    public ListNode oddEvenList(ListNode head) {
        if(head==null) return null;

        ListNode h1 = head, h2 = head.next;
        ListNode t1 = null, t2 = null;

        boolean flag = true;  // true -> odd
        while(head!=null){
            if(flag){
                if(t1==null){
                    t1 = head;
                }else{
                    t1.next = head;
                    t1 = head;
                }
            }else{
                if(t2==null){
                    t2 = head;
                }else{
                    t2.next = head;
                    t2 = head;
                }
            }
            head = head.next;
            flag = !flag;
        }
        if(t2!=null) t2.next = null;  // 避免成环
        t1.next = h2;
        return h1;
    }

    // 写法二，一次走两步
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode h1 = head, h2 = head.next;
        ListNode t1 = null, t2 = null;

        while (head != null && head.next != null) {
            if (t1!=null){
                t1.next = head;
            }
            t1 = head;
            if (t2!=null){
                t2.next = head.next;
            }
            t2 = head.next;

            head = head.next.next;
        }
        if (t1==null){  // 只有一个元素的情况
            return h1;
        }
        if (head != null){
            t1.next = head;
            t1 = head;
        }
        if(t2!=null){
            t2.next = null;  // 避免成环
        }

        t1.next = h2;
        return h1;
    }


    // 官方给出的更加优雅的写法
    public ListNode oddEvenList3(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode h2 = head.next;
        ListNode t1 = head, t2 = h2;
        while (t2 != null && t2.next != null) {
            t1.next = t2.next;
            t1 = t1.next;
            t2.next = t1.next;
            t2 = t2.next;
        }
        t1.next = h2;
        return head;
    }

}
