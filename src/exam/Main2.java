package exam;

import java.util.Scanner;
import java.util.StringJoiner;

/**
 * @author : qizidog
 * @date : 2021-04-14 19:17
 * @description :
 * 华为笔试，第2题
 * 为了让汽车能够和网络连接，建立了云端的数据库，
 * 汽车将自身的运行信息上报到云端，汽车自身每0.5秒生成一次速度数据。
 * 为了减少和云端之间的数据流量，汽车会在以下几种情况下进行数据上报：
 *
 *  1、周期上报：每 30秒 上报一次，启动后的第一个速度开始计算，第一帧需要上报；
 *
 *  2、AEB(自动紧急制动)上报：当汽车速度比上一次生成的速度  减少了9及以上  时，认为触发了AEB流程，
 *   如果   连续2S   均保持AEB状态，则触发AEB上报，上报的内容包括：
 *       1）本次AEB过程中的所有速度数据，触发AEB  前2S  的数据和AEB结束  后2S  的数据（具体参见样例1）；
 *       2）该范围内的数据中如果包含了已经周期上报的数据，重复上报（具体参见样例3）；
 *       3）如果两次AEB上报的数据有重叠，重叠数据仅上报一次；
 *
 *  3、在满足AEB上报条件时会立刻暂停周期上报，  // 应该需要连续2s保持AEB触发才停止
 *   即此时即使进入周期上报的周期也不再上报了（即第四次AEB计数同时也是周期上报时，本次周期上报取消）。
 *   在AEB上报结束后重新启动周期上报，新的周期从AEB上报的最后一个数据开始计算；
 *
 *    请根据输入的速度信息，输出上报到云端的内容。
 *
 *
 *    第一行输入为N，代表产生了多少次速度数据，1<=N<=10000
 *
 *    后面N行为每0.5秒产生的速度数据，单位为KM/H，范围0~280。
 *
 *    输出最终的上报的速度数据，每个速度间使用,隔开
 *
 *
 *    实例1：
 *
 *    23                       AEB               结束       开始周期计数
 *    0 8 16 24 32 40 48 60 70 60 50 40 30 20 10 0   0 0 0 0   0 5 10
 *                 2s前                                    后
 *    输出
 *    0,40,48,60,70,60,50,40,30,20,10,0,0,0,0,0
 *
 *    说明
 *    第一个速度0符合周期上报的要求，在70->60->50->40->30->20->10->0->0
 *    这一段运行过程中。 当速度为60时检测到进入了AEB流程，速度降到0以后检测到AEB流程结束，
 *    AEB的全流程为60->50->40->30->20->10->0。
 *    因此需要上报的数据是速度60前2S的数据，也就是40,48,60,70，速度0以后的2S数据，也就是 0,0,0,0。
 *    因此综合上报的内容为0,40,48,60,70,60,50,40,30,20,10,0,0,0,0,0
 *
 *
 *    输入
 *    62
 *    66 67 68 69 70 69 68 67 66 67 68 69 70 69 68 67 66 67 68 69 70 69 68 67 66 67 68 69 70 69 68 67 66 67 68 69 70 69 68 67 66 67 68 69 70 69 68 67 66 67 68 69 70 69 68 67 66 67 68 69 70 69
 *    输出
 *    66,70
 **/

public class Main2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        int[] input = new int[num];

        String ret = getData(input);

        System.out.println(ret);
    }

    private static String getData(int[] input) {
        int len;
        if (input==null || (len=input.length)==0) return "";

        StringJoiner sj = new StringJoiner(",");

        int schedule = 0;
        boolean fs = true;

        int AEBCount = 0;
        int start = -1;  // 开始时间
        int end = -1;  // 结束时间

        sj.add(""+input[0]);  // 第一个数直接加进去
        for(int i=1; i<len; i++){

            if(input[i-1]-input[i]>=9){
                AEBCount++;

                if(AEBCount==1){  // 第一次触发
                    start = i;
                }else if(AEBCount==4){  // 满足上报条件
                    fs = false;  // todo 记得改回来
                    for (int i1 = 4; i1 >= 0; i1--) {  // ==0时自己也加进去了
                        if(start-i1>=0) {  //前2s数据
                            sj.add("" + input[start - i1]);
                        }
                    }
                    continue;
                }else if(AEBCount>4){
                    sj.add(""+input[i]);
                    continue;
                }

            }else{
                AEBCount = 0;  // 停止计数
                end = i - 1;  // AEB在前0.5s结束了
                if(!fs){
                    // 如果之前确实发生了上报
                    for (int i1 = 0; i1 < 4; i1++) {
                        if(end+i1<len){  // 后2s数据
                            sj.add(""+input[end+i1]);
                        }
                    }
                    fs = true;
                    schedule = -1;
                    continue;
                }
            }

            // 必须放后面
            if (schedule%60==0  && fs){
                sj.add(""+input[i]);
            }
            schedule++;


        }

        return sj.toString();
    }
}
