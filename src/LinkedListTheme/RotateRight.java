package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-27 16:11
 * @description :
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 *
 * https://leetcode-cn.com/problems/rotate-list/
 **/

public class RotateRight {
    // 管它三七二十一，无论k是否大于链表长度，先把链表长度搞出来再说，别把自己搅晕了
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return null;

        // 计算长度，找到链表尾部
        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            len++;
            p = p.next;
        }
        ListNode tail = p;

        k = k % len;

        if (k == 0) {
            return head;
        } else if (k<0){  // 严格避免k为负数，虽然题目中说了k>=0
            k+=len;
        }

        // 双指针
        ListNode p1 = head;
        ListNode p2 = head;

        while (k > 0) {  // p1先走
            p1 = p1.next;
            k--;
        }

        ListNode pre = null;
        while (p1 != null) {
            pre = p2;
            p1 = p1.next;
            p2 = p2.next;
        }
        pre.next = null;
        tail.next = head;
        return p2;
    }
}
