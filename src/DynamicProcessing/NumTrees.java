package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2021-03-25 13:27
 * @description :
 * 96. 不同的二叉搜索树
 * 给定一个整数 n，求以 1 ... n 为节点组成的二叉搜索树有多少种？
 *
 * 示例:
 *
 * 输入: 3
 * 输出: 5
 * 解释:
 * 给定 n = 3, 一共有 5 种不同结构的二叉搜索树:
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees/
 *
 * 结合TreeTheme.GenerateTrees.java一起练习，这个是动态规划，那个是递归回溯
 **/

public class NumTrees {
    // 二维的动态规划，搞了我好久的
    public int numTrees2(int n){
        if(n<=0) return 0;

        int[][] dp = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<=i; j++){
                dp[i][j] = 1;
            }
        }

        for(int i=n-2; i>=0; i--){
            for(int j=i+1; j<n; j++){
                dp[i][j] = 0;
                for(int p=i; p<=j; p++){
                    dp[i][j] += (getDp(dp, i, p-1) * getDp(dp, p+1, j));
                }
            }
        }
        return dp[0][n-1];
    }

    public int getDp(int[][] dp, int i, int j){
        if(i<0 || i>dp.length-1 || j<0 || j>dp.length-1){
            return 1;
        }else{
            return dp[i][j];
        }
    }

    // 一维的动态规划其实就能够解决，
    public static int numTrees1(int n){
        if(n<=0) return 0;
        if(n==1) return 1;
        if(n==2) return 2;

        int[] dp = new int[n+1];  // 连续的i个数，能够组成的二叉搜索树有dp[i]种

        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            for (int j=0; j<i; j++){
                dp[i] += (dp[j]*dp[i-1-j]);
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(numTrees1(9));  // 4862
    }

}
