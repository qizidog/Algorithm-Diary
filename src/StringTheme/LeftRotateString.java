package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-11 15:57
 * @description :
 * JZ43 左旋字符串
 **/

public class LeftRotateString {
    public static String leftRotateString(String str,int n) {
        if(str.length()==0) return "";
        if (n>=0){
            n = n % str.length();
        } else{
            n = n % str.length() + str.length();
        }
        return str.substring(n)+str.substring(0, n);
    }

    public static void main(String[] args) {
        System.out.println(LeftRotateString.leftRotateString("abcXYZdef", 3));
        System.out.println(LeftRotateString.leftRotateString("abcXYZdef", 13));
        System.out.println(LeftRotateString.leftRotateString("", 13));
        System.out.println(LeftRotateString.leftRotateString("abcXYZdef", -1));
        System.out.println(LeftRotateString.leftRotateString("abcXYZdef", -10));
        System.out.println(LeftRotateString.leftRotateString("abcXYZdef", 0));
//        System.out.println(""+-1%8);
//        System.out.println(""+-11%8);
    }
}
