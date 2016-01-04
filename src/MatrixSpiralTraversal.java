import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 3/1/16
 * Time: 8:55 AM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixSpiralTraversal {

    public static List<Integer> spiralTraversal(int[][] matrix) {
        List<Integer> traversedList = new ArrayList<>();
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0) {
            return traversedList;
        }
        spiralTraversal(matrix, traversedList, matrix.length, matrix[0].length);
        return traversedList;
    }

    public static void spiralTraversal(int[][] matrix, List<Integer> traversedList, int n, int m) {
        int startRowIndex = 0;
        int endRowIndex = n;
        int startColumnIndex = 0;
        int endColumnIndex = m;
        while (startRowIndex < endRowIndex &&
               startColumnIndex < endColumnIndex) {
            for (int j = startColumnIndex; j < endColumnIndex; j++) {
                traversedList.add(matrix[startRowIndex][j]);
            }
            for (int i = startRowIndex + 1; i < endRowIndex; i++) {
                traversedList.add(matrix[i][endColumnIndex - 1]);
            }
            for (int j = endColumnIndex - 2; j >= startColumnIndex; j--) {
                traversedList.add(matrix[endRowIndex - 1][j]);
            }
            for (int i = endRowIndex - 2; i >= (startRowIndex + 1); i--) {
                traversedList.add(matrix[i][startColumnIndex]);
            }
            startRowIndex ++;
            endRowIndex --;
            startColumnIndex ++;
            endColumnIndex --;
        }
    }

    public static void main(String[] args) {
        //int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        int[][] matrix = new int[][]{{1, 2, 3, 4, 5}, {6, 7, 8, 9, 10}, {11, 12, 13, 14, 15}, {16, 17, 18, 19, 20}, {21, 22, 23, 24, 25}};
        for(int[] row : matrix)
        {
            System.out.println();
            for(int value : row)
            {
                System.out.print(value + " ");
            }
        }
        System.out.println("");
        System.out.println("Spiral Traversal: ");
        System.out.println(spiralTraversal(matrix));
    }
}
