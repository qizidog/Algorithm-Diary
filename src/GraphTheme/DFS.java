package GraphTheme;

import java.util.HashSet;
import java.util.Stack;

/**
 * @author : qizidog
 * @date : 2021-02-10 15:50
 * @description :
 * 图的深度优先搜索
 **/

public class DFS {
    private static HashSet<GraphNode> set1 = new HashSet<>();  // 方法一使用
    private static HashSet<GraphNode> set2 = new HashSet<>();  // 方法二使用

    // 基于递归实现的深度优先
    public static void dfs(GraphNode node){
        set1.add(node);
        System.out.print(node+", ");
        for (GraphNode next : node.nexts) {
            if (!set1.contains(next)) {
                dfs(next);
            }
        }

    }

    // 基于栈实现的深度优先
    public static void dfsByStack(GraphNode node){
        Stack<GraphNode> stack = new Stack<>();
        stack.push(node);
        set2.add(node);
        System.out.print(node+", ");
        while (!stack.isEmpty()) {
            GraphNode cur = stack.pop();
            for (GraphNode next : cur.nexts) {
                if (!set2.contains(next)){
                    stack.push(cur);
                    stack.push(next);
                    set2.add(next);
                    System.out.print(next+", ");
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        int[][] edges = GraphGenerator.getSampleEdges();
        Graph graph = GraphGenerator.convertGraph(edges, true);
        // dfs(graph.nodes.get(0));
        dfsByStack(graph.nodes.get(0));
    }
}
