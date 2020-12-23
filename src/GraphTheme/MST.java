package GraphTheme;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author : qizidog
 * @date : 2020-12-22 19:22
 * @description :最小生成树 Minimum Spanning Tree
 **/

public class MST {
    static class Node{
        private int id;

        private List<Edge> edges = new ArrayList<>();

        public Node(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public List<Edge> getEdges() {
            return edges;
        }

        public void addEdge(Edge edge) {
            this.edges.add(edge);
        }

        @Override
        public String toString() {
            return "Node{" +
                    "id=" + id +
                    '}';
        }
    }

    static class Edge implements Comparable{
        private Node node1;
        private Node node2;
        private int weight;

        public Edge(Node node1, Node node2, int weight) {
            this.weight = weight;
            this.node1 = node1;
            this.node2 = node2;
            this.node1.addEdge(this);
            this.node2.addEdge(this);
        }

        public int getWeight() {
            return weight;
        }

        public Node getNode1() {
            return node1;
        }

        public Node getNode2() {
            return node2;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "node1=" + node1.id +
                    ", node2=" + node2.id +
                    ", weight=" + weight +
                    "}\n";
        }

        @Override
        public int compareTo(Object o) {
            if (this.weight>=((Edge)o).weight) return 1;
            else return -1;
        }
    }

    static class MyGraph{
        private int nodeNum;
        // 权重为0表示边不存在
        private int[][] matrix;
        private List<Node> nodes = new ArrayList<>();
        private List<Edge> edges = new ArrayList<>();

        public MyGraph(int[][] matrix){
            this.nodeNum = matrix[0].length;
            this.matrix = matrix;
            // 创建nodeNum个节点，并存入集合中
            for (int i=0; i<nodeNum; i++){
                nodes.add(new Node(i));
            }
            // 创建边并存入集合中
            for(int i=0; i<nodeNum; i++){
                for (int j=i+1;j<nodeNum;j++){
                    if(matrix[i][j]>0) {
                        edges.add(new Edge(nodes.get(i), nodes.get(j), matrix[i][j]));
                    }
                }
            }
        }

        /**
         * 获得节点个数
         * @return
         */
        public int getNodeNum(){
            return nodeNum;
        }

        /**
         * 获取指定节点的所有相邻节点
         * @param x
         * @return
         */
        public int[] getNeighbors(int x){
            ArrayList<Integer> neighbors = new ArrayList<>();
            for (int i=0; i<nodeNum; i++){
                if (matrix[x][i]>0) {
                    neighbors.add(matrix[x][i]);
                }
            }
            int size = neighbors.size();
            int[] neighbors_arr = new int[size];
            for(int i=0; i<size; i++){
                neighbors_arr[i] = neighbors.get(i);
            }
            return neighbors_arr;
        }

        /**
         * 获得路径x-y的权重
         * @param x
         * @param y
         * @return
         */
        public int getDistance(int x, int y){
            return matrix[x][y];
        }

        public List<Node> getNodes(){
            return nodes;
        }

        public List<Edge> getEdges(){
            return edges;
        }

        public int[][] getMatrix(){
            return this.matrix;
        }

    }

    public static int[] prim(MyGraph graph){
        int nodeNum = graph.getNodeNum();


        // 标记节点是否已被访问，并记录节点到已标记节点群的最短距离
        // 默认添加第0个节点到已访问集中，用-1标记
        int[] min_dist = graph.getMatrix()[0];
        min_dist[0] = -1;

        // 标记生成树中节点的父节点
        int[] parents = new int[nodeNum];


        for (int i=1; i<nodeNum; i++){

        }

        // 找到min_dist中的最小正数
        int min = 99999; int index=0;
        for (int i=0; i<nodeNum; i++){
            if (min_dist[i]>0 && min_dist[i]<min) {
                min = min_dist[i];
                index = i;  // 最小值的下标
            }
        }
        min_dist[index] = -1;
        parents[index] = index;

        int[] tar = graph.getMatrix()[index];
        for (int j=0; j<nodeNum; j++){
            if (tar[j]>0 && min_dist[j]>-1 && tar[j]<min_dist[j]){
                min_dist[j] = tar[j];
            }
        }


        return parents;
    }

    // 实现kruskal算法
    public static List<Edge> kruskal(MyGraph graph){
        int nodeNum = graph.getNodeNum();
        List<Edge> mst = new ArrayList<>();

        List<Edge> edges = graph.getEdges();
        Collections.sort(edges);

        // count计数搜寻到边的数量；i指示搜索下一条边
        int count=0;int i=0;
        DisjointSet.init(nodeNum);
        while(count<nodeNum-1){
            Edge edge = edges.get(i);
            if(DisjointSet.union(edge.getNode1().getId(), edge.getNode2().getId())>0){
                mst.add(edge);
                count++;
            }
            i++;
        }
        return mst;
    }


    public static void main(String[] args) {
        int[][] matrix = new int[][]{
                { 0, 4, 0, 0, 0, 0, 0, 8, 0},
                { 4, 0, 8, 0, 0, 0, 0,11, 0},
                { 0, 8, 0, 7, 0, 4, 0, 0, 2},
                { 0, 0, 7, 0, 9,14, 0, 0, 0},
                { 0, 0, 0, 9, 0,10, 0, 0, 0},
                { 0, 0, 4,14,10, 0, 2, 0, 0},
                { 0, 0, 0, 0, 0, 2, 0, 1, 6},
                { 8,11, 0, 0, 0, 0, 1, 0, 7},
                { 0, 0, 2, 0, 0, 0, 6, 7, 0}};
        MyGraph myGraph = new MyGraph(matrix);

        System.out.println(myGraph.getNodes());
        System.out.println(myGraph.getEdges());


        List<Edge> mst_edges = kruskal(myGraph);
        System.out.println(mst_edges);

    };
}
