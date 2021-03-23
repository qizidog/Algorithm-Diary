package BackTracking;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-03-23 15:05
 * @description :
 * 剑指 Offer 46. 把数字翻译成字符串
 *
 * 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 * 提示：
 * 0 <= num < 231
 **/

public class TranslateNum {
    // 回溯法
    public static int translateNum(int num) {
        if(num<0) return 0;
        char[] arr = (""+num).toCharArray();
        System.out.println(Arrays.toString(arr));
        return process(arr, 0);
    }

    public static int process(char[] arr, int cur){
        int len = arr.length;
        if(cur >= len-1) return 1;

        if((arr[cur]-'0')==1 && cur<len-1){
            return process(arr, cur+1) + process(arr, cur+2);
        } else if((arr[cur]-'0')==2 && cur<len-1 && (arr[cur + 1]-'0') <6){
            return process(arr, cur+1) + process(arr, cur+2);
        } else{
            return process(arr, cur+1);
        }
    }

    // 动态规划求解，空间O(n)
    public static int dynamic(int num){
        char[] arr = (""+num).toCharArray();

        int len = arr.length;
        int[] list = new int[len+1];

        list[len] = 1;
        list[len-1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            if((arr[i]-'0')==1 && i<len-1){
                list[i] = list[i+1] + list[i+2];
            } else if((arr[i]-'0')==2 && i<len-1 && (arr[i + 1]-'0') <6){
                list[i] = list[i+1] + list[i+2];
            } else{
                list[i] = list[i+1];
            }
        }
        return list[0];
    }


    // 再写一次动态规划，空间O(1)（因为下一次计算仅依赖于前两次计算，故可以省略缓存数组）
    public static int dynamic2(int num){
        int p = 1; int q = 1;

        String str = num+"";
        int len = str.length();
        int cur = len-2;
        while (cur>=0) {
            String tmp = str.substring(cur, cur + 2);
            // 只有小于25，且不以0开头的两位数才能组合在一起用
            if (tmp.compareTo("25") < 1 && tmp.compareTo("10")>=0) {
                int temp = q;
                q = p;
                p = q + temp;
            } else{
                q = p;
            }
            cur--;
        }

        return p;
    }



    public static void main(String[] args) {
        int num = 223338216;
        System.out.println(translateNum(num));
        System.out.println(dynamic(num));
        System.out.println(dynamic2(num));
    }
}
