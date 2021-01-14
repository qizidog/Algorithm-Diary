package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-14 13:39
 * @description :
 * 快速排序
 * 平均时间复杂度O(NlogN)，2.0版本最坏时间复杂度(O(N^2))，空间复杂度O(NlogN)，不稳定
 **/

public class QuickSort {
    public static void main(String[] args) {
        int[] arr = new int[]{7,2,1,7,3,4,12,0,5,9,6,8,6};
        sort1(arr);
        // sort2(arr);
        // sort3(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 只使用 小于等于区 和 大于区
    private static int partition(int[] arr, int L, int R){
        int lessEqual = L-1;
        int p = L;

        while (p<R){
            if (arr[p]<arr[R]){
                swap(arr, p, ++lessEqual);
            }
            p++;
        }
        swap(arr, R, ++lessEqual);
        return lessEqual;
    }

    // 使用 小于区 等于区 和 大于区
    private static int[] netherlandsFlag(int[] arr, int L, int R){
        int less = L-1;
        int more = R;
        int p = L;

        while (p<more){
            if (arr[p]<arr[R]){
                swap(arr, p++, ++less);
            }else if (arr[p]>arr[R]){
                swap(arr, p, --more);
            }else{
                p++;
            }
        }
        swap(arr, R, more);
        return new int[]{less+1, more};
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void sort1(int[] arr){
        if (null==arr || arr.length<2) return;
        sort1(arr, 0, arr.length-1);
    }

    // 1.0版本
    private static void sort1(int[] arr, int L, int R) {
        if (L>=R) return;
        int pos = partition(arr, L, R);
        sort1(arr, L, pos-1);
        sort1(arr, pos+1, R);
    }

    public static void sort2(int[] arr){
        if (null==arr || arr.length<2) return;
        sort2(arr, 0, arr.length-1);
    }

    // 2.0版本
    private static void sort2(int[] arr, int L, int R){
        if (L>=R) return;

        // 最坏时间复杂度为O(N^2)
        int[] equals = netherlandsFlag(arr, L, R);

        sort2(arr, L, equals[0]-1);
        sort2(arr, equals[1]+1, R);
    }

    public static void sort3(int[] arr){
        if (null==arr || arr.length<2) return;
        sort3(arr, 0, arr.length-1);
    }

    // 3.0版本
    private static void sort3(int[] arr, int L, int R){
        if (L>=R) return;

        // 随机将一个数组中的元素和第R个元素交换，从概率意义上保证最坏时间复杂度也为O(NlogN)
        swap(arr, R, (int)(L+Math.random()*(R-L+1)));
        int[] equals = netherlandsFlag(arr, L, R);

        sort3(arr, L, equals[0]-1);
        sort3(arr, equals[1]+1, R);
    }
}
