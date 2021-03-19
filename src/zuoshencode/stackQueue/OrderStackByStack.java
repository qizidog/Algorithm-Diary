package zuoshencode.stackQueue;

import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-03-09 19:51
 * @description :
 * 用一个栈为另一个栈排序
 **/

public class OrderStackByStack {
    public static void proceed(Stack<Integer> stack, Stack<Integer> help){
        if (stack.size() == 1) {
            return;
        }
        Integer top = stack.pop();
        proceed(stack, help);
        while (!stack.isEmpty() && stack.peek()>top){
            help.push(stack.pop());
        }
        stack.push(top);
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }

    public static void order(Stack<Integer> stack){
        Stack<Integer> help = new Stack<>();
        proceed(stack, help);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        stack.push(2);
        stack.push(2);
        stack.push(4);
        stack.push(7);
        stack.push(3);
        stack.push(5);
        stack.push(1);
        order(stack);
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    // 2021.3.19 再次实现，非递归版本，思路更清晰
    public static void order2(Stack<Integer> stack) {
        Stack<Integer> help = new Stack<>();

        while (!stack.isEmpty()) {
            Integer top = stack.pop();
            if (help.isEmpty() || help.peek() >= top) {
                help.push(top);
            }else{
                while (!help.isEmpty() && help.peek() < top) {
                    stack.push(help.pop());
                }
                help.push(top);
            }
        }
        // 全部倒回原本的栈去
        while (!help.isEmpty()) {
            stack.push(help.pop());
        }
    }


}
