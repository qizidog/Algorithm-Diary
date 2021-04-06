package BitTheme;

/**
 * @author : qizidog
 * @date : 2021-04-06 15:45
 * @description :
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *  
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *  
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class ReverseInt {

    public int reverse(int x) {
        int y = 0;
        while(x!=0){
            if(y*10/10!=y) return 0;

            y = y*10 + (x%10);
            x = x/10;
        }
        return y;
    }

    public int reverse2(int x) {
        int y = 0;
        while(x!=0){
            if ((Integer.MAX_VALUE-(x%10))/10<y) return 0;
            y = y*10 + (x%10);
            x = x/10;
        }
        return y;
    }
}
