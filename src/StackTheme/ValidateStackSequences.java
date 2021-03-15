package StackTheme;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-03-15 20:21
 * @description :
 * 剑指 Offer 31. 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。
 * 假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，
 * 序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class ValidateStackSequences {
    // 我写的
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null && popped == null) return true;
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        if (pushed.length == 0 && popped.length == 0) return true;

        LinkedList<Integer> stack = new LinkedList<>();

        int i = 0;  // 当前考虑pushed数组的第i个元素
        int j = 0;  // 当前考虑popped数组的第j个元素
        for (; ; ) {
            if (stack.isEmpty() || stack.peek() != popped[j]) {
                stack.push(pushed[i++]);
            } else {
                stack.pop();
                j++;
            }
            // 如果已经全部都push进去了
            if (i == pushed.length) {
                break;
            }
        }
        while (!stack.isEmpty()) {
            if (stack.pop() != popped[j++]) {
                return false;
            }
        }
        return true;
    }

    // 大佬的写法
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        // 特殊值处理
        if (pushed == null && popped == null) return true;
        if (pushed == null || popped == null || pushed.length != popped.length) return false;
        if (pushed.length == 0 && popped.length == 0) return true;

        LinkedList<Integer> stack = new LinkedList<>();
        int j = 0;
        for (int e : pushed) {
            stack.push(e);  // 压一个入栈
            while (!stack.isEmpty() && stack.peek() == popped[j]) {  // 能弹多少弹多少
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
