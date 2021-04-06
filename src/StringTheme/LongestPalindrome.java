package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-23 10:55
 * @description :
 * 5. 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class LongestPalindrome {

    // 1.暴力破解法
    public String longestPalindrome(String s) {
        if(s==null){
            return "";
        }
        int len = s.length();
        if(len<1){
            return "";
        }

        int maxLen = 0;
        int fLeft = 0;
        int fRight = 0;

        char[] ss = s.toCharArray();
        for(int left=0; left<len-1; left++){
            for(int right=left+1; right<len; right++){
                if(isPalindrome(ss, left, right)){
                    if(maxLen<(right-left+1)){
                        maxLen = right-left+1;
                        fLeft = left;
                        fRight = right;
                    }
                }
            }
        }

        return s.substring(fLeft, fRight+1);
    }

    public boolean isPalindrome(char[] ss, int left, int right){
        while(left<right){
            if(ss[left]!=ss[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }



    // 2.中心扩散法
    public String longestPalindrome2(String s) {
        if(s==null || s.length()<1){
            return "";
        }
        int len = s.length();
        int maxLen = 0;
        int left=0, right=0;

        char[] ss = s.toCharArray();
        for (int i=0; i<len-1; i++){
            int l1 = isPalindrome2(ss, i, i);
            int l2 = isPalindrome2(ss, i, i+1);
            int ll = Math.max(l1, l2);
            if(ll>maxLen){
                maxLen = ll;
                left = i - (ll-1)/2;
                right = i + ll/2;
            }
        }
        return s.substring(left, right+1);
    }

    public int isPalindrome2(char[] ss, int center1, int center2){
        while (center1>=0 && center2<=ss.length-1){
            if (ss[center1]!=ss[center2]){
                return center2-center1-1;
            }
            center1--;
            center2++;
        }
        return center2-center1-1;
    }


    // 3.动态规划法
    public static String longestPalindrome3(String s) {
        if(s==null || s.length()<1){
            return "";
        }
        int len = s.length();
        int maxLen = 0;
        int left=0, right=0;

        char[] ss = s.toCharArray();
        boolean[][] table = new boolean[len][len];
        for(int i=0; i<len; i++){
            table[i][i] = true;
        }

        for(int j=1; j<len; j++){
            for(int i=0; i<j; i++){
                if(j==i+1){
                    table[i][j] = ss[i]==ss[j];
                }else if(table[i+1][j-1] && ss[i]==ss[j]){
                    table[i][j] = true;
                }else{
                    table[i][j] = false;
                }
                if(table[i][j] && maxLen<j-i+1){
                    maxLen = j-i+1;
                    left = i;
                    right = j;
                }
            }
        }
        return s.substring(left, right+1);
    }

    // 2021.4.6 复习动态规划法
    public static String longestPalindrome4(String s) {
        int size;
        if (s==null || (size=s.length())==0) return "";

        boolean[][] flag = new boolean[size][size];

        for(int i=0; i<size; i++){
            flag[i][i] = true;
        }
        int start = 0;
        int end = 0;
        int max = 1;
        for (int i=size-1; i>=0; i--){
            for (int j=i+1; j<size; j++){
                if (j==i+1){
                    flag[i][j] = s.charAt(i)==s.charAt(j);
                } else{
                    flag[i][j] = flag[i+1][j-1]&&s.charAt(i)==s.charAt(j);
                }
                if (flag[i][j] && (j-i+1)>max){
                    max = j-i+1;
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end+1);
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome4("ababa"));
    }
}
