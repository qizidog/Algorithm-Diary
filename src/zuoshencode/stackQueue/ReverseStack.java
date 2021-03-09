package zuoshencode.stackQueue;

import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-03-09 00:42
 * @description :
 * 仅用递归函数和栈操作逆序一个栈
 **/

public class ReverseStack {
    public static void process(Stack<Integer> stack){
        if (stack.size()<=1){
            return;
        }

        // get the bottom element
        Integer bottom = popBottom(stack);

        process(stack);

        stack.push(bottom);
    }

    private static Integer popBottom(Stack<Integer> stack) {
        if (stack.size()==1){
            return stack.pop();
        }

        Integer top = stack.pop();
        Integer bottom = popBottom(stack);
        stack.push(top);
        return bottom;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);

        System.out.println(stack);
        process(stack);
        System.out.println(stack);
    }
}
