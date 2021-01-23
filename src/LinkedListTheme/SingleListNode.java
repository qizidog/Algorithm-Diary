package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-23 21:30
 * @description :
 * 单链表，供本包使用
 **/

class SingleListNode<E> {
    SingleListNode<E> next;
    E value;

    public SingleListNode(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public String toString2() {
        return "SingleListNode{" +
                "value=" + value +
                ", next=" + (next == null ? "null" : next.value) +
                '}';
    }
}
