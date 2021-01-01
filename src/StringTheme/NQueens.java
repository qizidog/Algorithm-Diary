package StringTheme;

import java.util.*;

/**
 * @author : qizidog
 * @date : 2020-09-17 22:09
 * @description :
 * 51. N 皇后
 * 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
 *
 * 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
 *
 *  
 *
 * 示例：
 *
 * 输入：4
 * 输出：[
 *  [".Q..",  // 解法 1
 *   "...Q",
 *   "Q...",
 *   "..Q."],
 *
 *  ["..Q.",  // 解法 2
 *   "Q...",
 *   "...Q",
 *   ".Q.."]
 * ]
 * 解释: 4 皇后问题存在两个不同的解法。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/n-queens
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

/*
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
 * 要求输出所有的解
 */
public class NQueens {
    // 官方解答，道理我都懂，就是自己写不出来
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        int[] queens = new int[n];  // 用来存放queen列数的数组
        Arrays.fill(queens, -1);
        // 三个set表示queen不能存放的位子
        Set<Integer> columns = new HashSet<Integer>();
        Set<Integer> diagonals1 = new HashSet<Integer>();
        Set<Integer> diagonals2 = new HashSet<Integer>();
        backtrack(solutions, queens, n, 0, columns, diagonals1, diagonals2);
        return solutions;
    }

    // row表示正在处理的当前的行数
    public void backtrack(List<List<String>> solutions, int[] queens, int n, int row,
                          Set<Integer> columns, Set<Integer> diagonals1, Set<Integer> diagonals2) {
        if (row == n) {  // 如果已经找到最后一行的话，将该结果添加到结果集
            List<String> board = generateBoard(queens, n);  // 将数组转换成题目要求的形式
            solutions.add(board);  // 添加到结果集
        } else {
            // 从第0列开始遍历
            for (int i = 0; i < n; i++) {
                if (columns.contains(i)) {
                    continue;
                }
                int diagonal1 = row - i;
                if (diagonals1.contains(diagonal1)) {
                    continue;
                }
                int diagonal2 = row + i;
                if (diagonals2.contains(diagonal2)) {
                    continue;
                }

                // 如果都不包含的话，说明可以放一个棋子
                queens[row] = i;
                columns.add(i);
                diagonals1.add(diagonal1);
                diagonals2.add(diagonal2);

                backtrack(solutions, queens, n, row + 1, columns, diagonals1, diagonals2);

                queens[row] = -1;
                columns.remove(i);
                diagonals1.remove(diagonal1);
                diagonals2.remove(diagonal2);
            }
        }
    }

    // 用于将数组转换成题目要求的形式
    public List<String> generateBoard(int[] queens, int n) {
        List<String> board = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[i]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }

    // 测试
    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        List<List<String>> lists1 = nQueens.solveNQueens(5);
        System.out.println(lists1);
        System.out.println(lists1.size());


        List<List<String>> lists2 = nQueens.solveNQueens2(5);
        System.out.println(lists2);
        System.out.println(lists2.size());
    }

    /*=====================================自己实现NQueens回溯算法===============================================*/

    public List<List<String>> solveNQueens2(int n) {
        List<List<String>> solutions = new ArrayList<List<String>>();
        // List<int[]> solutions = new ArrayList<int[]>();
        backtrack2(n, 0, new int[n], solutions);
        return solutions;
    }

    /**
        @param n: 一共有多少行
        @param row: 当前是第几行，0表示第一行
        @param solution: 前面几行的布置情况
        @param solutions: 可行解
     */
    public void backtrack2(int n, int row, int[] solution, List<List<String>> solutions){
        // 如果之前的n行都构建成功了
        if (row==n){
            solutions.add(getSolutionBoard(solution));
            // solutions.add(solution);
        }

        // 从第i=0列开始给我遍历！！！
        for (int i=0; i<n; i++){
            if (isOK(solution, row, i)){
                solution[row] = i;  // 放一个Queen
                backtrack2(n, row+1, solution, solutions);
            }
            // 如果遍历完一行都没有合适的会退出递归，返回上一级
        }

    }

    public boolean isOK(int[] solution, int row, int col){
        for (int i=0; i<row; i++){
            if (col == solution[i]){
                return false;
            }
            if (col-solution[i]==row-i || col-solution[i]==i-row){
                return false;
            }
        }
        return true;
    }

    // 构建题目需要的类型
    public List<String> getSolutionBoard(int[] solution){
        // 每个解
        ArrayList<String> ans = new ArrayList<String>();
        for (int i : solution) {
            // 一个解的每一行
            StringBuilder row = new StringBuilder();
            for (int j=0; j<solution.length; j++){
                if (i!=j){
                    row.append('.');
                }else{
                    row.append('Q');
                }
            }
            ans.add(row.toString());
        }
        return ans;
    }

}
