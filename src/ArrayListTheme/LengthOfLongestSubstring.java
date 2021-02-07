package ArrayListTheme;

import GreedyAlgorithm.LowestLexicography;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : qizidog
 * @date : 2021-02-07 12:51
 * @description :
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 示例 4:
 *
 * 输入: s = ""
 * 输出: 0
 *  
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class LengthOfLongestSubstring {
    // 思路为滑动窗口求解
    public static int lengthOfLongestSubstring(String s) {
        if(s==null || s.length()==0) return 0;

        // 存放现有子串中，各字符及最后一次出现的下标
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        int i = 0; int j = 0;
        while(j<s.length()){
            // 如果现有子串中不包含新的这个字符
            if(!map.containsKey(s.charAt(j))){
                map.put(s.charAt(j), j);  // 记录该新字符
                j++;
            }else{
                // 统计字符数
                max = Math.max(max, j-i);
                // 将i指针移动到重复字符的下一个字符位置，并移除中途经过的字符
                while(i<map.get(s.charAt(j))){
                    map.remove(s.charAt(i));
                    i++;
                }
                i = map.get(s.charAt(j))+1;
                // 更新重复字符的位置
                map.put(s.charAt(j), j);
                j++;
            }
        }
        max = Math.max(max, j-i);
        return max;
    }

    // 抄一个大神的
    public static int lengthOfLongestSubstring2(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();

        int res = 0;
        int start = 0; // 窗口开始位置
        for(int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res   = Math.max(res, i - start + 1);
            last[index] = i;
        }

        return res;
    }


    // 改写大神的
    public static int lengthOfLongestSubstring3(String s) {
        if(s==null || s.length()==0) return 0;

        // 存放字符及该字符最后一次出现的下标
        Map<Character, Integer> map = new HashMap<>();

        int max = 0;
        int i = 0; int j = 0;
        while(j<s.length()){
            // 如果字符没有出现过，开始下标不变，否则下标值加1
            i = Math.max(map.getOrDefault(s.charAt(j), -1)+1, i);

            max = Math.max(max, j-i+1);
            map.put(s.charAt(j), j);
            j++;
        }
        return max;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String s = LowestLexicography.generateRandomStrings(1, 30)[0];
            // String s = " ";
            if (lengthOfLongestSubstring2(s)!=lengthOfLongestSubstring3(s)){
                System.out.println("error!");
            }
        }

    }
}
