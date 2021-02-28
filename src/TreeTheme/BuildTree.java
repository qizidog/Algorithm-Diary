package TreeTheme;

import java.util.HashMap;

/**
 * @author : qizidog
 * @date : 2021-02-28 22:45
 * @description :
 * 105. 从前序与中序遍历序列构造二叉树
 *
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * 例如，给出
 *
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class BuildTree {
    // 空间换时间，用来快速获取中序数组中头结点的位置
    private HashMap<Integer, Integer> map = new HashMap<>();

    public BiTreeNode buildTree(int[] preorder, int[] inorder) {
        if(preorder==null || inorder==null || preorder.length!=inorder.length){
            return null;
        }

        int len;
        for(int i=0; i<(len=inorder.length); i++){
            map.put(inorder[i], i);
        }
        return buildTree(preorder, inorder, 0, len-1, 0, len-1);
    }

    /**
     *
     * @param preorder 原始先序遍历数组
     * @param inorder 原始中序遍历数组
     * @param prel 当前处理的先序数组第一个元素下标
     * @param prer 当前处理的先序数组最后一个元素下标
     * @param inl 当前处理的中序数组第一个元素下标
     * @param inr 当前处理的先序数组最后一个元素下标
     * @return 该子树的头结点
     */
    private BiTreeNode buildTree(int[] preorder, int[] inorder,
                               int prel, int prer, int inl, int inr) {
        if(prel>prer || inl>inr){
            return null;
        }

        BiTreeNode head = new BiTreeNode(preorder[prel]);

        int temp = map.get(preorder[prel]);

        head.left = buildTree(preorder, inorder, prel+1, prel+temp-inl, inl, temp-1);
        head.right = buildTree(preorder, inorder, prel+temp-inl+1, prer, temp+1, inr);

        return head;
    }
}
