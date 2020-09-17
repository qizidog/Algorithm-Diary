package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-17 20:18
 * @description :
 * 415. 字符串相加
 **/

/*
    1.num1 和num2 的长度都小于 5100
    2.num1 和num2 都只包含数字 0-9
    3.num1 和num2 都不包含任何前导零
    4.你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 */
public class AddStrings {
    // 第一直觉解法，其实有问题，当超出int或long的数字大小时会溢出
    public String addStrings(String num1, String num2) {
        int ret1 = 0;
        for(int i=0; i<num1.length(); i++){
            ret1 = ret1*10 + (num1.charAt(i)-'0');
        }
        int ret2 = 0;
        for(int i=0; i<num2.length(); i++){
            ret2 = ret2*10 + (num2.charAt(i)-'0');
        }
        return ret1+ret2+"";
    }

    // 看来似乎只能通过字符串来实现
    public String addStrings2(String num1, String num2) {
        int len1 = num1.length()-1;
        int len2 = num2.length()-1;
        int grow = 0;
        StringBuilder sb = new StringBuilder();
        while(len1>=0||len2>=0){
            int part_a = len1>=0?(num1.charAt(len1)-'0'):0;
            int part_b = len2>=0?(num2.charAt(len2)-'0'):0;
            sb.append((part_a+part_b+grow)%10);
            grow = (part_a+part_b+grow)>=10?1:0;
            len1--;
            len2--;
        }
        if(grow>0) sb.append(grow);
        return sb.reverse().toString();
    }
}
