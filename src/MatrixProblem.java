import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 3/1/16
 * Time: 10:23 AM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixProblem {

    public static int[] getCountOfRowAndColumnWithAtLeastOneOne(int[][] matrix) {
        int[] count = new int[2];
        int rowCountIndex = 0;
        int columnCountIndex = 1;
        count[rowCountIndex] = 0;
        count[columnCountIndex] = 0;
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return count;
        }

        Set<Integer> rowsHavingOne = new HashSet<>();
        Set<Integer> columnsHavingOne = new HashSet<>();
        int rowSize = matrix.length;
        int columnSize = matrix[0].length;
        boolean done = traverseDiagonallyOneHalf(matrix, rowSize, columnSize, rowsHavingOne, columnsHavingOne, 0, true);


        if (!done) {
            traverseDiagonallyOneHalf(matrix, rowSize, columnSize, rowsHavingOne, columnsHavingOne, 1, false);
        }

        count[rowCountIndex] = rowsHavingOne.size();
        count[columnCountIndex] = columnsHavingOne.size();
        return count;
    }

    private static boolean traverseDiagonallyOneHalf(int[][] matrix, int rowSize, int columnSize,
                                                     Set<Integer> rowsHavingOne, Set<Integer> columnsHavingOne,
                                                     int start, boolean rowWise) {
        while ((rowWise && start < rowSize) || (!rowWise && start < columnSize)) {
            int i = rowWise ? start : 0, j = !rowWise ? start : 0;
            while (i < rowSize && j < columnSize) {
                if (matrix[i][j] == 1) {
                    rowsHavingOne.add(i);
                    columnsHavingOne.add(j);
                }
                i++;
                j++;
            }
            if (rowsHavingOne.size() == rowSize && columnsHavingOne.size() == columnSize) {
                return true;
            }
            start++;
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{0, 1, 0}, {1, 0, 0}, {0, 0, 1}};
        //int[][] matrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        for(int[] row : matrix)
        {
            System.out.println();
            for(int value : row)
            {
                System.out.print(value + " ");
            }
        }
        System.out.println("");

        int[] count = getCountOfRowAndColumnWithAtLeastOneOne(matrix);
        System.out.printf("Num of rows with atleast 1's are %d", count[0]);
        System.out.println("");
        System.out.printf("Num of columns with atleast 1's are %d", count[1]);
    }
}
