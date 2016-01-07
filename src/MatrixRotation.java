/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 4/1/16
 * Time: 5:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class MatrixRotation {

    public static int[][] rotateMatrix(int[][] a) throws Exception
    {
        if(a == null || a.length == 0 || a[0] == null || a[0].length == 0)
        {
            throw new Exception("Invalid input");
        }

        int rowSize = a.length;
        int colSize = a[0].length;
        int[][] retValue = new int[colSize][rowSize];

        for(int i=0; i<rowSize; i++)
        {
            for(int j=0; j<colSize; j++)
            {
                retValue[j][rowSize - i- 1] = a[i][j];
            }
        }
        return retValue;
    }

    public static void main(String[] args) {
        int[][] a = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}, {10, 11, 12}};
        System.out.println("Input matrix");
        MatrixDiagonalTraversal.printMatrix(a);
        System.out.println("Matrix rotated by 90 deg");
        try
        {
            MatrixDiagonalTraversal.printMatrix(rotateMatrix(a));
        }
        catch (Exception ignore)
        {

        }
    }
}
