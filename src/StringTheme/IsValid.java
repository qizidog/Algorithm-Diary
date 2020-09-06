package StringTheme;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2020-09-06 10:38
 * @description :
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

    public static void main(String[] args) {
        System.out.println(IsValid.isValid("(((()))[])"));
        System.out.println(IsValid.isValid("(((())))[])"));
    }

}
