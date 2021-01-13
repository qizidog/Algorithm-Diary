package SortTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-01-13 20:55
 * @description :
 * 求小和（一个数组中，在第i个数左边且比他小的数的小和，全部数的小和加起来是这个数组的小和）
 * - 例如[1, 3, 4, 2, 5]的小和为1+1+3+1+1+3+4+2=16
 * 借用归并排序来实现，时间复杂度O(NlogN)
 **/

public class SmallSum {
    public static int smallSum(int[] arr){
        return smallSum(arr, 0, arr.length-1);
    }

    public static int smallSum(int[] arr, int L, int R){
        if(L==R) return 0;

        int mid = L+((R-L)>>1);

        return smallSum(arr, L, mid) +
                smallSum(arr, mid+1, R) +
                merge(arr, L, mid, R);
    }

    public static int merge(int[] arr, int start, int mid, int end){
        int[] temp = new int[end - start + 1];

        int p1 = start;
        int p2 = mid+1;
        int i = 0;
        int ret = 0;

        while(p1<=mid && p2<=end){
            ret += (arr[p1]<arr[p2]?arr[p1]*(end-p2+1):0);
            temp[i++] = arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        while(p1<=mid){
            temp[i++] = arr[p1++];
        }
        while(p2<=end){
            temp[i++] = arr[p2++];
        }

        for(i=0; i<temp.length; i++){
            arr[start+i] = temp[i];
        }

        return ret;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 3, 4, 2, 5};
        int s = smallSum(arr);
        System.out.println(s);
        System.out.println(Arrays.toString(arr));
    }
}
