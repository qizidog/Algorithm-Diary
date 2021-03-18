package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-17 11:57
 * @description :
 * 剑指 Offer 22. 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 *
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 *
 *  
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 *
 * 返回链表 4->5.
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class GetKthFromEnd {
    public ListNode getKthFromEnd(ListNode head, int k) {
        if(head==null) return null;

        ListNode p1 = head;
        ListNode p2 = head;
        for(int i=0; i<k-1; i++){  // p1先走k-1步
            if(p2.next!=null){
                p2 = p2.next;
            }else{  // 小心明明只有k个节点，他要找倒数k+1个
                return null;
            }
        }
        while(p2.next!=null){
            p2 = p2.next;
            p1 = p1.next;
        }
        return p1;
    }

    public ListNode getKthFromEnd2(ListNode head, int k) {
        ListNode former = head, latter = head;
        for(int i = 0; i < k; i++)  // p1先走k步
            former = former.next;
        while(former != null) {
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }
}
