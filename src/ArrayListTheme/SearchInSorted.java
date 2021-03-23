package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-23 00:39
 * @description :
 * 剑指 Offer 53 - I. 在排序数组中查找数字 I
 * 统计一个数字在排序数组中出现的次数。
 *
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: 2
 *
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: 0
 **/

public class SearchInSorted {
    // 线性地找两个端点，下标求差
    public int search1(int[] nums, int target) {
        int len;
        if(nums==null || (len=nums.length)==0) return 0;

        int p1 = 0;
        int p2 = len-1;

        while(p1<=p2){
            if(nums[p1]==target && nums[p2]==target) {
                return p2-p1+1;
            }
            if(nums[p1]!=target){
                p1++;
            }
            if(nums[p2]!=target){
                p2--;
            }
        }
        return 0;
    }

    // 完全采用二分的递归来写的，但是这个边界控制也太麻烦的，一不小心就stackoverflow
    public int search(int[] nums, int target) {
        int len;
        if(nums==null || (len=nums.length)==0) return 0;
        return search(nums, target, 0, len-1);
    }

    public int search(int[] nums, int target, int left, int right) {
        int mid = (left+right)>>1;

        if(left==right) return nums[left]==target?1:0;

        if(nums[mid]<target){
            if(mid+1>right) return 0;
            return search(nums, target, mid+1, right);
        }else if(nums[mid]>target){
            if(mid-1<left) return 0;
            return search(nums, target, left, mid-1);
        }else{
            return 1 + (mid==left?0:search(nums, target, left, mid-1))
                    + (mid==right?0:search(nums, target, mid+1, right));
        }
    }

    public static void main(String[] args) {
        SearchInSorted si = new SearchInSorted();
        si.search(new int[]{0,0,1,1,1,2,2,3,3,3,4,4,4,4,5,5,6,6,6,8,10,10}, 4);
    }

    // 稍微简单点的log(n)解法，使用的前提是被查找的nums里面都是整数
    public int search3(int[] nums, int target){
        if(nums==null || nums.length==0) return 0;

        return find(nums, target) - find(nums, target-1);
    }

    // 找到最后一个值为target的下标
    private int find(int[] nums, int target){
        int left = 0;
        int right = nums.length-1;

        // 找到最后一个值为target的下标
        while (left<=right){
            int mid = (left+right)>>1;
            if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] <= target) {
                left = mid + 1;
            }
        }
        return left-1;
    }

}
