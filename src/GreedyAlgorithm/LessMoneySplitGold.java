package GreedyAlgorithm;

import java.util.PriorityQueue;

/**
 * @author : qizidog
 * @date : 2021-02-04 22:31
 * @description :
 * 一块金条切成两半，是需要花费和长度数值一样的铜板的。
 * 比如长度为20的金条，不管怎么切，都要花费20个铜板。 一群人想整分整块金条，怎么分最省铜板?
 *
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为60，金条要分成10，20，30三个部分。
 *
 * 如果先把长度60的金条分成10和50，花费60; 再把长度50的金条分成20和30，花费50;一共花费110铜板。
 * 但如果先把长度60的金条分成30和30，花费60;再把长度30金条分成10和20， 花费30;一共花费90铜板。
 * 输入一个数组，返回分割的最小代价。
 **/

public class LessMoneySplitGold {
    // 哈弗曼树求解
    public static int getMinCost(int[] arr){
        int ret = 0;
        PriorityQueue<Integer> blocks = new PriorityQueue<>();
        for (int i : arr) {
            blocks.add(i);
        }
        while (blocks.size()>1){
            int temp = blocks.poll()+blocks.poll();
            blocks.offer(temp);
            ret += temp;
        }
        return ret;
    }

    public static void main(String[] args) {
        int[] ints = {10, 20, 30, 40};
        System.out.println(getMinCost(ints));
    }
}
