package StringTheme;

import java.util.*;

/**
 * @author : qizidog
 * @date : 2020-09-12 10:02
 * @description :
 * JZ54 字符流中第一个不重复的字符
 **/

/*
    请实现一个函数用来找出字符流中第一个只出现一次的字符。
    例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
    当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。

    这道题出的莫名其妙的，做出来感觉也不对？？？
 */
public class FirstAppearingOnce {
    private static Map<Character, Integer> map = new HashMap<Character, Integer>();
    private static Queue<Character> queue = new LinkedList<Character>();
    //Insert one char from stringstream
    public static void insert(char ch){
        if(map.containsKey(ch)){
            map.put(ch,map.get(ch)+1);
        }else{
            map.put(ch,1);
        }

        queue.offer(ch);
    }

    //return the first appearence once char in current stringstream
    public static char firstAppearingOnce(){
        while(!queue.isEmpty()){
            if(map.get(queue.peek())==1){
                return queue.poll();
            }
            queue.poll();
        }
        return '#';
    }

//    @Override
    public static String toString2() {
        return queue.toString();
    }

    public static void main(String[] args) {
        String str = "google";
        for(char each : str.toCharArray()){
            FirstAppearingOnce.insert(each);
        }

        System.out.println(FirstAppearingOnce.toString2());

        System.out.println(FirstAppearingOnce.firstAppearingOnce());
    }
}
