package GraphTheme;

import java.util.Objects;

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
		return 	"{from=" + from +
				", to=" + to +
				", weight=" + weight +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		GraphEdge graphEdge = (GraphEdge) o;
		return weight == graphEdge.weight &&
				Objects.equals(from, graphEdge.from) &&
				Objects.equals(to, graphEdge.to);
	}

	@Override
	public int hashCode() {
		return Objects.hash(weight, from, to);
	}
}
