package DynamicProcessing;

import java.util.HashMap;

/**
 * @author : qizidog
 * @date : 2021-03-29 20:34
 * @description :
 * 97. 交错字符串
 * 给定三个字符串 s1、s2、s3，请你帮忙验证 s3 是否是由 s1 和 s2 交错 组成的。
 *
 * 两个字符串 s 和 t 交错 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串：
 *
 * s = s1 + s2 + ... + sn
 * t = t1 + t2 + ... + tm
 * |n - m| <= 1
 * 交错 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
 * 提示：a + b 意味着字符串 a 和 b 连接。
 *
 * 示例 1：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出：true
 *
 * 示例 2：
 * 输入：s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出：false
 *
 * 示例 3：
 * 输入：s1 = "", s2 = "", s3 = ""
 * 输出：true
 *
 * 提示：
 * 0 <= s1.length, s2.length <= 100
 * 0 <= s3.length <= 200
 * s1、s2、和 s3 都由小写英文字母组成
 **/

public class IsInterleave {

    public boolean isInterleave(String s1, String s2, String s3) {
        if(s1.length()+s2.length()!=s3.length()){
            return false;
        }
        if("".equals(s1) && "".equals(s2) && "".equals(s3)){
            return true;
        }
        return process(s1, s2, s3, 0, 0);
    }

    // 递归求解，超出时间限制
    public static boolean process(String s1, String s2, String s3, int idx1, int idx2){
        if(idx1+idx2==s3.length()) return true;

        char c3 = s3.charAt(idx1+idx2);  // 之所以不传s3的idx，是遵循左神的原则："可变参数的个数，能少则少"

        boolean r1=false;
        boolean r2=false;
        if(idx1<s1.length() && s1.charAt(idx1)==c3){
            r1 = process(s1,s2,s3, idx1+1, idx2);
        }
        if(idx2<s2.length() && s2.charAt(idx2)==c3){
            r2 = process(s1,s2,s3, idx1, idx2+1);
        }
        return r1 || r2;
    }

    // 加入傻缓存，通过时间限制
    private HashMap<String, Boolean> map = new HashMap<>();
    public boolean process(String s1, String s2, String s3, int idx1, int idx2, int idx3){
        if(idx3==s3.length()) return true;

        String key = idx1+"&"+idx2;
        if(map.containsKey(key)){
            return map.get(key);
        }

        char c3 = s3.charAt(idx3);

        boolean r1 = false;
        boolean r2 = false;
        if(idx1<s1.length() && s1.charAt(idx1)==c3){
            r1 = process(s1,s2,s3, idx1+1, idx2, idx3+1);
        }
        if(r1){
            map.put(key, r1);
            return r1;
        }
        if(idx2<s2.length() && s2.charAt(idx2)==c3){
            r2 = process(s1,s2,s3, idx1, idx2+1, idx3+1);
        }
        map.put(key, r2);
        return r2;
    }

    // 睡了一觉后自己写的dp
    public static boolean dp(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        boolean[][] dp = new boolean[len1+1][len2+1];

        dp[len1][len2] = true;

        for (int i = len1; i >= 0; i--) {
            for(int j = len2; j >= 0; j--){
                if(i==len1 && j==len2) continue;

                char c3 = s3.charAt(i + j);
                boolean r1 = false;
                boolean r2 = false;
                if(i<s1.length() && s1.charAt(i)==c3){
                    r1 = dp[i+1][j];
                }
                if(j<s2.length() && s2.charAt(j)==c3){
                    r2 = dp[i][j+1];
                }
                dp[i][j] = r1 || r2;
            }
        }

        return dp[0][0];
    }

    // 睡了一觉后自己写的滚动数组dp
    public static boolean dpp(String s1, String s2, String s3) {
        int len1 = s1.length();
        int len2 = s2.length();
        boolean[] dp = new boolean[len1+1];

        dp[len1] = true;

        for(int j = len2; j >= 0; j--){  // 注意这里交换了顺序
            for (int i = len1; i >= 0; i--) {
                if(i==len1 && j==len2) continue;

                char c3 = s3.charAt(i + j);
                boolean r1 = false;
                boolean r2 = false;
                if(i<s1.length() && s1.charAt(i)==c3){
                    r1 = dp[i+1];
                }
                if(j<s2.length() && s2.charAt(j)==c3){
                    r2 = dp[i];
                }
                dp[i] = r1 || r2;
            }
        }

        return dp[0];
    }

    // 动态规划 时间复杂度和空间复杂度都是 O(nm)
    public static boolean dp1(String s1, String s2, String s3){
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[][] f = new boolean[n + 1][m + 1];

        f[0][0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[i][j] = f[i][j] || (f[i - 1][j] && s1.charAt(i - 1) == s3.charAt(p));
                }
                if (j > 0) {
                    f[i][j] = f[i][j] || (f[i][j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }
        return f[n][m];
    }

    // 动态规划，滚动数组优化版 时间复杂度：O(nm)，空间复杂度：O(m)
    public boolean dp2(String s1, String s2, String s3) {
        int n = s1.length(), m = s2.length(), t = s3.length();

        if (n + m != t) {
            return false;
        }

        boolean[] f = new boolean[m + 1];

        f[0] = true;
        for (int i = 0; i <= n; ++i) {
            for (int j = 0; j <= m; ++j) {
                int p = i + j - 1;
                if (i > 0) {
                    f[j] = f[j] && s1.charAt(i - 1) == s3.charAt(p);
                }
                if (j > 0) {
                    f[j] = f[j] || (f[j - 1] && s2.charAt(j - 1) == s3.charAt(p));
                }
            }
        }

        return f[m];
    }

    public static void main(String[] args) {
        String s1 = "aabcc";
        String s2 = "dbbca";
        // String s3 = "aadbbcbcac";
        String s3 = "aadbbcbccc";

        System.out.println(process(s1, s2, s3, 0, 0));
        System.out.println(dpp(s1, s2, s3));
    }
}
