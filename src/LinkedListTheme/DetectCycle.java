package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-04-06 23:02
 * @description :
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *
 * https://leetcode-cn.com/problems/linked-list-cycle-ii/
 **/

public class DetectCycle {
    public ListNode detectCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head;
        while(p2!=null && p2.next!=null){
            p1 = p1.next;
            p2 = p2.next.next;
            if(p1==p2) break;
        }
        if(p2==null || p2.next==null) return null;
        p2 = head;
        while(p1!=p2){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }
}
