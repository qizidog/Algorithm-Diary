package StackTheme;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * @author : qizidog
 * @date : 2021-02-17 23:21
 * @description :
 * 225. 用队列实现栈
 * 请你仅使用两个队列实现一个后入先出（LIFO）的栈，并支持普通队列的全部四种操作（push、top、pop 和 empty）。
 *
 * 实现 MyStack 类：
 *
 * void push(int x) 将元素 x 压入栈顶。
 * int pop() 移除并返回栈顶元素。
 * int top() 返回栈顶元素。
 * boolean empty() 如果栈是空的，返回 true ；否则，返回 false 。
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-stack-using-queues
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class StackWithTwoQueue {
    // 该队列仅保留最后入栈的元素
    private LinkedList<Integer> topList;
    // 该队列保留除栈顶元素以外的其他所有元素
    private LinkedList<Integer> keepList;

    /** Initialize your data structure here. */
    public StackWithTwoQueue() {
        topList = new LinkedList<>();
        keepList = new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        while (!topList.isEmpty()){
            keepList.offer(topList.poll());
        }
        topList.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        if (topList.isEmpty()){
            throw new NoSuchElementException();
        }
        while (keepList.size()>1){
            topList.offer(keepList.poll());
        }
        LinkedList<Integer> temp = topList;
        topList = keepList;
        keepList = temp;
        return keepList.poll();
    }

    /** Get the top element. */
    public int top() {
        if (!empty()) {
            return topList.peek();
        }
        throw new NoSuchElementException();
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return topList.isEmpty();
    }
}
