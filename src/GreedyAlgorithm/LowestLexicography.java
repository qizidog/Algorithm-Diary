package GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-02-04 17:35
 * @description :
 * 给定一个由字符串组成的数组strs（如["ab", "b", "ba"]），
 * 必须把所有的字符串拼接起来，
 * 返回所有可能的拼接结果中，字典序最小的结果（返回"abbab"）
 **/

public class LowestLexicography {
    // 使用贪心策略求解，时间复杂度为排序算法的时间复杂度
    public static String lowestLexicography(String[] strs){
        if (strs==null || strs.length==0) return "";
        Arrays.sort(strs, (a, b)->{return (a+b).compareTo(b+a);});
        StringBuilder ret = new StringBuilder();
        for (String str : strs) {
            ret.append(str);
        }
        return ret.toString();
    }

    // 暴力求解
    public static String lowestLexicography2(String[] strs){
        if (strs==null || strs.length==0) return "";
        ArrayList<String> results = process(strs, new ArrayList<String>(), strs, "");
        String[] strings = new String[results.size()];
        results.toArray(strings);
        Arrays.sort(strings, (a, b)->{return (a+b).compareTo(b+a);});
        // System.out.println(Arrays.toString(strings));
        return strings[0];
    }

    public static ArrayList<String> process(String[] strs, ArrayList<String> results, String[] left, String result){
        if (left.length<=0){
            results.add(result);
            return results;
        }
        for (int i = 0; i < left.length; i++) {
            String[] newLeft = copyWithoutIndex(left, i);
            process(strs, results, newLeft, result+left[i]);
        }
        return results;
    }

    /**
     * 拷贝一份不包含第index个元素的新数组
     * @param strs 原始数组
     * @param index 不需要拷贝的下标
     * @return 新拷贝生成的数组
     */
    public static String[] copyWithoutIndex(String[] strs, int index){
        String[] newLeft = new String[strs.length - 1];
        for (int j = 0; j < newLeft.length; j++) {
            if (j<index) {
                newLeft[j] = strs[j];
            }else{
                newLeft[j] = strs[j+1];
            }
        }
        return newLeft;
    }

    /**
     * 随机生成一个由英文字母组成的字符串数组
     * @param maxNum 数组中字符串的最大个数
     * @param maxLens 数组中字符串的最大长度
     * @return 字符串数组
     */
    public static String[] generateRandomStrings(int maxNum, int maxLens){
        ArrayList<String> strings = new ArrayList<>();
        int num = (int) (Math.random()*maxNum+1);
        for (int i = 0; i < num; i++) {
            int len = (int) (Math.random()*maxLens+1);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                // 随机生成一个英文字符添加到字符串中
                sb.append((char) (Math.random()*26+97));
            }
            strings.add(sb.toString());
        }

        String[] strs = new String[strings.size() - 1];
        strings.toArray(strs);
        return strs;
    }

    // 对数器测试
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            String[] strs = generateRandomStrings(10, 10);
            if (!lowestLexicography(strs).equals(lowestLexicography2(strs))){
                System.out.println("error");
            }
        }
    }
}
