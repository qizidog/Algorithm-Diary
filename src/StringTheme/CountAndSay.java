package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-08 10:07
 * @description :
 * 38. 外观数列
 **/

/*
    给定一个正整数 n（1 ≤ n ≤ 30），输出外观数列的第 n 项。
    注意：整数序列中的每一项将表示为一个字符串。
 */
public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder ret = new StringBuilder("1");
        for (int i=1; i<n; i++){
            ret = getS(ret);
        }
        return ret.toString();
    }

    public StringBuilder getS(StringBuilder s){
        int num = 1;
        StringBuilder ret = new StringBuilder();
        s.append(" ");
        for(int i=0; i<s.length()-1; i++){
            if(s.charAt(i)!=s.charAt(i+1)){
                ret.append(num).append(s.charAt(i));
                num = 1;
            }else{
                num++;
            }
        }
        return ret;
    }
}
