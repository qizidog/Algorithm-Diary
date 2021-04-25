package ArrayListTheme;

/**
 * @author : qizidog
 * @date : 2021-03-23 18:09
 * @description :
 * 剑指 Offer 66. 构建乘积数组
 * 给定一个数组 A[0,1,…,n-1]，请构建一个数组 B[0,1,…,n-1]，
 * 其中 B[i] 的值是数组 A 中除了下标 i 以外的元素的积,
 * 即 B[i]=A[0]×A[1]×…×A[i-1]×A[i+1]×…×A[n-1]。不能使用除法！
 *
 * 示例:
 * 输入: [1,2,3,4,5]
 * 输出: [120,60,40,30,24]
 *
 * 提示：
 * 所有元素乘积之和不会溢出 32 位整数
 * a.length <= 100000
 *
 * https://leetcode-cn.com/problems/gou-jian-cheng-ji-shu-zu-lcof/
 **/

public class ConstructArr {
    // 两个数组分别存左右累乘结果
    public int[] constructArr(int[] a) {
        int len;
        if(a==null) return null;
        if((len=a.length)==0) return new int[0];

        int[] left = new int[len];  // 从左边一直累乘到这里是多少
        int[] right = new int[len];  // 从右边一直累乘到这里是多少

        for(int i=0; i<len; i++){
            if(i==0){
                left[i] = a[i];
                right[len-1-i] = a[len-1-i];
            }else{
                left[i] = left[i-1]*a[i];
                right[len-1-i] = right[len-i]*a[len-1-i];
            }
        }
        int[] ret = new int[len];
        for(int i=0; i<len; i++){
            int l = i-1>=0?left[i-1]:1;
            int r = i+1<len?right[i+1]:1;
            ret[i] = l*r;
        }
        return ret;

    }

    // 减少了额外空间占用
    public int[] constructArr2(int[] a) {
        int len;
        if(a==null) return null;
        if((len=a.length)==0) return new int[0];

        int[] ret = new int[len];

        for(int i=0; i<len; i++){
            if(i==0){
                ret[i] = 1;
            } else{
                ret[i] = ret[i-1] * a[i-1];
            }
        }
        for(int j=len-1, temp=1; j>=0; j--){
            if(j==len-1){
                temp = a[j];
            }else{
                ret[j] = ret[j] * temp;
                temp *= a[j];
            }
        }
        return ret;
    }
}
