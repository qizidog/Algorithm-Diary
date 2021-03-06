package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-04 01:21
 * @description :
 * 插入排序
 * 时间复杂度O(N^2)，空间复杂度O(1)，稳定
 **/

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void sort(int[] arr){
        if (null==arr || arr.length<2) return;

        int length = arr.length;
        for (int i=1; i<length; i++){
            int temp=arr[i];
            int j;
            for (j=i; j>0; j--) {
                if (temp < arr[j-1]) {
                    arr[j] = arr[j-1];
                }else{
                    break;
                }
            }
            arr[j] = temp;
        }
    }
}
