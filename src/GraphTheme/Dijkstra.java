package GraphTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2020-12-23 21:18
 * @description : 最短路算法 dijkstra
 **/

public class Dijkstra {
    // 图的数据结构使用邻接矩阵表示
    private int[][] graph;
    // 图中节点的数量
    private int nodeNum;

    public void setGraph(int[][] graph) {
        this.graph = graph;
        this.nodeNum = graph[0].length;
    }

    /**
     * 使用dijkstra算法求指定节点到其余节点的最短距离
     * @param node 待求节点编号，从0开始
     * @return 距离数组，数组元素下标为节点编号
     */
    public int[] dijkstra(int node){
        // initialize the array to save distance
        int[] dist = new int[nodeNum];
        for (int i=0; i<nodeNum; i++){
            dist[i] = graph[node][i];
        }

        // initialize the array to save node-mark info
        int[] marked = new int[nodeNum];
        marked[node] = 1;

        // 用来存放最短路中上一节点信息的数组
        int[] path = new int[nodeNum];
        path[node] = -1;


        for (int k=1; k<nodeNum; k++){
            // 寻找当前dist中 距离未被标记点 的最短距离
            int min_dist = 99;
            int min_index = 0;
            for(int i=0; i<nodeNum; i++){
                if (marked[i]<1 && dist[i]<min_dist) {
                    min_dist = dist[i];
                    min_index = i;
                }
            }
            marked[min_index] = 1;

            for(int j=0; j<nodeNum; j++){
                // 如果j点尚未被标记，且新增点后，到j点的距离比已知距离短（更新dist[]）
                if(marked[j]<1 && graph[min_index][j] + min_dist<dist[j]){
                    dist[j] = graph[min_index][j] + min_dist;
                    path[j] = min_index;  // 同时也更新其 当前 最短路中的上一节点
                }
            }
        }
        System.out.println("path:"+Arrays.toString(path));
        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0,5,3,99,8},{99,0,2,1,99},{99,99,0,99,4},
                                    {99,99,1,0,99},{99,99,99,99,0}};
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.setGraph(graph);

        int node = 0;
        int[] dist = dijkstra.dijkstra(node);
        System.out.println("dist:"+Arrays.toString(dist));
    }
}
