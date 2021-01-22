package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-07 23:18
 * @description :
 * 计数排序
 * 时间复杂度O(n+k)，空间复杂度O(n+k)，稳定
 **/

public class CountSort {

    public static void main(String[] args) {
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        // 找到最大的那个数（默认都是非负整数），创建一批桶
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i]>max) max=arr[i];
        }
        int[] buckets = new int[max+1];

        for (int e : arr) {
            buckets[e]++;
        }

        int index = 0;
        for (int i = 0; i < max+1; i++) {
            for (int j=0; j<buckets[i]; j++){
                arr[index++] = i;
            }
        }
    }
}
