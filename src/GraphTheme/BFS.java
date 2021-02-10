package GraphTheme;

import java.util.HashSet;
import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-02-10 15:48
 * @description :
 * 图的广度优先搜索
 **/

public class BFS {

    public static void bfs(GraphNode head){
        LinkedList<GraphNode> queue = new LinkedList<>();
        HashSet<GraphNode> set = new HashSet<>();
        queue.offer(head);
        set.add(head);
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();

            System.out.print(node+", ");

            for (GraphNode next : node.nexts) {
                if (!set.contains(next)){
                    queue.offer(next);
                    set.add(next);
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] edges = GraphGenerator.getSampleEdges();
        Graph graph = GraphGenerator.convertGraph(edges, true);
        bfs(graph.nodes.get(0));
        // 0, 1, 7, 2, 8, 6, 3, 5, 4
    }
}
