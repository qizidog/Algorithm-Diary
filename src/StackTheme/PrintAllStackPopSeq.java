package StackTheme;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-03-14 14:13
 * @description :
 * 打印一个指定进栈序列的所有可能出栈序列
 * 例如：进栈序列为[1,2]，可能的出栈序列为 [1,2], [2,1]
 *      进栈序列为[1,2,3]，可能的出栈序列为 [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,2,1]
 **/

public class PrintAllStackPopSeq {
    public static LinkedList<LinkedList<Integer>> result = new LinkedList<>();


    public static void process(Stack<Integer> stack, int[] queue, int idx, LinkedList<Integer> re){
        // 如果没有元素可以压入了，那只能弹出
        if (idx >= queue.length){
            Stack<Integer> temp = (Stack<Integer>)stack.clone();
            while (!temp.isEmpty()) {
                re.add(temp.pop());
            }
            result.add(new LinkedList<>(re));
            return;
        }
        // 如果还有元素可以压入，那么面临两种选择
        // 1. 弹出
        if (!stack.isEmpty()) {
            Integer pop = stack.pop();
            re.addLast(pop);
            process(stack, queue, idx, new LinkedList<Integer>(re));
            stack.push(pop);  // 复原栈
            re.removeLast();
        }
        // 2. 压入
        stack.push(queue[idx]);
        process(stack, queue, idx+1, new LinkedList<Integer>(re));
        stack.pop();  // 复原栈
    }


    public static void main(String[] args) {
        int[] queue = new int[]{1,2,3};

        process(new Stack<Integer>(), queue, 0, new LinkedList<Integer>());
        System.out.println(result);
    }
}
