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

// 2021.4.11 复习并查集实现
class UnionSet{
    HashMap<Integer, Integer> child_parent;

    public UnionSet(int[] arr){
        child_parent = new HashMap<>();
        for (int i : arr) {
            child_parent.put(i, i);
        }
    }

    public boolean isSameSet(int a, int b){
        return getParent(a).equals(getParent(b));
    }

    private Integer getParent(int e) {
        while (e != child_parent.get(e)){
            e = child_parent.get(e);
        }
        return e;
    }

    public void union(int a, int b){
        if (isSameSet(a, b)) return;

        Integer aP = getParent(a);
        while(b!=child_parent.get(b)) {
            int temp = child_parent.get(b);
            child_parent.put(b, aP);
            b = temp;
        }
        child_parent.put(b, aP);
    }


    public static void main(String[] args) {
        int[] nodes = new int[]{1,2,3,4,5,6};
        UnionSet unionSet = new UnionSet(nodes);

        System.out.println(unionSet.isSameSet(1, 2));
        unionSet.union(1, 2);
        System.out.println(unionSet.isSameSet(1, 2));
        unionSet.union(3, 2);
        unionSet.union(5, 2);
        unionSet.union(1, 6);

        System.out.println(unionSet.isSameSet(6, 2));
        System.out.println(unionSet.isSameSet(4, 2));

        unionSet.union(4, 6);
        System.out.println(unionSet.isSameSet(6, 2));

    }

}
