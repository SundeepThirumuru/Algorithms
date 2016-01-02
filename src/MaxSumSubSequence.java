import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;
import sun.plugin.javascript.navig.Array;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 31/12/15
 * Time: 12:10 PM
 * To change this template use File | Settings | File Templates.
 */
public class MaxSumSubSequence {

    public static List<Integer> getMaxSumSubSequence(List<Integer> input)
    {
        if(input.isEmpty())
        {
            return input;
        }
        int maxSum = input.get(0);
        int maxSumStartIndex = 0;
        int maxSumEndIndex = 1;
        int curSum = maxSum;
        int curSumStartIndex = maxSumStartIndex;
        int curSumEndIndex = maxSumEndIndex;

        for(int i=1; i<input.size(); i++)
        {
            if(curSum < 0)
            {
                curSum = input.get(i);
                curSumStartIndex = i;
                curSumEndIndex = curSumStartIndex + 1;
            }
            else
            {
                curSum += input.get(i);
                curSumEndIndex = i+1;
            }
            if(curSum > maxSum)
            {
                maxSum = curSum;
                maxSumStartIndex = curSumStartIndex;
                maxSumEndIndex = curSumEndIndex;
            }
        }
        System.out.println("Input list: " + input);
        List<Integer> maxSumSubSequence = input.subList(maxSumStartIndex, maxSumEndIndex);
        System.out.println("Max sum sub sequence: " + maxSumSubSequence);
        System.out.println("Max Sum: " + getSum(maxSumSubSequence));
        return maxSumSubSequence;
    }

    public static int getSum(List<Integer> list)
    {
        int sum = 0;
        for(Integer value : list)
        {
            sum += value;
        }
        return sum;
    }

    public static void main(String[] args) {
        getMaxSumSubSequence(Arrays.asList(1, 2, -1, 5, -4));
        getMaxSumSubSequence(Arrays.asList(1));
        getMaxSumSubSequence(Arrays.asList(-1));
        getMaxSumSubSequence(Collections.EMPTY_LIST);
        getMaxSumSubSequence(Arrays.asList(-1, -2, -5, -8));
        getMaxSumSubSequence(Arrays.asList(5, 6, 9, -4, 15, -10, 55));
    }
}
