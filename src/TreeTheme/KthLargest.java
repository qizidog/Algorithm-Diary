package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-03-16 01:24
 * @description :
 * 剑指 Offer 54. 二叉搜索树的第k大节点
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *  
 *
 * 限制：
 *
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class KthLargest {
    public int kthLargest(BiTreeNode root, int k) {
        if(root==null) return -1;

        dfs(root, k);
        return res;
    }

    private int res = -1;
    private int count = 0;
    private boolean found = false;  // 剪枝，避免找到目标后继续递归

    public void dfs(BiTreeNode root, int k){
        if(root.right!=null){
            dfs(root.right, k);
        }
        if(found) return;
        if(++count == k){
            res = root.val;
            found = true;
            return;
        }
        if(root.left!=null){
            dfs(root.left, k);
        }
    }
}
