package exam;

/**
 * @author : qizidog
 * @date : 2021-04-11 10:53
 * @description :
 * 字节面试，最长回文子串
 **/

public class LongestHuiWenSubstring {


    public static void main(String[] args) {
        // String input = "abcbae";
        String input = "dfabccbae";


        // String ret = getSubString(input, 3, 4);

        String ret = process(input);
        System.out.println(ret);
    }

    private static int len = 0;  // 回文串最大长度
    private static int mid = 0; // 回文串中间下标
    public static String process(String str){
        int size;
        if(str==null || (size=str.length())==0) return "";

        char[] arr = str.toCharArray();

        for(int i=0; i<size; i++){
            int lenOdd = getPString(arr, i, i);  // 奇对称
            int lenEven = getPString(arr, i, i+1);  // 偶对称
            int maxLen = Math.max(lenOdd, lenEven);
            if(len<maxLen){
                len = maxLen;  // 通过len能知道是奇对称还是偶对称
                mid = i;
            }
        }
        return getSubString(str, mid, len);
    }

    /**
     * mid1==mid2  odd; else even
     * return 回文串长度
     */
    public static int getPString(char[] arr, int mid1, int mid2){
        while(mid1>=0 && mid2< arr.length && arr[mid1]==arr[mid2]){
            mid1--;
            mid2++;
        }
        return mid2-mid1-1;
    }

    public static String getSubString(String str, int mid, int len){
        int left = -1;
        int right = -1;

        System.out.println("mid: "+mid);
        System.out.println("len: "+len);

        if(len%2 == 0){  // even
            left = mid - len/2 + 1;
            right = mid + len/2;
        }else{
            left = mid - len/2;
            right = mid + len/2;
        }
        System.out.println("left: "+left);
        System.out.println("right: "+right);

        return str.substring(left, right+1);
    }

}
