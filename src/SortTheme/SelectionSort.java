package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-07 23:12
 * @description :
 * 选择排序
 * 时间复杂度O(N^2)，空间复杂度O(1)，不稳定
 **/

public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        if (null==arr || arr.length<2) return;

        int length = arr.length;

        for (int i=0; i<length-1; i++){
            int min_idx = i;  // 记录最小值下标
            for (int j=i; j<length; j++){
                if (arr[j]<arr[min_idx]){
                    min_idx = j;
                }
            }
            swap(arr, i, min_idx);
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

}
