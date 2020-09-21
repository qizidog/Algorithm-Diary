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
