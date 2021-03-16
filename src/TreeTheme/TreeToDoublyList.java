package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-03-16 00:25
 * @description :
 * 剑指 Offer 36. 二叉搜索树与双向链表
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 * 我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。
 * 对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。
 *
 * 下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。
 *
 * 特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。
 * 还需要返回链表中的第一个节点的指针。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class TreeToDoublyList {
    // 我的实现（本来想写中序遍历的，写着写着就变成了这样，不过还是不错的）
    public BiTreeNode treeToDoublyList(BiTreeNode root) {
        if(root==null) return null;

        // 得到普通的双链表首位节点
        BiTreeNode[] ret = process(root);
        // 改成循环双链表
        ret[0].left = ret[1];
        ret[1].right = ret[0];

        return ret[0];
    }

    // 返回链表左右端点
    public BiTreeNode[] process(BiTreeNode node){
        // 双链表的头指针和尾指针
        BiTreeNode head, tail;

        // 和左子树生成的尾指针建立连接
        if(node.left != null){
            BiTreeNode[] L = process(node.left);
            L[1].right = node;
            node.left = L[1];
            head = L[0];
        }else{
            head = node;
        }
        // 和右子树生成的头指针建立连接
        if(node.right != null){
            BiTreeNode[] R = process(node.right);
            node.right = R[0];
            R[0].left = node;
            tail = R[1];
        }else{
            tail = node;
        }

        // 组合成新的头尾指针
        return new BiTreeNode[]{head, tail};
    }


    // 基于中序遍历的实现
    private BiTreeNode head;
    private BiTreeNode preNode;
    public BiTreeNode treeToDoublyList2(BiTreeNode root) {
        if (root == null) {
            return null;
        }

        dfs(root);
        // 改循环双链表
        head.left = preNode;
        preNode.right = head;
        return head;
    }

    private void dfs(BiTreeNode curRoot) {
        if (curRoot == null) {
            return;
        }

        dfs(curRoot.left);

        /*
            判断当前是否是第一个节点(最小节点)：
                若是，则让 head 指向 当前节点
                若不是，则让 前驱节点的右子指针 指向 当前节点
        */
        if (preNode == null) {
            head = curRoot;
        } else {
            preNode.right = curRoot;
        }
        curRoot.left = preNode; // 让 当前节点的左子指针 指向 前驱节点

        preNode = curRoot;  // 让 前驱节点 指向 当前节点
        dfs(curRoot.right);
    }
}
