package zuoshencode.stackQueue;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-03-09 00:11
 * @description :
 * 设计一个有getMin功能的栈
 **/

public class GetMinStack {
    private LinkedList<Integer> stack;
    private LinkedList<Integer> stackMin;

    public GetMinStack() {
        this.stack = new LinkedList<>();
        this.stackMin = new LinkedList<>();
    }

    public void push(Integer num){
        stack.push(num);
        Integer m = stackMin.peek();
        if (m != null) {
            stackMin.push(num <= m ? num : m);
        }else{
            stackMin.push(num);
        }
    }

    public Integer pop(){
        stackMin.pop();
        return stack.pop();
    }

    public Integer peek(){
        return stack.peek();
    }

    public Integer getMin(){
        return stackMin.peek();
    }

    public static void main(String[] args) {
        GetMinStack myStack = new GetMinStack();
        myStack.push(4);
        System.out.println(myStack.getMin());

        myStack.push(2);
        System.out.println(myStack.getMin());

        myStack.push(3);
        System.out.println(myStack.getMin());

        myStack.push(5);
        System.out.println(myStack.getMin());

        myStack.push(4);
        System.out.println(myStack.getMin());

        myStack.push(1);
        System.out.println(myStack.getMin());

        myStack.pop();
        System.out.println(myStack.getMin());

    }
}
