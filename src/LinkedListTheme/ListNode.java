package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-23 21:50
 * @description :
 * 单链表节点，供本包使用
 **/
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        return val+"";
    }
}
