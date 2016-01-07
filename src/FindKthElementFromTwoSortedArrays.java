import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 4/1/16
 * Time: 9:34 AM
 * To change this template use File | Settings | File Templates.
 */
public class FindKthElementFromTwoSortedArrays {

    public static int getElement(int[] a, int aStartIndex, int aEndIndex, int[] b, int bStartIndex, int bEndIndex, int k) {

        int m = aEndIndex - aStartIndex;
        int n = bEndIndex - bStartIndex;

        if (a == null || b == null || k < 0 || k > (m + n)) {
            throw new IndexOutOfBoundsException();
        }

        int i = aStartIndex + ((m * k) / (m + n));
        int j = bStartIndex + k - ((m * k) / (m + n));

        int aI = i > (aEndIndex - 1) ? Integer.MAX_VALUE : a[i];
        int aIPrev = (i - 1) < 0 ? Integer.MIN_VALUE : a[i - 1];
        int bJ = j > (bEndIndex - 1) ? Integer.MAX_VALUE : b[j];
        int bJPrev = (j - 1) < 0 ? Integer.MIN_VALUE : b[j - 1];

        if (aI <= bJ) {
            if (aI >= bJPrev) {
                return aI;
            }

            return getElement(a, i + 1, aEndIndex, b, bStartIndex, j + 1, k - (i - aStartIndex) - 1);

        } else {
            if (aIPrev <= bJ) {
                return bJ;
            }

            return getElement(a, aStartIndex, i + 1, b, j + 1, bEndIndex, k - (j - bStartIndex) - 1);
        }

    }

    public static void main(String[] args) {
        int maxNumber = 10;
        int[] a = new int[maxNumber]/*{1, 5, 9, 13} {0, 0, 0, 1, 6, 7, 7, 7, 9, 9}*/;
        int[] b = new int[maxNumber]/*{22, 42, 100, 167}{2, 2, 2, 4, 4, 5, 5, 6, 9, 9}*/;
        List<Integer> mergedList = new ArrayList<>();
        System.out.println("a[]: ");
        for (int i = 0; i < maxNumber; i++) {
            a[i] = (int) (Math.random() * maxNumber);
            mergedList.add(a[i]);
        }
        Arrays.sort(a);
        for (int value : a) {
            System.out.print(value + " ");
        }
        System.out.println();
        System.out.println("b[]: ");
        for (int i = 0; i < maxNumber; i++) {
            b[i] = (int) (Math.random() * maxNumber);
            mergedList.add(b[i]);
        }
        Arrays.sort(b);
        for (int value : b) {
            System.out.print(value + " ");
        }
        System.out.println("");
        Collections.sort(mergedList);
        int k = 4;
        int mismatchCount = 0;
        for (k = 0; k < mergedList.size(); k++) {

            System.out.println();
            int actualValue = getElement(a, 0, a.length, b, 0, b.length, k);
            System.out.printf("%d th element from two sorted arrays not merged: %d", k, actualValue);
            System.out.println("");
            System.out.printf("%d th element from sorted merged list: %d", k, mergedList.get(k));
            if (actualValue != mergedList.get(k)) {
                mismatchCount++;
            }
        }

        System.out.println();
        System.out.println("Mismatch count: " + mismatchCount);
    }
}
