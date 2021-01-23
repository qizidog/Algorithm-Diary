package LinkedListTheme;

import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-01-23 18:45
 * @description :
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class IsPalindrome {

    // O(n) 时间复杂度和 O(n) 空间复杂度
    public boolean isPalindrome(ListNode head) {
        if(head==null) return true;
        Stack<ListNode> stack = new Stack();
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        while(slow!=null){
            stack.push(slow);
            slow = slow.next;
        }

        while(!stack.isEmpty()){
            if(stack.pop().val!=head.val) return false;
            head = head.next;
        }
        return true;
    }

    // O(n) 时间复杂度和 O(1) 空间复杂度
    public boolean isPalindrome2(ListNode head) {
        if(head==null) return true;
        ListNode slow = head;
        ListNode fast = head;
        while(fast.next!=null && fast.next.next!=null){
            slow = slow.next;
            fast = fast.next.next;
        }
        // 交换链接节点方向
        ListNode pre = slow;
        ListNode cur = slow.next;
        ListNode next;
        slow.next = null;  // 中间节点next指定为null
        while(cur!=null){
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        //逐个比较
        while(head!=null){
            if(head.val!=pre.val) return false;
            head = head.next;
            pre = pre.next;
        }
        return true;
    }
}
