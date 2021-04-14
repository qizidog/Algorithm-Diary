package exam;

import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author : qizidog
 * @date : 2021-04-10 19:23
 * @description :
 * 京东笔试，求k交点个数
 *
 * 定义一个点为t直线交点当且仅当该点的   横纵坐标为非负整数   且刚好有t条直线经过该点。
 *
 * 给出平面上n条相互不重合的直线的斜截式y=kx+b,询问对于（2≤t≤n），t直线交点的数量。
 *
 * 蓝线和绿线在 (1,3) 交叉，红线与绿线在 (2,6) 交叉。
 * 由于红线和蓝线的交叉点横坐标不是整数，所以不计入答案。所以两直线交点一个，三直线交点零个。
 *
 **/

public class NumOfKPoints {
    /**
     * 样例输入
     * 3  // 3条直线
     * 2 2  // k, b
     * 3 0
     * 0 3
     *
     * 样例输出
     * 2 0
     * 输出描述
     * 一行输出n-1个数，第一个数表示平面上两直线交点的数量，第二个数表示三直线交点的数量，以此类推。
     */

    // 4 12 6 45 6 23 6 5 6
    private static HashMap<String, Integer> map = new HashMap<>();  // <"x_y", count>
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();

        int[][] arr = new int[num][2];
        for (int i = 0; i < num; i++) {
            arr[i][0] = scanner.nextInt();
            arr[i][1] = scanner.nextInt();
        }


        // 始终不能ac，原因是这里错了，不能这么迭代判断，会多算
        for (int i = 0; i < num; i++) {
            for (int j = i+1; j < num; j++) {
                getCrossPoint(arr[i][0], arr[i][1], arr[j][0], arr[j][1]);
            }
        }

        // <"x_y", count>
        Collection<Integer> values = map.values();
        // Integer max = Collections.max(values);

        int[] result = new int[num];  //3
        for(Integer i : values){
            result[i]++;
        }

        for(int i=1; i<num; i++){
            System.out.print(result[i]+" ");
        }
    }


    public static void getCrossPoint(int k1, int b1, int k2, int b2){
        if(k1==k2) return;
        // 小心double类型
        double y = ((double)(b1*k2-b2*k1)) / ((double)(k2-k1));
        double x = (y-b1) / ((double)(k1));
        if (isMatch(x, y)){
            String key = x+"_"+y;
            map.put(key, map.getOrDefault(key, 0)+1);
        }
    }

    public static boolean isMatch(double x, double y){
        return x>=0 && y>=0 && x==((int)x) && y==((int)y);
    }



}
