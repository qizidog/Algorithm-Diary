package exam;

import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-04-14 18:57
 * @description :
 * 华为笔试，第1题
 * 反转字符串，括号内的优先反转
 * 例如：
 *      输入：
 *          (u(love)i)
 *      输出
 *          iloveu
 **/

public class Code_4_14_1 {
    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // String str = sc.nextLine();

        String str = "(u(love)i)";

        String ret = reverse(str);

        System.out.println(ret);
    }

    // (u(love)i)
    // iloveu
    private static String reverse(String str) {
        if (str==null || str.equals("")) return str;

        Stack<Character> stack = new Stack<>();
        LinkedList<Character> queue = new LinkedList<>();

        char[] chars = str.toCharArray();

        for (char c : chars) {
            if (c!=')'){
                stack.push(c);
            }else{
                while (!stack.isEmpty() && stack.peek()!='('){
                    queue.add(stack.pop());
                }
                if (!stack.isEmpty()) {
                    stack.pop();
                }
                while (!queue.isEmpty()){
                    stack.push(queue.pollFirst());
                }
            }
        }
        StringBuilder ret = new StringBuilder();
        stack.forEach(ret::append);
        return ret.toString();
    }
}
