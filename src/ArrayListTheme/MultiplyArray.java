package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2020-09-15 22:37
 * @description :
 * JZ51 构建乘积数组
 **/

/*
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。不能使用除法。
 * （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
 */
public class MultiplyArray {
    // 优化的计算方法，时间复杂度O(n)
    public int[] multiply(int[] A) {
        int len = A.length;
        int[] left = new int[len];
        int[] right = new int[len];
        int[] B = new int[len];

        left[0] = 1;
        for(int i=1; i<len; i++){
            left[i] = left[i-1] * A[i-1];
        }
        right[len-1] = 1;
        for(int j=len-2; j>=0; j--){
            right[j] = right[j+1] * A[j+1];
        }
        for(int k=0; k<len; k++){
            B[k] = left[k] * right[k];
        }
        return B;
    }

    // 最暴力的方法，双重循环遍历，时间复杂度O(n^2)
    public int[] multiply2(int[] A) {
        int len = A.length;
        int[] B = new int[len];
        for (int i=0; i<len; i++){
            B[i] = 1;
            for(int j=0; j<len; j++){
                if(i==j){
                    continue;
                }else{
                    B[i]*=A[j];
                }
            }
        }
        return B;
    }
}
