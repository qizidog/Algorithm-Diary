package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2021-03-20 10:29
 * @description :
 * 左神练习题，象棋跑马
 * 象棋棋盘（0, 0）位置有一个马，马走日字，指定必须走完 k 步，
 * 问走到（x, y）这个坐标的方法有多少种？棋盘大小10行9列，不要越界。
 **/

public class RunHorseInChess {

    // 暴力递归解（x行，y列）
    public static int runHorseInChess(int x, int y, int k){

        int ret = 0;

        if (x>9 || x<0 || y>8 || y<0 || k<0) return 0;

        if (k==0 && x==0 && y==0) return 1;

        ret = runHorseInChess(x-2, y-1, k-1)
                + runHorseInChess(x-2, y+1, k-1)
                + runHorseInChess(x-1, y-2, k-1)
                + runHorseInChess(x-1, y+2, k-1)
                + runHorseInChess(x+1, y-2, k-1)
                + runHorseInChess(x+1, y+2, k-1)
                + runHorseInChess(x+2, y-1, k-1)
                + runHorseInChess(x+2, y+1, k-1);

        return ret;
    }

    // 动态规划求解
    public static int runHorseInChess2(int x, int y, int k){
        if (x>9 || x<0 || y>8 || y<0 || k<0) return 0;

        // k层，10行，9列
        int[][][] mtx = new int[k+1][10][9];
        mtx[0][0][0] = 1;

        // i对应x行，j对应y列
        for (int level = 1; level <= k; level++){
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 9; j++) {
                    mtx[level][i][j] = getVal(mtx, i-2, j-1, level-1)
                                     + getVal(mtx, i-2, j+1, level-1)
                                     + getVal(mtx, i-1, j-2, level-1)
                                     + getVal(mtx, i-1, j+2, level-1)
                                     + getVal(mtx, i+1, j-2, level-1)
                                     + getVal(mtx, i+1, j+2, level-1)
                                     + getVal(mtx, i+2, j-1, level-1)
                                     + getVal(mtx, i+2, j+1, level-1);
                }
            }
        }

        return mtx[k][x][y];
    }

    private static int getVal(int[][][] mtx, int x, int y, int k){
        if (x>9 || x<0 || y>8 || y<0 || k<0) return 0;
        return mtx[k][x][y];
    }

    public static void main(String[] args) {
        int x = 6;
        int y = 8;
        int k = 10;
        System.out.println(runHorseInChess(x, y, k));
        System.out.println(runHorseInChess2(x, y, k));
    }





}
