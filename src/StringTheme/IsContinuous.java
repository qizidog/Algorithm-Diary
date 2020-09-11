package StringTheme;

/**
 * @author : qizidog
 * @date : 2020-09-11 16:38
 * @description :
 * JZ45 扑克牌顺子
 **/

public class IsContinuous {
    public static boolean isContinuous(int [] numbers){
        if(numbers.length==0) return false;
        int min_ = 13;
        int max_ = 1;
        int[] count = new int[14];
        for(int each:numbers){
            if(each>0){
                if(++count[each]>1) return false;
                min_ = min_>each?each:min_;
                max_ = max_>each?max_:each;
            }
        }
        return max_ - min_ < 5;
    }

    public static void main(String[] args) {
        System.out.println(IsContinuous.isContinuous(new int[]{1,2,0,4,5}));
    }
}
