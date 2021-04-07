package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-04-07 01:29
 * @description :
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 *
 * https://leetcode-cn.com/problems/sort-list/
 **/

public class SortList {
    // 除了这种方法以外，还可以整一个list或者堆，api排序，然后重新连接节点顺序返回，空间O(n)
    public ListNode sortList(ListNode head) {
        if(head==null) return head;

        return sortList(head, null);
    }

    // 归并排序，时间O(nlogn)，空间似乎是O(logn)
    public ListNode sortList(ListNode head, ListNode tail) {
        if(head==tail) return head;

        ListNode[] mids = getMidNode(head, tail);

        ListNode n1 = sortList(head, mids[0]);
        ListNode n2 = sortList(mids[1], tail);

        return mergeNodes(n1, n2);
    }

    // 快慢指针获得中间节点，O(n)
    public ListNode[] getMidNode(ListNode head, ListNode tail){
        if(head==tail) return new ListNode[]{head, tail};
        ListNode p = head;
        while(p.next!=tail && p.next.next!=tail){
            head = head.next;
            p = p.next.next;
        }
        ListNode temp = head.next;
        head.next = null;  // 断开连接，否则合并的时候会找到非关注段去
        return new ListNode[]{head, temp};
    }

    // 合并两个有序的链表，O(n)
    public ListNode mergeNodes(ListNode n1, ListNode n2){
        if(n1==null){
            return n2;
        } else if(n2==null){
            return n1;
        }
        ListNode head = n1.val<=n2.val?n1:n2;
        ListNode p = null;
        while(n1!=null && n2!=null){
            if(n1.val<=n2.val){
                if(p!=null) p.next = n1;
                p = n1;
                n1 = n1.next;
            }else{
                if(p!=null) p.next = n2;
                p = n2;
                n2 = n2.next;
            }
        }
        if(n1!=null){
            p.next = n1;
        }
        if(n2!=null){
            p.next = n2;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(1);
        ListNode n4 = new ListNode(3);
        n1.next=n2;
        n2.next=n3;
        n3.next=n4;
        SortList sortList = new SortList();
        ListNode listNode = sortList.sortList(n1);
        while (listNode!=null){
            System.out.println(listNode);
            listNode = listNode.next;
        }
    }
}
