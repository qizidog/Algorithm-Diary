package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-23 16:35
 * @description :
 * 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点；
 *
 * 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点；
 *
 * 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个；
 *
 * 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个。
 **/

public class FastSlowPointer {

    public static void main(String[] args) {
        SingleListNode<Integer> head1 = new SingleListNode<>(1);
        SingleListNode<Integer> pointer = head1;
        System.out.print(pointer+",");
        for (int i=2; i<=5; i++){
            SingleListNode<Integer> cur = new SingleListNode<>(i);
            System.out.print(cur+",");
            pointer.next = cur;
            pointer = cur;
        }
        System.out.println();

        SingleListNode<Integer> head2 = new SingleListNode<>(1);
        pointer = head2;
        System.out.print(pointer+",");
        for (int i=2; i<=6; i++){
            SingleListNode<Integer> cur = new SingleListNode<>(i);
            System.out.print(cur+",");
            pointer.next = cur;
            pointer = cur;
        }
        System.out.println();


        System.out.println("奇数长度返回中点，偶数长度返回上中点");
        System.out.print(require1(head1)+",");
        System.out.println(require1(head2));

        System.out.println("奇数长度返回中点，偶数长度返回下中点");
        System.out.print(require2(head1)+",");
        System.out.println(require2(head2));

        System.out.println("奇数长度返回中点前一个，偶数长度返回上中点前一个");
        System.out.print(require3(head1)+",");
        System.out.println(require3(head2));

        System.out.println("奇数长度返回中点前一个，偶数长度返回下中点前一个");
        System.out.print(require4(head1)+",");
        System.out.println(require4(head2));
    }

    // 1）输入链表头节点，奇数长度返回中点，偶数长度返回上中点；
    public static SingleListNode<?> require1(SingleListNode<?> head){
        // 节点个数小于3个时
        if (head==null || head.next==null || head.next.next==null) return head;

        SingleListNode<?> fast = head.next.next;
        SingleListNode<?> slow = head.next;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 2）输入链表头节点，奇数长度返回中点，偶数长度返回下中点；
    public static SingleListNode<?> require2(SingleListNode<?> head){
        if (head==null || head.next==null) return head;

        SingleListNode<?> fast = head.next;
        SingleListNode<?> slow = head.next;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 3）输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个；
    public static SingleListNode<?> require3(SingleListNode<?> head){
        if (head==null || head.next==null || head.next.next == null) return null;

        SingleListNode<?> fast = head.next.next;
        SingleListNode<?> slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // 4）输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个。
    public static SingleListNode<?> require4(SingleListNode<?> head){
        if (head==null || head.next==null) return null;

        SingleListNode<?> fast = head.next;
        SingleListNode<?> slow = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
}
