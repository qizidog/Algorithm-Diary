package exam;

import java.util.Scanner;

/**
 * @author : qizidog
 * @date : 2021-04-11 19:37
 * @description :
 * 百度笔试，第2题
 *
 * 输出可能的比赛名词
 *
 * 输入:
 *     4 3 4  选手数，场次数，牛牛编号
 *     1 2    1号赢了2号
 *     2 3    2号赢了3号
 *     4 3    4号赢了3号
 * 输出:
 *     1 2 3
 * 解释:
 *     1 -> 2 -> 3
 *          4 -> 3
 *     所以牛牛可能是第一名，第二名，或者第三名
 **/

public class Code_4_11_2 {

    /*  输入
        4 3 4  选手数，场次数，牛牛编号
        1 2    1号赢了2号
        2 3    2号赢了3号
        4 3    4号赢了3号
    */
    /*
        输出
        1 2 3

        解释
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int candidateNum = sc.nextInt();  // 选手数量
        int compNum = sc.nextInt();  // 场次数
        int myNo = sc.nextInt();  // 我的编号

        int[][] input = new int[compNum][2];
        for (int i = 0; i < compNum; i++) {
            input[i][0] = sc.nextInt();
            input[i][1] = sc.nextInt();
        }

        int[] number = getNumber(candidateNum, myNo, input);

        for (int i = 0; i < number.length; i++) {
            if (i==number.length-1){
                System.out.println(number[i]);
            }else{
                System.out.print(number[i]+" ");
            }
        }

    }

    private static int[] getNumber(int candidateNum, int myNo, int[][] input) {

        return null;
    }


}
