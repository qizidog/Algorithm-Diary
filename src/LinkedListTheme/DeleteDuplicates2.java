package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-25 09:37
 * @description :
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 *
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/
 **/

public class DeleteDuplicates2 {
    // 疯了，这种链表的题会搞死人，动不动就NPE
    // 以后遇到这种链表的题，管它三七二十一，上去就是pre，cur，nxt三个指针，再加个sure
    public ListNode deleteDuplicates(ListNode head) {
        if(head==null) return null;

        ListNode HEAD = new ListNode();  // 增加一个头结点
        HEAD.next = head;

        ListNode sure = HEAD;  // 已确定的最后一个节点
        ListNode pre = null;  // 上一个节点
        ListNode cur = head;  // 当前节点
        ListNode nxt = head.next;  // 下一个节点

        // 第一次的时候pre为null，单独处理
        if(nxt!=null && cur.val==nxt.val){
            sure.next = nxt;  // 删除当前节点
        }else{
            sure = cur;
        }
        pre = cur;
        cur = nxt;
        if(nxt!=null){
            nxt = nxt.next;
        }

        while(cur!=null){
            if(cur.val==pre.val || (nxt!=null && cur.val==nxt.val)){
                sure.next = nxt;  // 删除当前节点
            }else{
                sure = cur;
            }
            pre = cur;
            cur = nxt;
            if(nxt!=null){
                nxt = nxt.next;
            }
        }
        return HEAD.next;
    }
}
