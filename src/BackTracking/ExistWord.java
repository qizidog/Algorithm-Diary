package BackTracking;

/**
 * @author : qizidog
 * @date : 2020-09-27 12:28
 * @description :
 * 79. 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 当心超出运行时间限制！！！
 **/

public class ExistWord {
    public boolean exist(char[][] board, String word) {
        boolean[] ret = new boolean[1];
        if(word==null) return ret[0];
        boolean[][] isUsed = new boolean[board.length][board[0].length];
        char[] wordChars = word.toCharArray();
        backtracking(board, wordChars, 0, ret, isUsed);
        return ret[0];
    }

    public void backtracking(char[][] board, char[] wordChars, int index,
                             boolean[] ret, boolean[][] isUsed){
        if(index==wordChars.length){
            return;
        }

        int rows = board.length;
        int cols = board[0].length;
        // 遍历网格，找到第一个相同字符的坐标
        for(int i=0; i<rows; i++){
            for(int j=0; j<cols; j++){
                if(wordChars[index]==board[i][j]){
                    isUsed[i][j] = true;
                    backtracking(board, wordChars, 1, i, j, ret, isUsed);
                    isUsed[i][j] = false;
                }
            }
        }
    }

    public boolean backtracking(char[][] board, char[] wordChars, int index,
                                int id, int jd, boolean[] ret, boolean[][] isUsed){
        if(index==wordChars.length){
            ret[0] = true;
            return true;
        }
        char temp = wordChars[index];
        if(id>0 && !isUsed[id-1][jd] && temp==board[id-1][jd]){
            isUsed[id-1][jd] = true;
            // 如果找到了就赶紧返回，别再找其他的解了
            if(backtracking(board, wordChars, index+1, id-1, jd, ret, isUsed)) return true;
            isUsed[id-1][jd] = false;
        }
        if(jd>0 && !isUsed[id][jd-1] && temp==board[id][jd-1]){
            isUsed[id][jd-1] = true;
            if(backtracking(board, wordChars, index+1, id, jd-1, ret, isUsed)) return true;
            isUsed[id][jd-1] = false;
        }
        if(jd<board[0].length-1 && !isUsed[id][jd+1] && temp==board[id][jd+1]){
            isUsed[id][jd+1] = true;
            if(backtracking(board, wordChars, index+1, id, jd+1, ret, isUsed)) return true;
            isUsed[id][jd+1] = false;
        }
        if(id<board.length-1 && !isUsed[id+1][jd] && temp==board[id+1][jd]){
            isUsed[id+1][jd] = true;
            if(backtracking(board, wordChars, index+1, id+1, jd, ret, isUsed)) return true;
            isUsed[id+1][jd] = false;
        }
        return false;
    }
}
