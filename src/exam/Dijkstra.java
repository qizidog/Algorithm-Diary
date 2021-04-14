package exam;

import java.util.Arrays;

/**
 * @author : qizidog
 * @date : 2021-04-08 20:20
 * @description :
 * 完美世界笔试，dijkstra
 **/

public class Dijkstra {
    public static void main(String[] args) {
        int n = 6;
        // int[][] matrix = new int[n][n];
        //
        // Scanner sc = new Scanner(System.in);
        // for(int i = 0; i < n; i++){
        //     for(int j = 0; j < n; j++){
        //         matrix[i][j] = sc.nextInt();
        //     }
        // }

        int[][] matrix = new int[][]{
                {0, 1, 12, -1, -1, -1},
                {-1, 0, 9, 3, -1, -1},
                {-1, -1, 0, -1, 5, -1},
                {-1, -1, 4, 0, 13, 15},
                {-1, -1, -1, -1, 0, 4},
                {-1, -1, -1, -1, -1, 0}
        };
        System.out.println(Arrays.toString(dijkstra(matrix)));
        // int[] ret = dijkstra(matrix);
        // for(int i=1; i<n; i++){
        //     System.out.println(ret[i]);
        // }
    }


    /*
        0 1 12 -1 -1 -1
        -1 0 9 3 -1 -1
        -1 -1 0 -1 5 -1
        -1 -1 4 0 13 15
        -1 -1 -1 -1 0 4
        -1 -1 -1 -1 -1 0
     */
    private static int[] dijkstra(int[][] matrix) {
        int size = matrix.length;
        boolean[] visited = new boolean[size];

        // 初始化
        int[] path = new int[size];
        for (int i = 0; i < size; i++) {
            path[i] = matrix[0][i];
        }


        for(int k=0; k<size; k++) {
            // 找到一轮中最近的那个
            int min = Integer.MAX_VALUE;
            int minIdx = 0;
            for (int i = 1; i < size; i++) {
                if (path[i] >= 0 && path[i] < min && !visited[i]) {
                    min = path[i];
                    minIdx = i;
                }
            }
            if (min!=Integer.MAX_VALUE){
                visited[minIdx] = true;
            }

            // 更新matrix
            for (int i = 0; i < size; i++) {
                if (matrix[minIdx][i] > 0 &&
                        (path[minIdx] + matrix[minIdx][i] < path[i] || path[i] == -1)) {
                    path[i] = path[minIdx] + matrix[minIdx][i];
                }
            }
        }


        return path;
    }
}
