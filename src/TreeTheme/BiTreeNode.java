package TreeTheme;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-01-26 16:11
 * @description :
 * 二叉树节点，供本包使用
 **/

public class BiTreeNode {
    int val;
    BiTreeNode left;
    BiTreeNode right;

    public BiTreeNode() {

    }

    public BiTreeNode(int val) {
        this.val = val;
    }

    public BiTreeNode(int val, BiTreeNode left, BiTreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    /**
     * 创建一棵简单的二叉树
     * @return 创建二叉树的头结点
     */
    public static BiTreeNode createBiTree(){
        BiTreeNode head = new BiTreeNode(1);
        BiTreeNode[] biTreeNodes = new BiTreeNode[7];
        biTreeNodes[0] = head;
        for (int i = 1; i < 7; i++) {
            biTreeNodes[i] = new BiTreeNode(i+1);
        }

        head.left = biTreeNodes[1];
        head.right = biTreeNodes[2];

        biTreeNodes[1].left = biTreeNodes[3];
        biTreeNodes[1].right = biTreeNodes[4];
        biTreeNodes[2].left = biTreeNodes[5];
        biTreeNodes[2].right = biTreeNodes[6];

        return head;
    }

    /**
     * 创建一棵简单的二叉树
     * @return 创建二叉树的头结点
     */
    public static BiTreeNode createBiTree2(){
        BiTreeNode head = new BiTreeNode(1);
        BiTreeNode[] biTreeNodes = new BiTreeNode[15];
        biTreeNodes[0] = head;
        for (int i = 1; i < 15; i++) {
            biTreeNodes[i] = new BiTreeNode(i+1);
        }

        head.left = biTreeNodes[1];
        head.right = biTreeNodes[2];

        biTreeNodes[1].left = biTreeNodes[3];
        biTreeNodes[1].right = biTreeNodes[4];
        biTreeNodes[2].left = biTreeNodes[5];
        biTreeNodes[2].right = biTreeNodes[6];

        biTreeNodes[4].left = biTreeNodes[9];
        biTreeNodes[4].right = biTreeNodes[10];
        biTreeNodes[5].left = biTreeNodes[11];
        // biTreeNodes[5].right = biTreeNodes[12];
        // biTreeNodes[6].right = biTreeNodes[14];

        return head;
    }

    /**
     * 随机递归生成一棵二叉树
     * @param maxLevel 生成二叉树的最大高度
     * @param maxVal 生成二叉树中，节点的值最大不超过maxVal
     * @param curLevel 当前节点属于二叉树的第几层
     * @return 二叉树的头节点
     */
    public static BiTreeNode generateRandomTree(int maxLevel, int maxVal, int curLevel){
        if (curLevel>maxLevel || Math.random()<0.3) return null;
        BiTreeNode biTreeNode = new BiTreeNode((int) (Math.random() * maxVal));
        biTreeNode.left = generateRandomTree(maxLevel, maxVal, curLevel+1);
        biTreeNode.right = generateRandomTree(maxLevel, maxVal, curLevel+1);
        return biTreeNode;
    }

    /**
     * 打印一棵二叉树数组
     * @param head 二叉树的头节点
     */
    public static void printTree(BiTreeNode head) {
        LinkedList<BiTreeNode> ret = new LinkedList<>();
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        queue.offer(head);
        ret.offer(head);
        while (!queue.isEmpty()){
            BiTreeNode node = queue.poll();
            if(node.left!=null) {
                queue.offer(node.left);
                ret.offer(node.left);
            }else{
                ret.offer(null);
            }

            if(node.right!=null) {
                queue.offer(node.right);
                ret.offer(node.right);
            }else{
                ret.offer(null);
            }
        }
        System.out.println(ret);
    }

    @Override
    public String toString() {
        return "" + val;
    }
}
