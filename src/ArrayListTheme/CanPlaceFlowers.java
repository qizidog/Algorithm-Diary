package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-01 22:32
 * @description :
 * 605. 种花问题
 * 假设你有一个很长的花坛，一部分地块种植了花，另一部分却没有。可是，花卉不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 *
 * 给定一个花坛（表示为一个数组包含0和1，其中0表示没种植花，1表示种植了花），和一个数 n 。
 * 能否在不打破种植规则的情况下种入 n 朵花？能则返回True，不能则返回False。
 *
 * 示例 1:
 *
 * 输入: flowerbed = [1,0,0,0,1], n = 1
 * 输出: True
 * 示例 2:
 *
 * 输入: flowerbed = [1,0,0,0,1], n = 2
 * 输出: False
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/can-place-flowers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class CanPlaceFlowers {

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        // 几种特殊情况处理
        if(flowerbed==null) return false;
        int length = flowerbed.length;
        if(length<n) return false;
        if(length==1){return flowerbed[0]==0||n==0;}

        int count = 0;
        // 首尾两个元素单独判断
        if(flowerbed[0]==0 && flowerbed[1]==0) {flowerbed[0]=1;count++;}
        if(flowerbed[length-2]==0 && flowerbed[length-1]==0) {flowerbed[length-1]=1;count++;}

        // 如果length=2，在这里就返回，避免数组下标越界
        if(length==2){return count>=n;}

        for(int i=1; i<length-1; i++){
            // 有人说如果i=1，则i+1一定不等于1，所以可以直接i+=2，也行，不过还要判断，写代码稍微麻烦一点
            if(flowerbed[i]==0 && flowerbed[i-1]==0 && flowerbed[i+1]==0){
                flowerbed[i] = 1;
                count++;
            }
            if(count>=n) break;
        }
        return count>=n;
    }

    // 官方解法，区别主要在于，他计算增加可种植个数时不是一个一个加的，而是利用数学规律，一次加一堆
    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int count = 0;
        int m = flowerbed.length;
        int prev = -1;  // 上一个种花的index，可以反映第0位有没有种花
        for (int i = 0; i < m; i++) {
            if (flowerbed[i] == 1) {
                if (prev < 0) {  // 如果发现index=0处没有种花，则左侧可中i/2个花
                    count += i / 2;
                } else {  // 如果左侧种了花，则可再种(i - prev - 2) / 2个花
                    count += (i - prev - 2) / 2;
                }
                if (count >= n) {
                    return true;
                }
                prev = i;
            }
        }
        // 最后计算右侧可种花的数量
        if (prev < 0) {
            count += (m + 1) / 2;
        } else {
            count += (m - prev - 1) / 2;
        }
        return count >= n;
    }

}
