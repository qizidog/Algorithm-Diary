package GraphTheme;

/**
 * @author : qizidog
 * @date : 2021-02-10 11:40
 * @description : 通用型图——边结构
 */

public class GraphEdge {
	public int weight;  // 边权重
	public GraphNode from;  // 弧尾节点
	public GraphNode to;  // 弧头节点

	public GraphEdge(int weight, GraphNode from, GraphNode to) {
		this.weight = weight;
		this.from = from;
		this.to = to;
	}

	@Override
	public String toString() {
		return 	"{weight=" + weight +
				", from=" + from +
				", to=" + to +
				'}';
	}
}
