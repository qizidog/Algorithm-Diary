package GraphTheme;

import java.util.ArrayList;

/**
 * @author : qizidog
 * @date : 2021-02-10 11:40
 * @description : 通用型图——节点结构
 */

public class GraphNode {
	public int value;  // 节点值/编号
	public int in;  // 节点的入度
	public int out;  // 节点的出度
	public ArrayList<GraphNode> nexts;  // 该节点可以指向的节点
	public ArrayList<GraphEdge> edges;  // 节点的出弧

	public GraphNode(int value) {
		this.value = value;
		in = 0;
		out = 0;
		nexts = new ArrayList<>();
		edges = new ArrayList<>();
	}

	@Override
	public String toString() {
		return ""+value;
	}
}
