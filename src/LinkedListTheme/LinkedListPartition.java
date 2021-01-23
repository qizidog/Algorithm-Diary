package LinkedListTheme;

import java.util.ArrayList;

/**
 * @author : qizidog
 * @date : 2021-01-23 21:40
 * @description :
 * 将单向链表按某值划分成左边小、中间相等、右边大的形式
 **/

public class LinkedListPartition {
    public static void main(String[] args) {
        ListNode head = new ListNode((int) (Math.random() * 100));
        System.out.print(head+",");
        ListNode p = head;
        for (int i = 1; i < 10; i++) {
            p.next = new ListNode((int) (Math.random() * 100));
            p = p.next;
            System.out.print(p+",");
        }

        head = linkedListPartition2(head, head.val);
        System.out.println();
        p = head;
        while (p!=null){
            System.out.print(p+",");
            p = p.next;
        }
    }

    // 使用6个变量，最后串起来
    private static ListNode linkedListPartition(ListNode head, int target) {
        if (head==null) return null;

        ListNode lS = null; ListNode lE = null;
        ListNode eS = null; ListNode eE = null;
        ListNode mS = null; ListNode mE = null;

        ListNode p = head;
        while (p!=null){
            if (p.val<target){
                if (lS==null){
                    lS = p;
                    lE = p;
                }else{
                    lE.next = p;
                    lE = p;
                }
            } else if (p.val==target){
                if (eS==null){
                    eS = p;
                    eE = p;
                }else{
                    eE.next = p;
                    eE = p;
                }
            } else if (p.val>target){
                if (mS==null){
                    mS = p;
                    mE = p;
                }else{
                    mE.next = p;
                    mE = p;
                }
            }
            ListNode temp = p;
            p = p.next;
            temp.next = null;
        }

        if (eS==null){
            eS = mS;
            eE = mE;
        }
        if (lS==null){
            lS = eS;
            lE = eE;
        }
        if (eE==null){
            lE.next = mS;
        }else{
            lE.next = eS;
            eE.next = mS;
        }

        return lS;
    }

    // 转化为荷兰国旗问题
    private static ListNode linkedListPartition2(ListNode head, int target){
        ArrayList<ListNode> list = new ArrayList<>();
        while (head!=null){
            list.add(head);
            head = head.next;
        }
        ListNode[] arr = new ListNode[list.size()];
        arr = list.toArray(arr);

        int less = -1;
        int more = arr.length;

        int p = 0;
        while (p<more) {
            if (arr[p].val<target){
                swap(arr, ++less, p++);
            } else if (arr[p].val>target){
                swap(arr, --more, p);
            } else{
                p++;
            }
        }

        for (int i = 0; i < arr.length-1; i++) {
            arr[i].next = arr[i+1];
        }
        arr[arr.length-1].next = null;
        return arr[0];
    }

    private static void swap(ListNode[] arr, int i, int j){
        ListNode temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
