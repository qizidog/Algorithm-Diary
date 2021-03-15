package DynamicProcessing;

/**
 * @author : qizidog
 * @date : 2021-03-15 19:02
 * @description :
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 *
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/minimum-path-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/

public class MinPathSum {
    public int minPathSum(int[][] grid) {
        if(grid==null || grid.length==0 || grid[0].length==0){
            return -1;
        }
        return process(grid, 0, 0);
    }

    // 当前位于第row行，第col列，返回到达右下角的最短距离
    public int process(int[][] grid, int row, int col){
        int height = grid.length;
        int width = grid[0].length;

        // 如果已经位于右下角了  base case
        if(row==height-1 && col==width-1){
            return grid[row][col];
        }

        // 如果已经位于最后一行了，只能向右走
        if(row==height-1){
            return process(grid, row, col+1) + grid[row][col];
        }
        // 如果已经位于最后一列了，只能往下走
        if(col==width-1){
            return process(grid, row+1, col) + grid[row][col];
        }

        // 否则，递归看哪条路短，走短的那条路
        int goRight = process(grid, row, col+1);
        int goDown = process(grid, row+1, col);

        return Math.min(goRight, goDown)+grid[row][col];
    }

    public int minPathSum2(int[][] grid){
        int height = grid.length;
        int width = grid[0].length;

        int[][] matrix = new int[height][width];

        matrix[height-1][width-1] = grid[height-1][width-1];
        // 填充最后一列
        for(int i=height-2; i>=0; i--){
            matrix[i][width-1] = matrix[i+1][width-1] + grid[i][width-1];
        }
        // 填充最后一行
        for(int i=width-2; i>=0; i--){
            matrix[height-1][i] = matrix[height-1][i+1] + grid[height-1][i];
        }
        // 填充其余中间的格子
        for(int i=height-2; i>=0; i--){
            for(int j=width-2; j>=0; j--){
                matrix[i][j] = grid[i][j] + Math.min(matrix[i][j+1], matrix[i+1][j]);
            }
        }

        return matrix[0][0];
    }


}
