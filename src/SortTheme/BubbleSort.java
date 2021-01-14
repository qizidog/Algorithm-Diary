package SortTheme;

import java.util.Arrays;

import static SortTheme.MergeSort.sort;

/**
 * @author : qizidog
 * @date : 2021-01-14 13:41
 * @description :
 * 冒泡排序
 * 时间复杂度O(N^2)，空间复杂度O(1)，稳定
 **/

public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        if (null==arr || arr.length<2) return;

        int length = arr.length;
        for (int i=0; i<length-1; i++){
            for (int j=0; j<length-1-i; j++){
                if (arr[j]>arr[j+1])
                    swap(arr, j, j+1);
            }
        }
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
