package exam;

import java.util.Scanner;

/**
 * @author : qizidog
 * @date : 2021-04-10 19:23
 * @description :
 * 京东笔试，合成大西瓜
 *
 * 2021年过年，小明在玩合成大西瓜。
 * 他听说你会编代码，他想让你帮他拿最高分。但你显然并不会如此编程，所以你想了个简易版的合成大西瓜来糊弄小明。
 *
 * 规则是这样的，有一串数字序列，当序列到来时，你们可以选择让数字掉落在左边或者右边。
 * 已掉落的只有左右两列。如果掉落到一列的数字和此列原有的顶部数字相同，将会合成一个原来的数字并记1分，
 * 不同的数字则会堆积（顶部数字换为新掉落的数字）并不计分。
 * 例如，左右两边的顶部元素分别是1，2，此时掉落1，如果你让它掉在左边，左边两个元素1合并，并积一分，
 * 如果你让它掉在右边，两边的顶部元素就会变成1，1，并且不计分。
 *
 * 小明能提前得知数字序列，请你帮忙计算在现有规则下的最大得分。
 *
 * 样例输入
 * 6
 * 1 2 3 1 2 2
 * 样例输出
 * 2
 *
 * 提示
 * 将第2，5，6个掉落在右边，剩余的掉落在左边即可得到最大得分，2分。
 **/

public class BigWatermelon {
    /**
     * 输入描述
     * 第一行一个正整数，n表示数字序列的数字数量。
     *
     * 第二行n个正整数ai，表示第i个掉落的数字。
     *
     * 1≤n≤1x105 ,1≤ai≤n
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = scanner.nextInt();
        }

        // int num = 6;
        // int[] arr = new int[]{1,2,3,1,2,2};

        int ret = doProcess(num, arr);
        System.out.println(ret);
    }

     // 6
     // 1 2 3 1 2 2
    private static int doProcess(int num, int[] arr) {
        if (num<2) return 0;

        return process(arr, 0);
    }

    private static int left = -1;
    private static int right = -1;
    private static int process(int[] arr, int idx){
        if(idx==arr.length) return 0;

        if (arr[idx]==left || arr[idx]==right){
            // 相等时，直接+1，看下一个放哪里
            return 1+process(arr, idx + 1);
        }else{
            int tempL = left;  // 放左边，备份
            left = arr[idx];
            int retL = process(arr, idx + 1);
            left = tempL;  // 还原现场

            int tempR = right;
            right = arr[idx];
            int retR = process(arr, idx + 1);
            right = tempR;
            return Math.max(retL, retR);
        }
    }

    // 是我傻了，总想着改dp，但是这个题其实可能并不适合改dp，直接做个傻缓存就好了的
    private static int process(int[] arr, int idx, int left, int right){
        if(idx==arr.length) return 0;

        if (arr[idx]==left || arr[idx]==right){
            // 相等时，直接+1，看下一个放哪里
            return 1+process(arr, idx + 1, left, right);
        }else{
            int retL = process(arr, idx + 1, arr[idx], right);
            int retR = process(arr, idx + 1, left, arr[idx]);

            return Math.max(retL, retR);
        }
    }

}
