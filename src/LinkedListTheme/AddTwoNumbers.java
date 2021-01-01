package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-01 00:12
 * @description :
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class AddTwoNumbers {
    // Definition for singly-linked list.
    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 考虑两种特殊情况
        if(l1==null) return l2;
        if(l2==null) return l1;

        ListNode nl1 = l1;
        ListNode nl2 = l2;
        int rest = 0;  // 进位

        int sum = nl1.val+nl2.val+rest;
        ListNode newl = new ListNode(sum%10);  // 记录头结点
        rest = sum/10;
        nl1 =  nl1.next;
        nl2 = nl2.next;

        ListNode pre = newl;
        ListNode cur = null;
        while(nl1!=null || nl2!=null){
            int v1 = nl1!=null?nl1.val:0;
            int v2 = nl2!=null?nl2.val:0;
            sum = v1+v2+rest;
            cur = new ListNode(sum%10);
            rest = sum/10;

            // 为下一轮循环准备
            pre.next = cur;
            pre = cur;
            if(nl1!=null) nl1 =  nl1.next;
            if(nl2!=null) nl2 = nl2.next;
        }
        // 如果最后进位了，再在最后加一个节点
        if(rest>0){
            pre.next = new ListNode(1);
        }

        // ListNode temp = newl;
        // while(temp!=null){
        //     System.out.println(temp.val);
        //     temp = temp.next;
        // }
        return newl;
    }
}
