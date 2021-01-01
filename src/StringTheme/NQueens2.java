package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-17 22:09
 * @description :
 * 52. N皇后 II
 * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
 *
 * 示例:
 *
 * 输入: 4
 * 输出: 2
 * 解释: 4 皇后问题存在如下两个不同的解法。
 * [
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

/*
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 * 要求输出解的个数
 */
public class NQueens2 {
    public int totalNQueens(int n) {
        int count = 0;
        int[] solution = new int[n];
        count = traceBack(0, n, solution, 0);
        return count;
    }

    public int traceBack(int row, int n, int[] solution, int count){
        if(row==n){
            count++;
        }
        for(int i=0; i<n; i++){
            if(isOK(row, i, solution)){
                solution[row] = i;
                count = traceBack(row+1, n, solution, count);
            }
        }
        return count;
    }

    public boolean isOK(int row, int col, int[] solution){
        for(int i=0; i<row; i++){
            if(solution[i] == col){
                return false;
            }
            if(col-solution[i]==row-i){
                return false;
            }
            if(col-solution[i]==i-row){
                return false;
            }
        }
        return true;
    }
}
