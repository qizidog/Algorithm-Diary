package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-04-07 13:13
 * @description :
 * 23. 合并K个升序链表
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 **/

public class MergeKLists {
    // 每次找到所有链表最小的头部来连接（个人偏爱的一种写法，然而排bug排了40min，最后效率还很低？？？）
    public static ListNode mergeKLists(ListNode[] lists) {
        int len;
        if(lists==null || (len=lists.length)==0) return null;

        ListNode head = new ListNode();  // 虚拟头结点，省事
        ListNode p = head;

        for(;;) {
            ListNode min = null;
            int midx = 0;
            boolean ctn = false;  // ctn -> continue
            for (int i=0; i<len; i++) {
                if (lists[i] == null){
                    continue;
                }
                ctn = true;
                if (min == null || min.val > lists[i].val) {
                    min = lists[i];
                    midx = i;
                }
            }
            p.next = min;
            p = p.next;
            if (min != null) {
                lists[midx] = min.next;
            }
            if (!ctn) {
                break;
            }
        }

        return head.next;
    }

    // 朴素做法，每次合并两个链表（虽然朴素，但是效率居然比我的高。。）
    public ListNode mergeKLists2(ListNode[] lists) {
        ListNode ans = null;
        for (int i = 0; i < lists.length; ++i) {
            ans = mergeTwoLists(ans, lists[i]);
        }
        return ans;
    }

    // 主要是应该把这个方法抽取出来，基本上是固定死了的，不容易出错
    public ListNode mergeTwoLists(ListNode a, ListNode b) {
        if (a == null || b == null) {
            return a != null ? a : b;
        }
        ListNode head = new ListNode(0);
        ListNode tail = head, aPtr = a, bPtr = b;
        while (aPtr != null && bPtr != null) {
            if (aPtr.val < bPtr.val) {
                tail.next = aPtr;
                aPtr = aPtr.next;
            } else {
                tail.next = bPtr;
                bPtr = bPtr.next;
            }
            tail = tail.next;
        }
        tail.next = (aPtr != null ? aPtr : bPtr);
        return head.next;
    }

    // 基于分治（推荐）
    public ListNode mergeKLists3(ListNode[] lists) {
        int len;
        if(lists==null || (len=lists.length)==0) return null;

        return process(lists, 0, len-1);
    }

    private ListNode process(ListNode[] lists, int start, int end){
        if (start==end) return lists[start];
        int mid = (start+end)/2;
        ListNode p1 = process(lists, start, mid);
        ListNode p2 = process(lists, mid + 1, end);
        return mergeTwoLists(p1, p2);
    }


    public static void main(String[] args) {
        // [[1,4,5],[1,3,4],[2,6]]
        ListNode n11 = new ListNode(1);
        ListNode n12 = new ListNode(4);
        ListNode n13 = new ListNode(5);
        n11.next = n12;
        n12.next = n13;

        ListNode n21 = new ListNode(1);
        ListNode n22 = new ListNode(3);
        ListNode n23 = new ListNode(4);
        n21.next = n22;
        n22.next = n23;

        ListNode n31 = new ListNode(2);
        ListNode n32 = new ListNode(6);
        n31.next = n32;

        ListNode listNode = mergeKLists(new ListNode[]{n11, n21, n31});
        while (listNode!=null){
            System.out.println(listNode);
            listNode = listNode.next;
        }
    }
}
