package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-07 22:18
 * @description :
 * 归并排序（尝试递归和非递归两种方法实现）
 * 时间复杂度O(NlogN)，空间复杂度O(N)，稳定
 **/

public class MergeSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        sort(arr);
        // sort2(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 重载递归实现的归并排序
    public static void sort(int[] arr){
        sort(arr, 0, arr.length-1);
    }

    // 递归实现归并排序
    private static void sort(int[] arr, int L, int R){
        if (null==arr || arr.length<2) return;

        if (L==R) return;  // 递归结束条件

        int mid = L +((R-L)>>1);

        sort(arr, L, mid);
        sort(arr, mid+1, R);

        merge(arr, L, mid, R);
    }

    // 非递归实现归并排序
    public static void sort2(int[] arr){
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;
        while (mergeSize < N) {
            int L = 0;
            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }
            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }

    // 合并两个有序的数组，并使之整体有序
    private static void merge(int[] arr, int start, int mid, int end){
        int[] temp = new int[end-start+1];

        int p1 = start;
        int p2 = mid + 1;
        int k = 0;
        while(p1<=mid && p2<=end){
            temp[k++] = arr[p1]<=arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1<=mid){
            temp[k++] = arr[p1++];
        }
        while(p2<=end){
            temp[k++] = arr[p2++];
        }
        for(int i=0; i<temp.length; i++){
            arr[i+start] = temp[i];
        }
    }

}
