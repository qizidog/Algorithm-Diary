package TreeTheme;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2021-03-25 12:43
 * @description :
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 *
 * 示例：
 * 输入：3
 * 输出：
 * [
 *   [1,null,3,2],
 *   [3,2,null,1],
 *   [3,1,null,null,2],
 *   [2,1,3],
 *   [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 *
 *    1         3     3      2      1
 *     \       /     /      / \      \
 *      3     2     1      1   3      2
 *     /     /       \                 \
 *    2     1         2                 3
 *
 * 提示：
 *
 * 0 <= n <= 8
 *
 * https://leetcode-cn.com/problems/unique-binary-search-trees-ii/
 * 结合DynamicProcessing.NumTrees来看
 **/

public class GenerateTrees {
    // 基本思路：如果选择i作为根节点，
    // 那么只有小于i的数组成的搜索树才可能是i的左子树，
    // 只有大于i的数组成的搜索树才可能是i的右子树
    public List<BiTreeNode> generateTrees(int n) {
        return process(1, n);
    }

    // 从s到e之间生成一棵二叉搜索树
    public List<BiTreeNode> process(int s, int e){
        List<BiTreeNode> result = new LinkedList<>();
        if(s>e) {
            result.add(null);
            return result;
        }

        for(int i = s; i<=e; i++){
            List<BiTreeNode> leftResult = process(s, i-1);  // 左边的各种可能
            List<BiTreeNode> rightResult = process(i+1, e);  // 右边的各种可能

            for(BiTreeNode l : leftResult){
                for(BiTreeNode r : rightResult){
                    BiTreeNode head = new BiTreeNode(i);
                    head.left = l;
                    head.right = r;
                    result.add(head);
                }
            }
        }
        return result;
    }
}
