package GraphTheme;

/**
 * @author : qizidog
 * @date : 2021-02-10 15:52
 * @description :
 * 工具类
 * 将给定形式的图结构转化生成一个我们熟悉好用的图结构
 * 给定形式的图结构：[边权重, 尾节点, 头节点]
 **/

public class GraphGenerator {
    // 构造方法私有，不允许外界实例化
    private GraphGenerator(){};

    /**
     * 将给定形式的图结构转化生成一个我们熟悉好用的图结构
     * @param edges 边集
     * @param isDouble 为true表示生成无向图，false表示有向图
     * @return 图（有向图或无向图）
     */
    public static Graph convertGraph(int[][] edges, boolean isDouble){
        Graph graph = new Graph();
        for (int[] e : edges){
            // 如果节点存在，直接获取，否则创建该节点
            GraphNode from = graph.nodes.getOrDefault(e[1], new GraphNode(e[1]));
            GraphNode to = graph.nodes.getOrDefault(e[2], new GraphNode(e[2]));
            graph.nodes.put(e[1], from);
            graph.nodes.put(e[2], to);

            GraphEdge edge1 = new GraphEdge(e[0], from, to);
            graph.edges.add(edge1);

            from.out++;
            from.nexts.add(to);
            from.edges.add(edge1);
            to.in++;

            // 如果为无向图，生成逆向边
            if (isDouble){
                GraphEdge edge2 = new GraphEdge(e[0], to, from);
                graph.edges.add(edge2);

                to.out++;
                to.nexts.add(from);
                to.edges.add(edge2);
                from.in++;
            }
        }
        return graph;
    }

    /**
     * 生成有向图
     * @param edges 边集
     * @return 有向图
     */
    public static Graph convertGraph(int[][] edges){
        return convertGraph(edges, false);
    }

    /**
     * 生成一个样例边集
     * @return 样例边集
     */
    public static int[][] getSampleEdges(){
        return new int[][]{
                {4,0,1}, {8,0,7}, {11,1,7}, {8,1,2}, {7,7,8},
                {1,7,6}, {2,2,8}, {6,8,6}, {7,2,3}, {4,2,5},
                {2,6,5}, {14,3,5}, {9,3,4}, {10,5,4}
        };
    }
}
