package StackTheme;

import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2020-09-10 10:11
 * @description :
 * JZ5 用两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 **/

public class QueueWithTwoStack {
    private Stack<Integer> stack1 = new Stack<Integer>();
    private Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node){
        stack1.push(node);
    }

    public int pop(){
        if(stack2.empty()){
            while (!stack1.empty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }

    public int peek(){
        return stack2.peek();
    }

    // 经过尝试，使用两个stack来实现queue，toString方法极度不好实现，就算了

    public static void main(String[] args) {
        QueueWithTwoStack queue = new QueueWithTwoStack();
        queue.push(1);
        queue.push(3);
        System.out.println(queue.pop());

        queue.push(2);
        System.out.println(queue.pop());

        queue.push(7);
        System.out.println(queue.pop());
        System.out.println(queue.peek());
//        System.out.println(queue.pop());
//        System.out.println(queue.peek());
    }
}
