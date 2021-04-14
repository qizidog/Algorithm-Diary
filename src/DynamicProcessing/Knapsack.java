package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2021-03-10 21:06
 * @description :
 * 背包问题，给定w, v两个数组，分别代表物体的重量和价值
 * 要求拿取物体的总重不超过bag，求能够获得的最大价值
 **/

public class Knapsack {

    public static int knapsack(int[] w, int[] v, int bag){
        return knapsack(w, v, bag, 0);
    }

    // 暴力递归
    public static int knapsack(int[] w, int[] v, int bag, int idx){
        // 如果到达最后一个位置
        if (idx==w.length-1) return bag>=w[idx]?v[idx]:0;


        int ret1 = knapsack(w, v, bag, idx+1);  // 不要这个
        int ret2 = bag<w[idx]?0:(v[idx]+knapsack(w, v, bag-w[idx], idx+1));  // 要这个（前提是要得起）

        return Math.max(ret1, ret2);
    }

    // 动态规划
    public static int knapsack2(int[] w, int[] v, int bag){

        int[][] ret = new int[w.length+1][bag+1];

        // 最后一行全部为0，在声明数组时自动完成了
        for (int row = w.length-1; row >= 0; row--) {
            for (int col = 0; col <= bag; col++) {
                // 此句是下面几句的缩写
                // ret[row][col] = Math.max(ret[row+1][col],
                //         w[row]<=col ? (ret[row+1][col-w[row]] + v[row]) : 0);

                // 不取
                int r1 = ret[row+1][col];
                // 取
                int r2;
                if (w[row]<=col){
                    r2 = ret[row+1][col-w[row]] + v[row];
                }else{
                    r2 = 0;
                }
                ret[row][col] = Math.max(r1, r2);
            }

        }
        return ret[0][bag];
    }

    public static void main(String[] args) {
        int[] w = new int[]{5, 4, 2, 3};
        int[] v = new int[]{6, 7, 3, 5};
        int bag = 10;
        System.out.println(knapsack(w, v, bag));
        System.out.println(knapsack2(w, v, bag));
    }
}
