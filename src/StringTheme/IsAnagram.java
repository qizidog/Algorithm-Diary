package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-09 23:06
 * @description :
 * 242. 有效的字母异位词
 **/

/*
    给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
    输入: s = "anagram", t = "nagaram"
    输出: true
    输入: s = "rat", t = "car"
    输出: false
 */
public class IsAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++){
            count[s.charAt(i)-'a']++;
            count[t.charAt(i)-'a']--;
        }
        for(int j:count){
            if(j!=0) return false;
        }
        return true;
    }
}
