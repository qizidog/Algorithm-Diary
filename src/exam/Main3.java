package exam;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-04-14 19:26
 * @description :
 * 华为笔试，第3题
 *
 *   无线设备传输过程中，数据经常需要通过各种中继设备进行中转。
 *   现有某段传输路径，每隔1km放置1个中继设备用于数据中转，
 *   现用一数组来描述包括起始点的所有中继设备的最大传输距离（单位km）。
 *   求从起点到终点，能完成信号传输的最少中转次数；
 *
 *   入参通过多行字符串方式表示，例如：
 *   4
 *   2 3 1 1
 *   字符串含义：
 *   1.第一行表示总共中转设备台数：总计4台；
 *   2.第二行表示各中转设备的最大传输能力：
 *   第一台设备最大传输距离为2km,第二台设备最大传输距离为3km，第三台设备最大传输距离为1km，第四台设备最大传输距离为1km。
 *
 *   输出从起点到终点，能完成信号传输的最少中转次数； 上例答案为2
 *
 **/

public class Main3 {

    public static void main(String[] args) {
        // Scanner sc = new Scanner(System.in);
        // int num = sc.nextInt();
        //
        // int[] input = new int[num];
        // for (int i = 0; i < num; i++) {
        //     input[i] = sc.nextInt();
        // }

        // int[] input = new int[]{2,3,1,1};
        // int[] input = new int[]{7,1,65535,1,1,1,1,1,1};

        // int ret = getNum(input);
        // System.out.println(ret);
        // int ret2 = process(input, 0);
        // System.out.println(ret2);

        for (int i = 0; i < 1000; i++) {
            int[] randomArr = getRandomArr(1, 200, 20);
            if(getNum(randomArr)!=process(randomArr, 0)){
                System.out.println("GO WRONG! "+Arrays.toString(randomArr));
                break;
            }
        }
        System.out.println("FIN");
    }

    private static int getNum(int[] input) {
        int len;
        if(input==null || (len = input.length)==0) return 0;

        int[] dp = new int[len];  // 可简化，后面再做，好像不能简化
        dp[len-1] = 1;  // 显式声明

        for(int i=len-2; i>=0; i--){
            int min = Integer.MAX_VALUE;
            for(int j=1; j<=input[i]; j++){
                if(i+input[i]>=len){
                    min = 0;
                    break;
                }else{
                    min = Math.min(min, dp[i+j]);
                }
            }
            dp[i] = min + 1;
        }

        return dp[0];
    }

    private static int process(int[] input, int idx){
        int len = input.length;

        if (idx >= len){
            return 0;
        }

        int min = Integer.MAX_VALUE;
        for(int i=1; i<=input[idx]; i++){
            min = Math.min(min, process(input, idx+i));
        }
        return min+1;
    }


    public static int[] getRandomArr(int min, int max, int maxLen){
        int len = (int)(Math.random()*maxLen);
        int[] ret = new int[len];
        for (int i = 0; i < len; i++) {
            ret[i] = min+(int)(Math.random()*(max-min));
        }
        return ret;
    }


}
