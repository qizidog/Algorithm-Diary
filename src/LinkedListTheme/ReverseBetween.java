package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-25 10:55
 * @description :
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 **/

public class ReverseBetween {
    // 自己的写法，虽然能搞出来，但是很难维护代码，并且写的时候很痛苦
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null) return null;

        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt = head.next;

        ListNode before = null;
        ListNode leftNode = null;

        int count = 1;
        while(cur!=null){
            if(count<left || count>right){  // 如果没有到达指定范围
                pre = cur;
                cur = cur.next;
                count++;
            }else{
                before = pre;  // 记录指定范围的前一个节点
                while(cur!=null && count>=left && count<=right){  // 反转指定区域
                    nxt = cur.next;

                    if(count==left) {
                        leftNode = cur;  // 记录指定范围的第一个节点
                    }else{
                        cur.next = pre;
                    }

                    pre = cur;
                    cur = nxt;
                    count++;
                }
                leftNode.next = cur;
                if(before!=null){
                    before.next = pre;
                }else{
                    head = pre;
                }
                break;
            }

        }
        return head;
    }

    // 换一种写法，把指定区域拆出来，反转完后再接回去，快速实现，坑少
    public ListNode reverseBetween2(ListNode head, int left, int right) {
        if (head==null) return null;

        ListNode pre = null;
        ListNode cur = head;
        ListNode nxt;

        int count = 1;
        while(count<left && cur!=null){  // 先迭代直到抵达指定区域
            pre = cur;
            cur = cur.next;
            count++;
        }

        ListNode before = pre;
        ListNode leftNode = cur;

        while(count<right && cur!=null){  // 先迭代直到抵达指定区域
            pre = cur;
            cur = cur.next;
            count++;
        }
        ListNode after = cur.next;
        cur.next = null;  // 拆出来

        // 反转指定区域
        ListNode rightNode = reverseList(leftNode);

        // 接回去
        if (before != null) {
            before.next = rightNode;
        }else{
            head = rightNode;
        }
        leftNode.next = after;

        return head;
    }

    public ListNode reverseList(ListNode head){
        ListNode pre = null;
        ListNode next = null;

        while(head!=null){
            next = head.next;
            head.next = pre;

            // 为下一次循环做准备
            pre = head;
            head=next;
        }
        return pre;
    }

}
