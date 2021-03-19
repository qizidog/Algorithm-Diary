package zuoshencode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-03-19 14:19
 * @description :
 * 单调栈
 * 求一个数组中，每一个下标对应的：左侧第一个比他小的数的下标，和右侧第一个比他小的数的下标
 **/

public class MonotonousStack {
    public static int[][] getNearLessNoRepeat(int[] arr) {
        int len;
        if (arr==null || (len=arr.length)==0) return null;

        int[][] ret = new int[len][2];

        // 单调栈（里面存下标队列）
        Stack<LinkedList<Integer>> stack = new Stack<>();

        // 先尝试把元素逐个压入栈中
        for (int i=0; i<len; i++) {
            // 如果栈顶元素大于这个元素
            while (!stack.isEmpty() && arr[stack.peek().peekLast()] > arr[i]) {
                LinkedList<Integer> list = stack.pop();
                // 构造结果集
                for (Integer j : list) {
                    ret[j][0] = stack.isEmpty()?-1:stack.peek().peekLast();
                    ret[j][1] = i;
                }
            }

            if (stack.isEmpty() || arr[stack.peek().peekLast()] < arr[i]) {
                LinkedList<Integer> list = new LinkedList<>();
                list.add(i);
                stack.push(list);
            }else if (arr[stack.peek().peekLast()] == arr[i]){  // 如果相等，加入别人的队列
                stack.peek().addLast(i);
            }

        }
        // 栈里面可能还有剩余的元素，要记得弹出
        while (!stack.isEmpty()) {
            LinkedList<Integer> list = stack.pop();
            for (Integer j : list) {
                ret[j][0] = stack.isEmpty()?-1:stack.peek().peekLast();
                ret[j][1] = -1;
            }
        }

        return ret;
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

    public static void main(String[] args) {
        int[] arr = getRandomArray(10);
        System.out.println(Arrays.toString(arr));
        int[][] ret = getNearLessNoRepeat(arr);
        for (int[] ints : ret) {
            System.out.println(Arrays.toString(ints));
        }
    }
}
