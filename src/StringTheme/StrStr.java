package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-08 00:07
 * @description :
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 *
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 *
 * 当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * 示例 1:
 *
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 *
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/implement-strstr
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class StrStr {
    public int strStr(String haystack, String needle) {
        haystack.indexOf(needle);
        int n_len = needle.length();
        int h_len = haystack.length();

        // 考虑几种特殊情况
        if(needle.equals("")) return 0;
        if(h_len<n_len) return -1;
        if(h_len==n_len) return haystack.equals(needle)?0:-1;

        for(int i=0; i<=h_len-n_len; i++){
            if(needle.equals(haystack.substring(i, i+n_len))){
                return i;
            }
        }
        return -1;
    }
}
