import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 3/1/16
 * Time: 7:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixDiagonalTraversal {


    public List<Integer> traverseDiagonally(int[][] matrix, boolean top, boolean left)
    {
        List<Integer> traversedList = new ArrayList<Integer>();
        if(matrix == null || matrix[0] == null)
        {
            return traversedList;
        }
        int n = matrix.length;
        int m = matrix[0].length;

        if(top && left)
        {
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, 0, false, true, true, false);
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, 1, true, true, true, false);
        }

        if(top && !left)
        {
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, m-1, false, false, true, true);
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, 1, true, true, true, true);
        }

        if(!top && left)
        {
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, n-1, true, false, true, true);
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, 1, false, true, true, true);
        }

        if(!top && !left)
        {
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, n-1, true, false, true, false);
            traverseDiagonallyOneHalf(matrix, traversedList, n, m, m-2, false, false, true, false);
        }
        return traversedList;
    }

    private void traverseDiagonallyOneHalf(int[][] matrix, List<Integer> traversedList, int n, int m,
                                           int start, boolean rowWise, boolean increaseStart,
                                           boolean increaseRow, boolean increaseColumn) {
        while(start >= 0 && ((rowWise && start < n) || (!rowWise && start < m)))
        {
            int i = rowWise ? start : increaseRow ? 0 : n - 1;
            int j = !rowWise ? start : increaseColumn ? 0 : m - 1;
            while (i >= 0 && i < n && j >= 0 && j < m)
            {
                traversedList.add(matrix[i][j]);
                if(increaseRow)
                {
                    i++;
                }
                else
                {
                    i--;
                }
                if(increaseColumn)
                {
                    j++;
                }
                else
                {
                    j--;
                }
            }
            if(increaseStart)
            {
                start ++;
            }
            else
            {
                start --;
            }
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        printMatrix(matrix);

        System.out.println("Diagonal Traversal Top to Left");
        System.out.println(new MatrixDiagonalTraversal().traverseDiagonally(matrix, true, true));
        System.out.println("Diagonal Traversal Top to right");
        System.out.println(new MatrixDiagonalTraversal().traverseDiagonally(matrix, true, false));
        System.out.println("Diagonal Traversal bottom to Left");
        System.out.println(new MatrixDiagonalTraversal().traverseDiagonally(matrix, false, true));
        System.out.println("Diagonal Traversal bottom to right");
        System.out.println(new MatrixDiagonalTraversal().traverseDiagonally(matrix, false, false));
    }

    public static void printMatrix(int[][] matrix) {
        for(int[] row : matrix)
        {
            System.out.println();
            for(int value : row)
            {
                System.out.print(value + " ");
            }
        }
        System.out.println("");
    }
}
