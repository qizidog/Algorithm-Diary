package TreeTheme;

import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-02-11 00:06
 * @description :
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 *
 *  
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 *  
 *
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *  
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class IsSymmetric {
    // 经测试，直接只用中序遍历（左-中-右）和逆中序（右-中-左）遍历比较，是行不通的
    // 下方法为官方提供的递归解法
    public boolean isSymmetric(BiTreeNode root) {
        if (root == null) {
            return true;
        }
        return check(root, root);
    }

    private boolean check(BiTreeNode node1, BiTreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        if (node1 == null || node2 == null) {
            return false;
        }
        return node1.val==node2.val && check(node1.left, node2.right) && check(node1.right, node2.left);
    }

    // 用两个队列实现的非递归求解方案
    public boolean isSymmetric2(BiTreeNode root){
        if (root==null) return true;
        if (root.left==null && root.right==null) return true;
        if (root.left==null || root.right==null) return false;

        LinkedList<BiTreeNode> queue1 = new LinkedList<>();
        LinkedList<BiTreeNode> queue2 = new LinkedList<>();

        queue1.offer(root.left);
        queue2.offer(root.right);

        while ((!queue1.isEmpty()) && (!queue2.isEmpty())){
            BiTreeNode n1 = queue1.poll();
            BiTreeNode n2 = queue2.poll();

            if ((n1==null && n2!=null)
                    || (n1!=null && n2==null)
                    || (n1!=null && n2!=null & n1.val!=n2.val))
                return false;

            if(n1!=null && n2!=null){
                queue1.offer(n1.left);
                queue2.offer(n2.right);

                queue1.offer(n1.right);
                queue2.offer(n2.left);
            }
        }
        return queue1.size()==queue2.size();
    }

    // 用一个队列实现
    public boolean isSymmetric3(BiTreeNode root) {
        if (root==null) return true;

        LinkedList<BiTreeNode> queue = new LinkedList<>();
        queue.offer(root);
        queue.offer(root);
        while (!queue.isEmpty()) {
            BiTreeNode n1 = queue.poll();
            BiTreeNode n2 = queue.poll();

            if (n1==null && n2==null) continue;

            if (n1==null || n2==null || n1.val!=n2.val) return false;

            queue.offer(n1.left);
            queue.offer(n2.right);

            queue.offer(n1.right);
            queue.offer(n2.left);
        }
        return true;
    }
}
