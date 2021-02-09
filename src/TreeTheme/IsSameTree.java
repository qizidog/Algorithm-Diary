package TreeTheme;

/**
 * @author : qizidog
 * @date : 2021-02-10 00:41
 * @description :
 * 100. 相同的树
 * 给你两棵二叉树的根节点 p 和 q ，编写一个函数来检验这两棵树是否相同。
 *
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/same-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class IsSameTree {
    // 过于简单，不赘述
    public boolean isSameTree(BiTreeNode p, BiTreeNode q){
        if(p==null && q==null)
            return true;
        if(p==null || q==null || p.val!=q.val)
        return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
