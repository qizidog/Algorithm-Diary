package exam;

import java.util.Scanner;

/**
 * @author : qizidog
 * @date : 2021-04-11 19:37
 * @description :
 * 百度笔试，第1题
 *
 * 完全二叉树指定两点之间的最短距离
 *
 * 输入:
 *      3        一共三组数据
 *      1 2      求1号节点和2号节点之间的距离
 *      4 14
 *      8 5
 * 输出:
 *      1
 *      5
 *      3
 **/

public class Code_4_11_1 {

    public static void main(String[] args) {
        // System.out.println(getParent(8, 11));
        // System.out.println(getParent(8, 13));
        // System.out.println(getParent(5, 6));
        // System.out.println(getParent(8, 11));
        // System.out.println(getParent(8, 11));


        // System.out.println(isChild(1, 11));
        // System.out.println(isChild(2, 8));
        // System.out.println(isChild(2, 9));
        // System.out.println(isChild(2, 11));
        // System.out.println(isChild(2, 12));
        // System.out.println(isChild(3, 13));


        // System.out.println(getLevel(1));
        // System.out.println(getLevel(3));
        // System.out.println(getLevel(4));
        // System.out.println(getLevel(14));
        // System.out.println(getLevel(15));

        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        while(num>0){
            System.out.println(getDistance(sc.nextInt(), sc.nextInt()));
            num--;
        }

        // System.out.println(getDistance(8, 5));
    }

    public static int getDistance(int a, int b){
        if (a==b) return 0;

        int temp = a;
        a = Math.min(temp, b);  // 确保a是小于b的那个
        b = Math.max(temp, b);


        int ret = isChild(a, b);
        if(ret < 0) {  // 如果 a 不是 b 的祖先
            int levela = getLevel(a);
            int levelb = getLevel(b);
            int parent = getParent(a, b);
            return levela + levelb - 2*getLevel(parent);
        } else {  // 如果a是b的祖先
            return ret;
        }

    }

    // 计算一个指定的节点位于满二叉树的第几层
    public static int getLevel(int n){
        int s = 1;
        int level = 0;
        while(s <= n){
            s *= 2;
            level++;
        }
        return level;
    }

    // 用于当a不是b的祖先时，求a和b的最近公共祖先
    private static int getParent(int a, int b) {
        // a < b is certain
        while (a!=b) {
            while (b > a) {
                if (b % 2 == 0) {  // left child
                    b /= 2;
                } else {
                    b = (b - 1) / 2;
                }
            }
            if (a==b) break;
            if (a % 2 == 0) {  // left child
                a /= 2;
            } else {
                a = (a - 1) / 2;
            }
        }

        return b;
    }

    // 如果a是b的祖先，返回a和b之间的level差，否则返回-1
    public static int isChild(int a, int b){
        int count = 0;
        // a < b is certain
        while(b > a){
            if (b%2==0){  // left child
                b /= 2;
            } else{
                b = (b-1) / 2;
            }
            count++;
        }

        return b==a ? count : -1;
    }

}
