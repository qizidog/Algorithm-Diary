package exam;

import DynamicProcessing.Knapsack;

/**
 * @author : qizidog
 * @date : 2021-04-08 19:47
 * @description :
 * 完美世界笔试，背包问题
 **/

public class BagProblem {


    /*
        4  道具数量

        15  背包大小

        5 3 4 6  重量

        20 10 12 30  价值

        sout 62
     */
    // public static void main(String[] args) {
    //     Scanner sc = new Scanner(System.in);
    //
    //     int count = sc.nextInt();
    //
    //     int bagSize = sc.nextInt();
    //
    //     int[] weight = new int[count];
    //     for (int i = 0; i < count; i++) {
    //         weight[i] = sc.nextInt();
    //     }
    //
    //     int[] value = new int[count];
    //     for (int i = 0; i < count; i++) {
    //         value[i] = sc.nextInt();
    //     }
    //
    //
    //     // int count = 4;
    //     // int bagSize = 15;
    //     // int[] weight = new int[]{5,3,4,6};
    //     // int[] value = new int[]{20,10,12,30};
    //
    //     int ret = doProcess(count, bagSize, weight, value);
    //     System.out.println(ret);
    //
    // }

    private static int doProcess(int count, int bagSize, int[] weight, int[] value) {
        if (count<=0 || bagSize<0) return 0;

        return process(bagSize, weight, value, 0);
    }

    // 兄弟，dp不会改，傻缓存记得加啊
    private static int process(int bagSize, int[] weight, int[] value, int idx) {
        // if (bagSize<0 || bagSize<weight[idx]) return 0;

        // if (idx==weight.length-1) return value[idx];

        if (idx==weight.length-1) return bagSize>=weight[idx]?value[idx]:0;

        return Math.max(
                process(bagSize, weight, value, idx+1),  // 不要这个
                bagSize>=weight[idx]?(value[idx]+process(bagSize-weight[idx], weight, value, idx+1)):0  // 要这个
        );
    }

    public static int dp(int bagSize, int[] weight, int[] value){

        int len = weight.length;
        int[][] dp = new int[len+1][bagSize+1];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= bagSize; j++) {
                if(j >= weight[i]) {
                    dp[i][j] = Math.max(dp[i+1][j], value[i]+dp[i+1][j-weight[i]]);
                }else{
                    dp[i][j] = dp[i+1][j];
                }
            }
        }

        return dp[0][bagSize];
    }

    public static void main(String[] args) {
        int bagSize = 15;
        int[] weight = new int[]{3,4,6,2,4,5,0};
        int[] value = new int[]{10,12,30,26,32,21,12};

        // int bagSize = 5;
        // int[] weight = new int[]{3,4,6,0};
        // int[] value = new int[]{10,30,20,12};

        Knapsack knapsack = new Knapsack();
        System.out.println(knapsack.knapsack(weight, value, bagSize));
        System.out.println(knapsack.knapsack2(weight, value, bagSize));
        System.out.println(doProcess(100000, bagSize, weight, value));
        System.out.println(dp(bagSize, weight, value));
    }


}
