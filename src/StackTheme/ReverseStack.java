package StackTheme;

import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-02-17 19:52
 * @description :
 * 使用递归实现栈元素的逆序，不得使用额外的数据结构
 **/

public class ReverseStack {

    // 方法一：弹出栈底元素，将剩余栈逆转，再将弹出的元素压入栈顶
    public static void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()) return;
        Integer last = popLast(stack);
        reverseStack(stack);
        stack.push(last);
    }

    public static Integer popLast(Stack<Integer> stack){
        if (stack.size() == 1) {
            return stack.pop();
        }
        Integer peek = stack.pop();
        Integer last = popLast(stack);
        stack.push(peek);
        return last;
    }

    // 方法二：弹出栈顶元素，将剩余栈逆转，再将弹出的元素插入栈底
    public static void reverseStack2(Stack<Integer> stack) {
        if (stack.isEmpty()) return;
        Integer top = stack.pop();
        reverseStack2(stack);
        insertBottom(stack, top);
    }

    private static void insertBottom(Stack<Integer> stack, Integer top) {
        if (stack.isEmpty()) {
            stack.push(top);
            return;
        }
        Integer e = stack.pop();
        insertBottom(stack, top);
        stack.push(e);
    }


    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>(){{
            add(1);
            add(2);
            add(3);
            add(4);
            add(5);
        }};
        System.out.println(stack);
        reverseStack(stack);
        System.out.println(stack);
        reverseStack2(stack);
        System.out.println(stack);
    }
}
