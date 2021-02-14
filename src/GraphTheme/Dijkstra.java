package GraphTheme;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
            // 寻找当前dist中 距离 未被标记点 的最短距离
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

    public static HashMap<GraphNode, Integer> dijkstra(Graph graph, GraphNode head){
        int num = graph.nodes.size();
        // 结果集
        HashMap<GraphNode, Integer> result = new HashMap<>();
        // 已找到最短边的节点
        HashSet<GraphNode> nodeSet = new HashSet<>();

        // 头结点单独处理
        result.put(head, 0);  // 到自己的距离为0
        nodeSet.add(head);
        for (GraphEdge edge : head.edges) {
            GraphNode to = head==edge.from ? edge.to : edge.from;
            result.put(to, edge.weight);
        }

        while (nodeSet.size()<num){
            // 找到未确定的节点中，最近的一个节点
            Integer min = Integer.MAX_VALUE;
            GraphNode minNode = null;
            for (GraphNode key : result.keySet()) {
                if (!nodeSet.contains(key) && result.get(key)<min){
                    minNode = key;
                    min = result.get(key);
                }
            }
            nodeSet.add(minNode);

            // 更新最近节点的邻边信息
            for (GraphEdge edge : minNode.edges) {
                GraphNode to = minNode==edge.from ? edge.to : edge.from;
                if (!result.containsKey(to)){  // 如果没有记录过到这个节点的距离
                    result.put(to, result.get(minNode)+edge.weight);
                }else {  // 如果更新能够使得距离更小，才更新
                    if (result.get(minNode)+edge.weight < result.get(to)) {
                        result.put(to, result.get(minNode)+edge.weight);
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] myGraph = new int[][]{{0,5,3,99,8},{99,0,2,1,99},{99,99,0,99,4},
                                    {99,99,1,0,99},{99,99,99,99,0}};
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.setGraph(myGraph);

        int node = 0;
        int[] dist = dijkstra.dijkstra(node);
        System.out.println("dist:"+Arrays.toString(dist));


        int[][] sample = GraphGenerator.getSampleEdges();
        Graph graph = GraphGenerator.convertGraph(sample, true);
        HashMap<GraphNode, Integer> path = dijkstra(graph, graph.nodes.get(0));
        System.out.println(path);
    }
}
