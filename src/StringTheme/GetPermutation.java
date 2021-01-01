package StringTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2020-09-05 17:29
 * @description :
 * 60. 第k个排列（阶乘）
 * 这道题做了我一个下午，我太南了
 *
 * 给出集合 [1,2,3,...,n]，其所有元素共有 n! 种排列。
 *
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 *
 * "123", "132", "213", "231", "312", "321"
 * 给定 n 和 k，返回第 k 个排列。
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 3
 * 输出："213"
 * 示例 2：
 *
 * 输入：n = 4, k = 9
 * 输出："2314"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/permutation-sequence
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class GetPermutation {
    public static String getPermutation(int n, int k) {
        // 准备一个用来做除数的数组
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        StringBuilder ret = new StringBuilder();
        int[] rowNums = new int[n];  // 这个数组用来获取行数和n的对应关系
        for (int i = 0; i < n; i++) {
            rowNums[i] = 1;
        }

        for (int i = 1; i <= n; i++) {
            int p = (k - 1) / factorial[n - i] + 1;
            // 如果求余不为0，则将余数赋值给k，否则将除数赋值给k
            // 代表位于第p行的第k个数
            k = k % factorial[n - i]==0?factorial[n-i]:k % factorial[n - i];
            for (int j = 0; j < n; j++) {
                p = p - rowNums[j];
                if (p == 0) {
                    rowNums[j] = 0;
                    ret.append(j + 1);
                    break;
                }
            }
        }

        return ret.toString();
    }

    public static void main(String[] args) {
        String permutation = GetPermutation.getPermutation(6, 360);
        System.out.println(permutation);
    }
}

class Solution {
    // 官方解法
    public static String getPermutation(int n, int k) {
        // 这个数组是拿来存储被别人除的阶乘的
        int[] factorial = new int[n];
        factorial[0] = 1;
        for (int i = 1; i < n; ++i) {
            factorial[i] = factorial[i - 1] * i;
        }

        --k;  // 这个地方还没搞懂
        // 只有行数n才会影响最终计算的结果

        StringBuilder ans = new StringBuilder();

        int[] valid = new int[n + 1];
        Arrays.fill(valid, 1);
        for (int i = 1; i <= n; ++i) {
            // 求出目标位于第order行
            int order = k / factorial[n - i] + 1;
            k %= factorial[n - i];

            for (int j = 1; j <= n; ++j) {
                order -= valid[j];
                if (order == 0) {
                    ans.append(j);
                    valid[j] = 0;
                    break;
                }
            }
            // 求余，看这个数位于第n行的第几个
//            k %= factorial[n - i];
        }
        return ans.toString();
    }

}
