package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-25 09:37
 * @description :
 * 83. 删除排序链表中的重复元素
 * 给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。
 *
 * 示例 1:
 * 输入: 1->1->2
 * 输出: 1->2
 * 示例 2:
 *
 * 输入: 1->1->2->3->3
 * 输出: 1->2->3
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/
 **/

public class DeleteDuplicates {
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;
        ListNode dummy = new ListNode(0, head);

        ListNode sure = dummy;
        ListNode cur = head;

        while(cur!=null){
            if(cur.next!=null && cur.val == cur.next.val){
                sure.next = cur.next;  // delete
            }else{
                sure = cur;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
