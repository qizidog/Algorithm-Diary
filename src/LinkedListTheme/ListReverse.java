package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-02 22:11
 * @description :
 * 实现单链表或双链表的反转（基础）
 **/

public class ListReverse {

    /*
        创建一个单链表
     */
    public static SingleListNode<Integer> createSingleList(){
        SingleListNode<Integer> head = new SingleListNode<>(0);
        SingleListNode<Integer> pointer = head;
        for (int i=1; i<6; i++){
            SingleListNode<Integer> cur = new SingleListNode<>(i);
            pointer.next = cur;
            pointer = cur;
        }
        return head;
    }

    /*
        创建一个双链表
     */
    public static DoubleListNode<Integer> createDoubleList(){
        DoubleListNode<Integer> head = new DoubleListNode<>(0);
        DoubleListNode<Integer> pointer = head;
        // DoubleListNode<Integer> pre = null;
        for (int i=1; i<6; i++){
            DoubleListNode<Integer> cur = new DoubleListNode<Integer>(i);
            pointer.next = cur;
            cur.pre = pointer;
            pointer = cur;
        }
        return head;
    }

    /*
        单链表反转
     */
    public static SingleListNode reverseList(SingleListNode head){
        SingleListNode pre = null;
        SingleListNode next = null;

        while(head!=null){
            next = head.next;
            head.next = pre;

            // 为下一次循环做准备
            pre = head;
            head=next;
        }
        return pre;
    }

    /*
        双链表反转
     */
    public static DoubleListNode reverseList(DoubleListNode head){
        DoubleListNode pre = null;
        DoubleListNode next = null;

        while(head!=null){
            next = head.next;
            pre = head.pre;

            head.next = pre;
            head.pre = next;

            pre = head;
            head = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        // 单链表反转测试
        SingleListNode<Integer> singleList = createSingleList();

        singleList = reverseList(singleList);

        // 查看链表
        SingleListNode<Integer> singlePointer = singleList;
        for (int i=0; i<6; i++){
            System.out.println(singlePointer);
            singlePointer = singlePointer.next;
        }

        System.out.println("==============================");

        // 双链表反转测试
        DoubleListNode<Integer> doubleList = createDoubleList();

        doubleList = reverseList(doubleList);

        // 查看链表
        DoubleListNode<Integer> doublePointer = doubleList;
        for (int i=0; i<6; i++){
            System.out.println(doublePointer);
            doublePointer = doublePointer.next;
        }

    }
}
