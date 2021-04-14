package exam;

import java.util.Scanner;

/**
 * @author : qizidog
 * @date : 2021-04-11 19:37
 * @description :
 * 百度笔试，第3题
 *
 * 给定两个字符串S, T，问，指定做num次 "子串位置" 互换，有几种方式能够得到T
 * 由于结果可能特别大，所以要求 模10000000007 后输出
 *
 * 例 1： S = "aaca"; T = "caaa"; num = 2;
 * 有两种方法:
 * 1. aac a -> aaa c -> caaa
 * 2. a aca -> a caa -> caaa
 * 所以返回 2
 *
 * 例 2： S = "ac"; T = "ca"; num = 2;
 * 无法做到
 * 返回 0
 **/

public class Code_4_11_3 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // String S = sc.nextLine();
        // String T = sc.nextLine();
        // int num = sc.nextInt();

        String S = "aac";
        String T = "aca";
        int num = 2;

        int count = getNumber(S, T, num);

        System.out.println(count%10000000007L);
    }

    // 必然是暴力递归改动态规划
    private static int getNumber(String S, String T, int num) {
        int length = S.length();

        if (S.equals(T) && num==0){
            return 1;
        }
        if (num<=0) return 0;

        int ret = 0;
        for (int i = 1; i < length; i++) {
            ret += getNumber(swap(S, i), T, num-1);
        }

        return ret;
    }

    // String S = "aaca";
    // 其实违背了每一个参数不能比int更复杂的要求
    private static String swap(String S, int i){
        return S.substring(i) + S.substring(0, i);
    }


    // todo 改不出来，后面再写吧
    private static int dp(String S, String T, int num){
        int len = S.length();

        int[] dp = new int[num+1];
        dp[0] = S.equals(T) ? 1 : 0;

        for (int k = 1; k < len; k++) {
            for (int i = 1; i < len; i++) {
                // dp[k] += getNumber(swap(S, i), T, num-1);
            }
        }

        return dp[num];
    }

    private static int getDP(int[][] dp, int i, int j){
        if (j<0) return 0;
        return dp[i][j];
    }
}
