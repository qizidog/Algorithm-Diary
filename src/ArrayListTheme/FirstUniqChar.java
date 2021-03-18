package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-18 21:45
 * @description :
 * 剑指 Offer 50. 第一个只出现一次的字符
 *
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 **/

public class FirstUniqChar {
    public char firstUniqChar(String s) {
        int len;
        if(s==null || (len=s.length())==0) return ' ';

        int[] hash = new int[1<<16];
        char[] ca = s.toCharArray();

        for(char c : ca){
            hash[(int)c]++;
        }

        for(char c : ca){
            if(hash[(int)c]==1){
                return c;
            }
        }

        return ' ';
    }
}
