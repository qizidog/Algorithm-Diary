package StringTheme;

import java.util.*;

/**
 * @author : qizidog
 * @date : 2020-09-17 22:09
 * @description :
 * 51. N 皇后
 *
 **/

/*
 * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击
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


    // 自己实现NQueens回溯算法
    public List<List<String>> solveNQueens2(int n) {
        List<int[]> solutions = new ArrayList<int[]>();



        return getSolutionBoard(solutions);
    }

    // 构建题目需要的类型
    public List<List<String>> getSolutionBoard(List<int[]> solutions){
        // 所有解
        List<List<String>> solutionBoard = new ArrayList<List<String>>();
        for (int[] solution : solutions) {
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
            solutionBoard.add(ans);
        }

        return solutionBoard;
    }





}
