/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 5/1/16
 * Time: 3:34 PM
 * To change this template use File | Settings | File Templates.
 */
public class FibonacciNumbers {

    public static int[] getFibonacciNumbersDP(int n)
    {
        int[] fibonacciNumbers = new int[n + 1];
        for(int i = 0; i<= n; i++)
        {
            fibonacciNumbers[i] = getFibonacciNumberDP(i, fibonacciNumbers);
        }
        return fibonacciNumbers;
    }

    public static int getFibonacciNumberDP(int n, int[] fibonacciNumbers)
    {
        if(n == 0 || n == 1)
        {
            return 1;
        }

        int fibNum = fibonacciNumbers[n - 2] + fibonacciNumbers[n - 1];
        return fibNum;
    }

    public static int[] getFibonacciNumbers(int n)
    {
        int[] fibonacciNumbers = new int[n + 1];
        for(int i=0; i<= n; i++)
        {
            fibonacciNumbers[i] = getFibonacciNumber(i);
        }
        return fibonacciNumbers;
    }

    public static int getFibonacciNumber(int n)
    {
        if(n == 0 || n == 1)
        {
            return 1;
        }

        return getFibonacciNumber(n-1) + getFibonacciNumber(n-2);
    }

    public static void main(String[] args) {

        int n = 40;
        System.out.printf("%d Fibonacci Numbers ", n);
        System.out.println();
        long startTime = System.currentTimeMillis();
        int[] fibNumbers = getFibonacciNumbers(n);
        long endTime = System.currentTimeMillis();
        System.out.printf("Time taken: %d ms", (endTime - startTime));
        RotatedSortedArray.printArray(fibNumbers);
        startTime = System.currentTimeMillis();
        fibNumbers = getFibonacciNumbersDP(n);
        endTime = System.currentTimeMillis();
        System.out.printf("Time taken DP: %d ms", (endTime - startTime));
        RotatedSortedArray.printArray(fibNumbers);
    }
}
