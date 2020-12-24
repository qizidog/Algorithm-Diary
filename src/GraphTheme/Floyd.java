package GraphTheme;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2020-12-23 21:18
 * @description :
 **/

public class Floyd {
    private int[][] graph;
    private int nodeNum;

    public void setGraph(int[][] graph) {
        this.graph = graph;
        this.nodeNum = graph[0].length;
    }

    public int[][] floyd(){
        // initialize
        int[][] dist = new int[nodeNum][nodeNum];
        for (int i=0; i<nodeNum; i++){
            for (int j=0; j<nodeNum; j++){
                dist[i][j] = graph[i][j];
            }
        }

        for (int k=0; k<nodeNum; k++){
            for (int i=0; i<nodeNum; i++){
                for (int j=0; j<nodeNum; j++){
                    if (dist[i][k]+dist[k][j]<dist[i][j])
                    dist[i][j] = dist[i][k]+dist[k][j];
                }
            }
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] graph = new int[][]{{0,1,3,8},{99,0,1,99},{99,99,0,4},{99,99,99,0}};
        Floyd floyd = new Floyd();
        floyd.setGraph(graph);
        int[][] dist = floyd.floyd();
        for (int[] r:dist){
            System.out.println(Arrays.toString(r));
        }
    }
}
