package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-01 00:12
 * @description :
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
