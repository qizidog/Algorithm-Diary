package zuoshencode;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-03-19 14:20
 * @description :
 * 达标子数组的数量
 * 给定一个整型数组arr，和一个整数num，
 * 某个arr中的子数组sub，如果想达标，
 * 必须满足：sub中最大值 – sub中最小值 <= num，返回arr中达标子数组的数量
 *
 * 暴力破解O(n^3)，单调栈方法O(n)
 *
 * 基于以下单调性
 *
 * - 如果一个范围 [a, b] 达标，那么这个范围内任意子范围一定达标
 * - 如果一个范围 [a, b] 达标，但 [a, b+1] 不达标，那么 [a, b+n] 一定都不达标
 **/

public class AllLessNumSubArray {
    public static int allLessNumSubArray(int[] arr, int num){
        int len;
        if (arr==null || (len=arr.length)==0) return 0;

        LinkedList<Integer> minQueue = new LinkedList<>();
        LinkedList<Integer> maxQueue = new LinkedList<>();

        int L = 0;  // 初始窗口左指针
        int R = 0;  // 初始窗口右指针
        int count = 0;

        while (L<len) {  // 首先固定左指针
            while (R<len) {  // 右指针逐步增加，不回退
                while (!maxQueue.isEmpty() && arr[maxQueue.peekLast()] <= arr[R]) {
                    maxQueue.pollLast();
                }
                maxQueue.offerLast(R);

                while (!minQueue.isEmpty() && arr[minQueue.peekLast()] >= arr[R]) {
                    minQueue.pollLast();
                }
                minQueue.offerLast(R);

                if (arr[maxQueue.peekFirst()] - arr[minQueue.peekFirst()] > num) {
                    // 不能写在这里，因为如果是R到达len后自动结束，这里不会清算
                    // count += (R-L);
                    break;
                }
                R++;
            }
            // R是最后一个达标位置的再下一个，第一个违规的位置
            count += (R-L);
            if (maxQueue.peekFirst() <= L) {
                maxQueue.pollFirst();
            }
            if (minQueue.peekFirst() <= L) {
                minQueue.pollFirst();
            }
            L++;
        }

        return count;
    }

    // for test 随机生成一个指定长度的数字数组
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        printArray(arr);
        int num = 5;
        System.out.println(allLessNumSubArray(arr, num));
    }

}
