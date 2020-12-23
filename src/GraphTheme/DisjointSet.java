package GraphTheme;

/**
 * @author : qizidog
 * @date : 2020-12-22 17:27
 * @description :手动实现并查集
 **/
public class DisjointSet {
    private static int[] parents;  // 记录节点的双亲节点
    private static int[] ranks;  // 记录节点的深度

    public static void init(int num){
        parents = new int[num];
        ranks = new int[num];
        for (int i=0;i<parents.length;i++) {
            parents[i] = -1;
            ranks[i] = 0;
        }
    }

    public static int search_root(int node){
        int root = node;
        while (parents[root]!=-1){
            root = parents[root];
        }
        return root;
    }


    public static int union(int x, int y){
        int root_x = search_root(x);
        int root_y = search_root(y);
        if (root_x==root_y){
            System.out.println("存在循环路径，合并失败");
            // System.exit(0);
            return 0;  // 合并失败
        }else{  // 把深度小的合并到深度大的上，总的深度不会加大
            if (ranks[root_x]>ranks[root_y]){
                parents[root_y] = root_x;
            }else if (ranks[root_x]<ranks[root_y]){
                parents[root_x] = root_y;
            }else{
                parents[root_y] = root_x;
                ranks[root_x]++;
            }
            return 1;  // 合并成功
        }
    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0,1}, {1,2}, {1,3}, {2,5}, {3,4}, /*{2,4}*/};
        init(6);
        for (int[] edge:edges) {
            union(edge[0], edge[1]);
        }
        System.out.println("无循环路径，合并成功");
    }

}