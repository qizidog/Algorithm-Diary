package zuoshencode.stackQueue;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-03-09 00:31
 * @description :
 * 由两个栈组成的队列
 **/

public class QueueWithTwoStack {
    LinkedList<Integer> stackin = new LinkedList<Integer>();
    LinkedList<Integer> stackout = new LinkedList<Integer>();

    public void offer(Integer num){
        stackin.push(num);
    }

    public Integer poll(){
        if (!stackout.isEmpty()){
            return stackout.pop();
        }else{
            while (!stackin.isEmpty()) {
                stackout.push(stackin.pop());
            }
            return stackout.pop();
        }
    }

    public Integer peek(){
        if (!stackout.isEmpty()){
            return stackout.peek();
        }else{
            while (!stackin.isEmpty()) {
                stackout.push(stackin.pop());
            }
            return stackout.peek();
        }
    }

    public static void main(String[] args) {
        QueueWithTwoStack queue = new QueueWithTwoStack();
        queue.offer(1);
        queue.offer(7);
        queue.offer(4);
        queue.offer(3);
        queue.offer(2);
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println("peek=>"+queue.peek());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.poll());
    }
}
