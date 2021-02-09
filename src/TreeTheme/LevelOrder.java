package TreeTheme;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-02-10 01:04
 * @description :
 * 102. 二叉树的层序遍历
 *
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 *
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回其层序遍历结果：
 *
 * [
 *   [3],
 *   [9,20],
 *   [15,7]
 * ]
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-level-order-traversal
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

// 此题解法挺多的
public class LevelOrder {

    // 左神套路解法（不借助HashMap）
    public List<List<Integer>> levelOrder(BiTreeNode root) {
        List<List<Integer>> result = new ArrayList<>();

        if(root==null) return result;

        // 层次遍历使用的队列
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> layer = new ArrayList<>();

        // 记录本层的最后一个节点是谁
        BiTreeNode curLast = root;
        // 记录下层的最后一个节点是谁
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
                result.add(layer);
                layer = new ArrayList<>();
                curLast = nextLast;
            }
        }

        return result;
    }

    // 法二：借助HashMap记录节点位于第几层

    // 法三：一次性添加并处理队列长度（即一行）的元素

    // 法四：深度优先遍历，同一层的加到同一个列表里面
    // https://leetcode-cn.com/problems/binary-tree-level-order-traversal/solution/er-cha-shu-de-ceng-xu-bian-li-by-leetcode-solution/
}
