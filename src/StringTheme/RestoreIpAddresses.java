package StringTheme;


import java.util.ArrayList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-09-21 23:27
 * @description :
 * 93. 复原IP地址
 * 和NQueens问题其实思路差不多，回溯算法
 * 这次完全靠自己在leetcode手写的，没有参照别人，也没有使用IDE，非常满意，就是时间太长了
 **/


/*
    给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。
    有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。
    例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，
    但是 "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效的 IP 地址。
*/
public class RestoreIpAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> addresses = new ArrayList<String>();

        backTracking(addresses, s, 0, new String[4]);
        return addresses;
    }

    public void backTracking(List<String> addresses, String subs, int num, String[] address){
        if(num>=4){
            // 判断是否有剩余子串，有则失败，无则成功
            if(subs.length()==0){
                addresses.add(toMyString(address));
            }
            // 失败拦截，不执行下面了
            return;
        }
        // 最多三位数
        for(int i=0; i<Math.min(3, subs.length()); i++){
            // 如果取i位满足条件的话
            String ss = subs.substring(0, i+1);
            if(isOK(ss)){
                address[num] = ss;
                backTracking(addresses, subs.substring(i+1), num+1, address);
            }
        }
    }

    public boolean isOK(String ss){
        // 前导0开头不行
        if('0'==(ss.charAt(0)) && ss.length()>1){
            return false;
        }
        int num = Integer.valueOf(ss);
        // 大于255不行
        return num <= 255;
    }

    public String toMyString(String[] address){
        StringBuilder sb = new StringBuilder();
        sb.append(address[0]);
        sb.append(".");
        sb.append(address[1]);
        sb.append(".");
        sb.append(address[2]);
        sb.append(".");
        sb.append(address[3]);
        return sb.toString();
    }
}
