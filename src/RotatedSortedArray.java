import com.sun.org.apache.xerces.internal.dom.PSVIAttrNSImpl;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 5/1/16
 * Time: 1:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class RotatedSortedArray {

    public static int[] rotateArray(int[] a, int numOfShifts, boolean clockWise)
    {
        numOfShifts = numOfShifts % a.length;
        if(a == null || a.length < 2 || numOfShifts == 0)
        {
            return a;
        }

        int[] rotatedArray = new int[a.length];
        int j = numOfShifts;
        if(!clockWise)
        {
            j = -numOfShifts;
        }
        for(int i=0; i<a.length; i++)
        {
            if(j < 0)
            {
                j = a.length + j;
            }
            if(j > a.length - 1)
            {
                j = j - a.length;
            }
            rotatedArray[j] = a[i];
            j++;
        }
        return rotatedArray;
    }

    public static int[] rotateArrayInPlace(int[] a, int numOfShifts, boolean left)
    {
        if(left)
        {
            //a = {1, 2, 3, 4, 5}; numOfShifts = 2;
            reverse(a, 0, numOfShifts); //a= {2, 1, 3, 4, 5}
            reverse(a, numOfShifts, a.length); //a={2, 1, 5, 4, 3}
            reverse(a, 0, a.length);//a={3, 4, 5, 1, 2}
        }
        else
        {
            reverse(a, 0, a.length);
            reverse(a, 0, numOfShifts);
            reverse(a, numOfShifts, a.length);
        }
        return a;
    }

    public static void reverse(int[] a, int startIndex, int endIndex)
    {
        int temp = 0;
        int size = endIndex - startIndex;
        for(int i=0; i < (size / 2); i++)
        {
            temp = a[startIndex + i];
            a[startIndex + i] = a[endIndex - 1 - i];
            a[endIndex - 1 - i] = temp;
        }
    }


    public static int findElement(int[] rotatedSortedArray, int startIndex, int endIndex, int element)
    {
        if(startIndex < endIndex)
        {
            int middleIndex = (startIndex + endIndex)/2;
            if(element == rotatedSortedArray[middleIndex])
            {
                return middleIndex;
            }
            if(rotatedSortedArray[startIndex] <= rotatedSortedArray[middleIndex])
            {
                if(element < rotatedSortedArray[middleIndex])
                {
                    return findElement(rotatedSortedArray, startIndex, middleIndex, element);
                }
                else
                {
                    return findElement(rotatedSortedArray, middleIndex + 1, endIndex, element);
                }
            }
            else
            {
                int index = findElement(rotatedSortedArray, startIndex, middleIndex + 1, element);
                if(index == -1)
                {
                    return findElement(rotatedSortedArray, middleIndex + 1, endIndex, element);
                }
            }
        }
        return -1;
    }

    public static void printArray(int[] a)
    {
        System.out.println();
        for(int value : a)
        {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        int element = 3;
        System.out.print("Input Array: ");
        printArray(a);
        System.out.println("Index of Element " + element + " searched: " + findElement(a, 0, a.length, element));
        System.out.print("Rotated Array ClockWise 3: ");
        int[] rotatedArray = rotateArray(a, 3, true);
        printArray(rotatedArray);
        System.out.println("Index of Element " + element + " searched: " + findElement(rotatedArray, 0, rotatedArray.length, element));
        System.out.println();
        System.out.print("Rotated Array AntiClockWise 2: ");
        rotatedArray = rotateArray(a, 2, false);
        printArray(rotatedArray);
        System.out.println("Index of Element " + element + " searched: " + findElement(rotatedArray, 0, rotatedArray.length, element));
        System.out.print("Rotated Array ClockWise 10: ");
        rotatedArray = rotateArray(a, 10, true);
        printArray(rotatedArray);
        System.out.println("Index of Element " + element + " searched: " + findElement(rotatedArray, 0, rotatedArray.length, element));
        System.out.print("Rotated Array AntiClockWise 12: ");
        rotatedArray = rotateArray(a, 12, false);
        printArray(rotatedArray);
        System.out.println("Index of Element" + element + " searched: " + findElement(rotatedArray, 0, rotatedArray.length, element));


//        System.out.println("In place rotate array 2 left: ");
//        printArray(rotateArrayInPlace(a, 2, true));

        System.out.println("In place rotate array 2 right: ");
        printArray(rotateArrayInPlace(a, 2, false));


    }
}
