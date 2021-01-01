package BackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-09-24 10:52
 * @description :
 * 17. 电话号码的字母组合
 * 穷举类的题目，可以考虑使用回溯算法
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 示例:
 *
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 **/

public class LetterCombinations {
    public List<String> letterCombinations(String digits) {
        List<String> ret = new ArrayList<String>();
        if(digits==null || digits.equals("")) return ret;
        HashMap<Character, char[]> map = new HashMap<Character, char[]>(){{
            put('2', new char[]{'a', 'b', 'c'});
            put('3', new char[]{'d', 'e', 'f'});
            put('4', new char[]{'g', 'h', 'i'});
            put('5', new char[]{'j', 'k', 'l'});
            put('6', new char[]{'m', 'n', 'o'});
            put('7', new char[]{'p', 'q', 'r', 's'});
            put('8', new char[]{'t', 'u', 'v'});
            put('9', new char[]{'w', 'x', 'y', 'z'});
        }};

        // char[] dig = digits.toCharArray();
        backtracking(digits, 0, map, "", ret);
        return ret;
    }

    public void backtracking(String digits, int id, HashMap<Character,
            char[]> map, String sb, List<String> ret){
        if(id==digits.length()){
            // 添加到结果集
            ret.add(sb.toString());
            return;
        }
        char[] arr = map.get(digits.charAt(id));
        for(char c:arr){
            backtracking(digits, id+1, map, sb+c, ret);
        }
    }


}
