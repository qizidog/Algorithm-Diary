package GraphTheme;

import java.util.HashMap;
import java.util.HashSet;

/**
 * @author : qizidog
 * @date : 2021-02-10 11:40
 * @description : 通用型图结构
 */

public class Graph {
	public HashMap<Integer, GraphNode> nodes;  // 图的点集（key：节点编号，value：节点实例）
	public HashSet<GraphEdge> edges;  // 图的边集

	public Graph() {
		nodes = new HashMap<>();
		edges = new HashSet<>();
	}
}
