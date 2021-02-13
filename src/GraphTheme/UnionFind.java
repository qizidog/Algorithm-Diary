package GraphTheme;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author : qizidog
 * @date : 2021-02-13 15:49
 * @description :
 * 并查集，面向对象实现
 **/

public class UnionFind<E> {
    // <child, parent>
    public HashMap<E, E> map = new HashMap<>();

    public UnionFind(Collection<E> col) {
        // 初始化，每个元素各自为一个集合，其代表为自己
        for (E el : col){
            map.put(el, el);
        }
    }

    public boolean isSameSet(E e1, E e2){
        return findParent(e1).equals(findParent(e2));
    }

    public void union(E e1, E e2){
        E p1 = findParent(e1);
        E parent;
        while (e2!=(parent=map.get(e2))){
            map.put(e2, e1);
            e2 = parent;
        }
        map.put(e2, e1);

    }

    private E findParent(E e){

        E parent;
        while (e!=(parent=map.get(e))){
            e = parent;
        }
        return parent;
    }

    public static void main(String[] args) {
        int[][] sample = GraphGenerator.getSampleEdges();
        Graph graph = GraphGenerator.convertGraph(sample, false);

        LinkedList<GraphNode> nodes = new LinkedList<GraphNode>(graph.nodes.values());
        System.out.println(nodes);

        System.out.println(nodes.get(0));
        System.out.println(nodes.get(1));

        UnionFind<GraphNode> unionFind = new UnionFind<>(nodes);

        System.out.println(unionFind.map);

        System.out.println(unionFind.isSameSet(nodes.get(0), nodes.get(1)));

        unionFind.union(nodes.get(0), nodes.get(1));
        System.out.println(unionFind.map);

    }
}
