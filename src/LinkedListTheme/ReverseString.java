package LinkedListTheme;

/**
 * @author : qizidog
 * @date : 2020-09-30 10:07
 * @description :
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 **/

public class ReverseString {
    public void reverseString(char[] s) {
        if(s.length==0) return;
        int left = 0;
        int right = s.length - 1;
        while(left<right){
            char temp = s[left];
            s[left++] = s[right];
            s[right--] = temp;
        }
    }

    public void reverseString2(char[] s) {
        if (s.length==0) return;
        int idx = (s.length-1)/2+1;
        for(int i = 0; i<idx; i++){
            char temp = s[s.length-i-1];
            s[s.length-i-1] = s[i];
            s[i] = temp;
        }
    }
}
