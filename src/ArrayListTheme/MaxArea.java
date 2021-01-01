package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2021-01-01 23:01
 * @description :
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。
 * 在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/container-with-most-water
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class MaxArea {
    // 官方双指针法，时间复杂度O(n)，空间复杂度O(1)，我自己只能想到暴力破解法。。。。
    public int maxArea(int[] height) {
        if(height==null) return 0;
        int length = height.length;
        if(length<2) return 0;

        // 双指针
        int i = 0;
        int j = length-1;
        int maxArea = 0;

        while(i<j){
            int h1 = height[i];
            int h2 = height[j];
            int h = h1<=h2?h1:h2;
            int area = h * (j-i);
            maxArea = maxArea>area?maxArea:area;
            if(h1<=h2){
                i++;
            }else{
                j--;
            }
        }

        return maxArea;
    }
}
