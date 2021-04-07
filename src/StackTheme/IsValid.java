package StackTheme;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2020-09-06 10:38
 * @description :
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 *
 * 输入: "()"
 * 输出: true
 * 示例 2:
 *
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 *
 * 输入: "(]"
 * 输出: false
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class IsValid {
    public static boolean isValid(String s) {
        // 考虑几种特殊情况
        if(s.equals("")){
            return true;
        }
        if(s==null || s.length()%2 != 0){
            return false;
        }

        ArrayList<Character> stack = new ArrayList<Character>();
        for (int i=0; i<s.length(); i++){
            if(stack.size()==0){
                stack.add(s.charAt(i));
            }else if(isCouple(stack.get(stack.size()-1), s.charAt(i))){
                stack.remove(stack.size()-1);
            }else{
                stack.add(s.charAt(i));
            }
        }

        return stack.size()==0;
    }

    static boolean isCouple(char a, char b){
        return a=='('&&b==')' || a=='['&&b==']' || a=='{'&&b=='}';
    }

    public static boolean isValid2(String s) {
        if(s==null || s.length()%2!=0){
            return false;
        }

        LinkedList<Character> stack = new LinkedList<Character>();
        for (int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(' || s.charAt(i)=='[' || s.charAt(i)=='{'){
                stack.push(s.charAt(i));
            //    这里是个大坑，必须要强制转型，不然可能自动拆箱导致空指针异常
            // }else if(stack.peek()==(Character) getCouple(s.charAt(i))){
            //    或者这样更妥当
            }else if(((Character) getCouple(s.charAt(i))).equals(stack.peek())){
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.size()==0;
    }

    static char getCouple(char c){
        switch(c){
            case ')': return '(';
            case ']': return '[';
            case '}': return '{';
            default: return ' ';
        }
    }

    public static void main(String[] args) {
        System.out.println(IsValid.isValid("(((()))[])"));
        System.out.println(IsValid.isValid("(((())))[])"));

        System.out.println(IsValid.isValid2("(((()))[])"));
        System.out.println(IsValid.isValid2("(((())))[])"));
        System.out.println(IsValid.isValid2("){"));

    }

}
