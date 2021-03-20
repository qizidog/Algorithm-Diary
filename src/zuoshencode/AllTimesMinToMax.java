package zuoshencode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-03-20 12:47
 * @description :
 *
 * 给定一个只包含正数的数组arr，
 * arr中任何一个子数组sub，一定都可以算出【sub累加和 * sub中的最小值】这样一个值，
 * 那么所有子数组中，这个值最大是多少？
 **/

public class AllTimesMinToMax {
    // 自己实现的code
    public static int allTimesMinToMax(int[] arr){
        int len;
        if (arr==null || (len=arr.length)==0) return 0;

        // 前缀和
        int[] cumSum = new int[len];
        // 第i个数左右两侧第一个比自己小的数的下标
        int[][] minIndex = new int[len][2];

        // 单调栈(找左侧第一个比自己小的数 和 右侧第一个比自己小的数)
        LinkedList<LinkedList<Integer>> stack = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            // 顺便计算前缀和
            if (i == 0) {
                cumSum[i] = arr[i];
            } else {
                cumSum[i] = cumSum[i - 1] + arr[i];
            }

            // 统计左右侧第一个比自己小的数的下标
            while (!stack.isEmpty() && arr[stack.peek().peekLast()] > arr[i]) {
                LinkedList<Integer> top = stack.pop();
                // 记录信息
                for (Integer j : top) {
                    minIndex[j][0] = stack.isEmpty()?-1:stack.peek().peekLast();
                    minIndex[j][1] = i;
                }
            }
            if (stack.isEmpty() || arr[stack.peek().peekLast()] < arr[i]) {
                LinkedList<Integer> list = new LinkedList<>();
                list.addLast(i);
                stack.push(list);
            } else{
                stack.peek().addLast(i);
            }
        }
        // 单调栈里面的剩余值也倒出来统计一下
        while (!stack.isEmpty()) {
            LinkedList<Integer> top = stack.pop();
            // 记录信息
            for (Integer j : top) {
                minIndex[j][0] = stack.isEmpty()?-1:stack.peek().peekLast();
                minIndex[j][1] = -1;
            }
        }

        int maxRet = 0;
        int test = 0;
        for (int i = 0; i < len; i++) {
            // 这两个下标可能等于-1
            int rightIdx = minIndex[i][1];
            int leftIdx = minIndex[i][0];

            maxRet = Math.max(maxRet,
                    arr[i] * (
                            cumSum[rightIdx==-1?(len-1):(rightIdx-1)]
                            - (leftIdx==-1?0:cumSum[leftIdx])
                    )
            );
        }
        return maxRet;
    }

    // 测试
    public static void main(String[] args) {

        // 对数器测试
        for (int i = 0; i < 1000; i++) {
            int[] arr = gerenareRondomArray();
            if (allTimesMinToMax(arr) != max2(arr)) {
            // if (max1(arr) != max2(arr)) {
                System.out.println("FUCK!");
                System.out.println(Arrays.toString(arr));
                break;
            }
        }
    }

    // 左神的code
    public static int max2(int[] arr) {
        int size = arr.length;
        int[] sums = new int[size];
        sums[0] = arr[0];
        for (int i = 1; i < size; i++) {
            sums[i] = sums[i - 1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < size; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int j = stack.pop();
                max = Math.max(max, (stack.isEmpty() ? sums[i - 1] : (sums[i - 1] - sums[stack.peek()])) * arr[j]);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int j = stack.pop();
            max = Math.max(max, (stack.isEmpty() ? sums[size - 1] : (sums[size - 1] - sums[stack.peek()])) * arr[j]);
        }
        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    // 暴力法验证
    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }
}
