package BackTracking;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-03-16 20:32
 * @description :
 * 剑指 Offer 38. 字符串的排列
 * 输入一个字符串，打印出该字符串中字符的所有排列。
 *
 * 你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。
 *
 * 示例 1:
 * 输入：s = "abc"
 * 输出：["abc","acb","bac","bca","cab","cba"]
 *
 * 示例 2：
 * 输入：s = "aab"
 * 输出：["aba","aab","baa"]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zi-fu-chuan-de-pai-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class Permutation {
    private HashSet<String> results = new HashSet<>();

    // 这道题和Permute差不多的，只不过这个没有说输入字符串不会有重复元素，原题是没有给示例2的，很容易忽略这个问题
    public String[] permutation(String s) {
        int len;
        if(s==null || (len=s.length())==0) return new String[0];
        char[] arr = s.toCharArray();
        process(arr, new boolean[len], 0, new StringBuilder());
        return results.toArray(new String[0]);
    }

    // 搞哪个字符串，哪些字符已经搞过了，已经搞了几个了，现在搞成什么个样子
    public void process(char[] arr, boolean[] flags, int count, StringBuilder sb){
        int len;
        if(count == (len=arr.length)){
            results.add(sb.toString());
        }
        HashSet<Character> set = new HashSet<>();  // 剪枝使用，同一位上，使用过的字段不再回溯了
        for(int i=0; i<len; i++){
            if(!flags[i] && !set.contains(arr[i])){
                set.add(arr[i]);  // 剪枝
                flags[i] = true;
                sb.append(arr[i]);
                process(arr, flags, count+1, sb);
                sb.deleteCharAt(count);
                flags[i] = false;
            }
        }
    }



    // 别人的写法，也可以通过swap来实现，省点空间复杂度
    List<String> res = new LinkedList<>();
    char[] c;
    public String[] permutation2(String s) {
        c = s.toCharArray();
        dfs(0);
        return res.toArray(new String[res.size()]);
    }
    void dfs(int x) {
        if(x == c.length - 1) {
            res.add(String.valueOf(c));      // 添加排列方案
            return;
        }
        HashSet<Character> set = new HashSet<>();
        for(int i = x; i < c.length; i++) {
            if(set.contains(c[i])) continue; // 重复，因此剪枝
            set.add(c[i]);
            swap(i, x);                      // 交换，将 c[i] 固定在第 x 位
            dfs(x + 1);                      // 开启固定第 x + 1 位字符
            swap(i, x);                      // 恢复交换
        }
    }
    void swap(int a, int b) {
        char tmp = c[a];
        c[a] = c[b];
        c[b] = tmp;
    }
}
