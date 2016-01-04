import com.sun.javafx.binding.StringFormatter;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 3/1/16
 * Time: 9:51 AM
 * To change this template use File | Settings | File Templates.
 */
public class RectangleSquare {

    public static int getNumOfSquaresToCoverRectangle(int length, int width)
    {
        int count = 0;
        int x = length < width ? width : length;
        int y = length < width ? length : width;
        while(y != 0)
        {
            count += x / y;
            int temp = y;
            y = x % y;
            x = temp;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.printf("The number of squares needed to completely cover rectangle %d X %d is %d", 21, 8, getNumOfSquaresToCoverRectangle(21, 8));
    }

}
