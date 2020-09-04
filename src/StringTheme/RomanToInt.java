package StringTheme;

import java.util.HashMap;

/**
 * @author : qizidog
 * @date : 2020-09-02 00:08
 * @description :
 * 13. 罗马数字转整数
 **/

public class RomanToInt {
    public int romanToInt(String s) {
        HashMap<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('I', 1);
            put('V', 5);
            put('X', 10);
            put('L', 50);
            put('C', 100);
            put('D', 500);
            put('M', 1000);
        }};

        int ret = 0;
        int temp1 = 0;
        int temp2 = 0;
        for(int i=0; i<s.length()-1; i++){
            temp1 = map.get(s.charAt(i));
            temp2 = map.get(s.charAt(i+1));
            if(temp1>temp2){
                ret += temp1;
            }else{
                ret -= temp1;
            }
        }
        return ret+temp2;
    }

    public int romanToInt2(String s) {
        int sum = 0;
        int preNum = getValue(s.charAt(0));
        for(int i = 1;i < s.length(); i ++) {
            int num = getValue(s.charAt(i));
            if(preNum < num) {
                sum -= preNum;
            } else {
                sum += preNum;
            }
            preNum = num;
        }
        sum += preNum;
        return sum;
    }

    private int getValue(char ch) {
        switch(ch) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default: return 0;
        }
    }

}
