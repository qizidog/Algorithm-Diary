package StringTheme;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-03-15 20:07
 * @description :
 * 6. Z 字形变换
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 *
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 *
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 *
 * 请你实现这个将字符串进行指定行数变换的函数：
 *
 * string convert(string s, int numRows);
 *
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 *
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/zigzag-conversion
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class ConvertZ {
    public String convert(String s, int numRows) {
        int len;
        // 特殊情况特殊处理
        if(s==null || s.length() ==0){
            return "";
        }
        if(numRows>=(len=s.length()) || numRows==1){
            return s;
        }

        // 找规律
        StringBuilder sb = new StringBuilder();
        // 结果一定是numRows行，一次循环拼一行
        for(int i=0; i<numRows; i++){
            // 如果是第一行或者最后一行，相邻两字符下标差为(numRows-1)*2)
            if(i==0 || i==numRows-1){
                for(int j=i; j<len; j+=((numRows-1)*2)){
                    sb.append(s.charAt(j));
                }
            }else{  // 如果是中间行，一次添加两个字符
                for(int j=i; j<len; j+=((numRows-1)*2)){
                    sb.append(s.charAt(j));
                    int temp;
                    // 其中，第一个字符和第二个字符的下标差为(numRows-i-1)*2
                    if((temp=j+(numRows-i-1)*2)<len){  // 小心下标越界
                        sb.append(s.charAt(temp));
                    }
                }
            }
        }
        return sb.toString();
    }

    /* 这种解法还比较简单，就是空间复杂度有点高
        我们可以使用 min(numRows,len(s)) 个列表来表示 Z 字形图案中的非空行。
        从左到右迭代 ss，将每个字符添加到合适的行。可以使用当前行和当前方向这两个变量对合适的行进行跟踪。
        只有当我们向上移动到最上面的行或向下移动到最下面的行时，当前方向才会发生改变。
    */
    public String convert2(String s, int numRows) {

        if (numRows == 1) return s;

        List<StringBuilder> rows = new ArrayList<>();
        for (int i = 0; i < Math.min(numRows, s.length()); i++)
            rows.add(new StringBuilder());

        int curRow = 0;
        boolean goingDown = false;

        for (char c : s.toCharArray()) {
            rows.get(curRow).append(c);
            if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown;
            curRow += goingDown ? 1 : -1;
        }

        StringBuilder ret = new StringBuilder();
        for (StringBuilder row : rows) ret.append(row);
        return ret.toString();
    }
}
