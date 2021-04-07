package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-04-07 12:24
 * @description :
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 **/

public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(head==null) return head;

        ListNode p1 = head, p2 = head;
        while(p1!=null && n>0){
            p1 = p1.next;
            n--;
        }
        if(n>0){  // n超出了链表长度
            return head;
        }
        ListNode pre = null;
        while(p1!=null){
            p1 = p1.next;
            pre = p2;
            p2 = p2.next;
        }
        if(pre==null){
            return head.next;
        }
        // 移除节点
        pre.next = p2.next;

        return head;
    }

}
