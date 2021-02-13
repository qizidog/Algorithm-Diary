package GraphTheme;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-02-13 16:41
 * @description :
 * 图的拓扑排序（必须是无环有向图）
 **/

public class TopologySort {
    public static void topologySort(Collection<GraphNode> nodes){
        // 记录节点及其入度
        HashMap<GraphNode, Integer> counts = new HashMap<>();
        // 存放入度为0的节点
        LinkedList<GraphNode> zeros = new LinkedList<>();
        // 初始化
        for (GraphNode node : nodes) {
            counts.put(node, node.in);
            if (node.in==0){
                zeros.add(node);
            }
        }

        while (zeros.size() != 0) {
            GraphNode zero = zeros.pop();
            System.out.print(zero+", ");
            // 连接的节点，入度-1，如果入度变为0，加入队列
            for (GraphNode next : zero.nexts) {
                Integer in = counts.get(next);
                counts.put(next, in - 1);
                if (in == 1){
                    zeros.add(next);
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] sample = GraphGenerator.getSampleEdges();
        Graph graph = GraphGenerator.convertGraph(sample);
        topologySort(graph.nodes.values());
    }
}
