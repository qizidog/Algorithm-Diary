package TreeTheme;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-01-26 17:27
 * @description :
 * 求二叉树最宽层的节点数（层次遍历的应用）
 **/

public class LevelTraversalBT {
    public static void main(String[] args) {
        BiTreeNode head = BiTreeNode.createBiTree2();
        calcWidth1(head);
        calcWidth2(head);
    }

    public static void calcWidth1(BiTreeNode head){
        HashMap<BiTreeNode, Integer> map = new HashMap<>();
        LinkedList<BiTreeNode> queue = new LinkedList<>();

        int curLevel = 1;  // 当前是哪一层
        int curNum = 0;  // 当前层的节点数
        int maxLevel = 1;  // 最宽层
        int max = 0;  // 最宽层节点数
        BiTreeNode cur = head;
        queue.offer(cur);
        map.put(cur, curLevel);
        while (!queue.isEmpty()){
            cur = queue.poll();
            int level = map.get(cur);
            if (level==curLevel){  // 如果弹出的是同一层的节点
                curNum++;
            }else{  // 如果不是同一层的节点，结算
                if(curNum>max){
                    max = curNum;
                    maxLevel = curLevel;
                }
                curLevel = level;
                curNum = 1;  // 当前层节点数置1
            }

            if (cur.left!=null){
                queue.offer(cur.left);
                map.put(cur.left, curLevel+1);
            }
            if (cur.right!=null){
                queue.offer(cur.right);
                map.put(cur.right, curLevel+1);
            }
        }
        // 最后一层没有清算，这里单独清算
        if(curNum>max){
            max = curNum;
            maxLevel = curLevel;
        }
        System.out.println("第"+maxLevel+"层拥有最多的节点，节点数量为："+max);
    }

    public static void calcWidth2(BiTreeNode head){
        LinkedList<BiTreeNode> queue = new LinkedList<>();

        int curNum = 0;  // 当前层的节点数
        int max = 0;  // 最宽层节点数
        int curLevel = 1;
        int maxLevel = 1;
        BiTreeNode curEnd = head;  // 本层的最后一个节点
        BiTreeNode nextEnd = null;  // 下一层的最后一个节点
        BiTreeNode cur = head;
        queue.offer(cur);
        while (!queue.isEmpty()){
            cur = queue.poll();
            curNum++;
            if (cur.left!=null) {
                queue.offer(cur.left);
                nextEnd = cur.left;
            }
            if (cur.right!=null) {
                queue.offer(cur.right);
                nextEnd = cur.right;
            }
            if(cur==curEnd){  // 当抵达本层最后一个节点后
                if(curNum>max) {
                    max = curNum;
                    maxLevel = curLevel;
                }
                curLevel++;
                curEnd = nextEnd;
                curNum = 0;
            }
        }
        System.out.println("第"+maxLevel+"层拥有最多的节点，节点数量为："+max);
    }
}
