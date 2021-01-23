package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-23 21:31
 * @description :
 * 双链表节点，供本包使用
 **/
class DoubleListNode<E> {
    DoubleListNode<E> next;
    DoubleListNode<E> pre;
    E value;

    public DoubleListNode(E value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DoubleListNode{" +
                "pre=" + (pre == null ? "null" : pre.value) +
                ", value=" + value +
                ", next=" + (next == null ? "null" : next.value) +
                '}';
    }
}
