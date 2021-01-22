package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-05 15:09
 * @description :
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class LongestCommonPrefix {
    // 第一版纵向扫描
    public String longestCommonPrefix(String[] strs) {
        // 字符串数组为空时直接返回空串
        if(strs==null || strs.length==0){
            return "";
        }

        // 将第一个字符串作为基准字符串
        String first = strs[0];

        // 依次取出基准字符串的第i个字符
        for(int i=0; i<first.length(); i++){
            // 与其余字符串的第i个字符比较
            for(int j=1; j<strs.length; j++){
                // 如果发现基准字符串比对比字符串长，返回之前已经比较通过的字符串
                if(i>=strs[j].length()){
                    return strs[j];
                }
                if(first.charAt(i)!=strs[j].charAt(i)){
                    // 如果不相等，则返回之前已经比较通过的字符串
                    return first.substring(0, i);
                }
            }
        }
        // 全部通过，则返回基准字符串
        return first;
    }

    // 第二版纵向扫描
    public String longestCommonPrefix2(String[] strs) {
        // 字符串数组为空时直接返回空串
        if(strs==null || strs.length==0){
            return "";
        }

        // 将第一个字符串作为基准字符串
        String first = strs[0];

        // 依次取出基准字符串的第i个字符
        for(int i=0; i<first.length(); i++){
            // 与其余字符串的第i个字符比较
            for(int j=1; j<strs.length; j++){
                if(i>=strs[j].length() || first.charAt(i)!=strs[j].charAt(i)){
                    // 如果发现基准字符串比对比字符串长，或者不相等，则返回之前已经比较通过的字符串
                    return first.substring(0, i);
                }
            }
        }
        // 全部通过，则返回基准字符串
        return first;
    }

    public static void main(String[] args) {
        String[] strs = new String[]{"flower","flow","flight"};
        LongestCommonPrefix longestCommonPrefix = new LongestCommonPrefix();
        String s = longestCommonPrefix.longestCommonPrefix(strs);
        System.out.println(s);

    }
}
