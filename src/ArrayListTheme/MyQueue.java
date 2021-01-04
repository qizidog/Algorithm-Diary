package ArrayListTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-02 23:54
 * @description :
 * 自己实现一个基于数组的队列（基础）
 **/

public class MyQueue<E> {
    private int size;  // 队列总长度
    private int num;  // 用来存储当前队列中元素的数量
    int head = 0;  // 当前队首位置
    int tail = 0;  // 下一空缺的位置
    private E[] arr;

    public MyQueue(int size) {
        this.size = size;
        arr = (E[]) new Object[size];
    }

    public boolean offer(E e){
        if (num>=size) return false;

        arr[tail++] = e;
        tail = (tail+size)%size;

        num++;
        return true;
    }

    public E poll(){
        if (num==0) return null;

        E t = arr[head];
        arr[head] = null;
        head = (head+1)%size;

        num--;
        return t;
    }

    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public String toString() {
        return "MyQueue{" +
                "arr=" + Arrays.toString(arr) +
                '}';
    }

    public static void main(String[] args) {
        MyQueue<Integer> q = new MyQueue<>(6);
        for (int i=1; i<7; i++){
            q.offer(i);
        }
        q.offer(8);
        System.out.println(q.poll());
        System.out.println(q);

        q.poll();
        q.poll();
        q.poll();
        System.out.println(q);

        q.offer(10);
        System.out.println(q);

        q.offer(11);
        q.offer(12);
        q.offer(13);
        q.offer(14);
        System.out.println(q);

        q.poll();
        System.out.println(q);
    }
}
