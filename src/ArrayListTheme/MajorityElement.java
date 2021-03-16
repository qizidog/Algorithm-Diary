package ArrayListTheme;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : qizidog
 * @date : 2021-03-16 22:30
 * @description :
 * 剑指 Offer 39. 数组中出现次数超过一半的数字
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1:
 *
 * 输入: [1, 2, 3, 2, 2, 2, 5, 4, 2]
 * 输出: 2
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/shu-zu-zhong-chu-xian-ci-shu-chao-guo-yi-ban-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class MajorityElement {
    // 解法一  hashmap统计频率，取频率最大的那个
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int num : nums){
            int n = map.getOrDefault(num, 0);
            map.put(num, n+1);
        }

        int max = 0;
        int ret = 0;
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            if(e.getValue()>max){
                max = e.getValue();
                ret = e.getKey();
            }
        }
        return ret;
    }

    // 解法二  直接排完序后取中间那个数
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }

    // 解法三  基于partition实现，时间复杂度O(n)，但是执行超时了，怎么改都不行
    public int majorityElement3(int[] nums) {
        return process(nums, 0, nums.length-1);
    }

    public int process(int[] nums, int s, int e){
        int mid = nums.length/2;
        int pos = partition(nums, s, e);

        while(pos!=mid){
            if(pos<mid){
                s = pos+1;
                pos = partition(nums, s, e);
            }else{
                e = pos-1;
                pos = partition(nums, s, e);
            }
        }

        return nums[mid];

        // 如果做完partition后这个元素被移动到了数组中间的位置
        // if(pos==mid){
        //     return nums[mid];
        // }else if(pos<mid){
        //     return process(nums, pos+1, e);
        // }else{
        //     return process(nums, s, pos-1);
        // }
    }

    public int partition(int[] nums, int s, int e){
        int l = s-1;
        int r = e;
        int i = s;
        while(i<r){
            if(nums[i]<nums[e]){
                swap(nums, i++, ++l);
            }else if(nums[i]>nums[e]){
                swap(nums, i, --r);
            }else{
                i++;
            }
        }
        swap(nums, e, r);
        return r;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 解法四  摩尔投票（和当前结果一致就投支持票，不一样就投反对票，超过一半的人力压群雄）
    public int majorityElement4(int[] nums) {
        int ret = nums[0];
        int count = 0;
        for (int num : nums) {
            if (count==0){
                ret = num;
                count++;
            }else{
                count += num==ret?1:-1;
            }
        }

        return ret;
    }
}
