package TreeTheme;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-02-10 20:31
 * @description :
 * 107. 二叉树的层序遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层序遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 *
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其自底向上的层序遍历为：
 *
 * [
 *   [15,7],
 *   [9,20],
 *   [3]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class LevelOrderBottom {
    public List<List<Integer>> levelOrderBottom(BiTreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();

        if(root==null) return result;

        // 层次遍历使用的队列
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> layer = new LinkedList<>();

        BiTreeNode curLast = root;
        BiTreeNode nextLast = null;

        while(!queue.isEmpty()){
            BiTreeNode node = queue.poll();
            layer.add(node.val);

            if(node.left!=null){
                queue.offer(node.left);
                nextLast = node.left;
            }
            if(node.right!=null){
                queue.offer(node.right);
                nextLast = node.right;
            }
            if(node==curLast){
                result.push(layer);
                layer = new LinkedList<>();
                curLast = nextLast;
            }
        }

        return result;
    }
}
