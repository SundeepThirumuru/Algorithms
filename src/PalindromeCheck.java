import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 5/1/16
 * Time: 5:27 PM
 * To change this template use File | Settings | File Templates.
 */
public class PalindromeCheck {

    public static boolean isPalindrome(String s)
    {
        if(s == null)
        {
            return false;
        }

        char[] charArray = s.toCharArray();
        for(int i=0; i<(s.length()/2); i++)
        {
            if(charArray[i] != charArray[s.length() - 1 - i])
            {
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindrome(int number)
    {
        if(number < 0)
        {
            return false;
        }
        List<Integer> digits = new LinkedList<>();
        int digit = 0;
        do
        {
            digit = number % 10;
            digits.add(digit);
            number = number / 10;
        }
        while(number != 0);

        for(int i=0; i<digits.size()/2; i++)
        {
            if(digits.get(i) != digits.get(digits.size() - 1 - i))
            {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int number = 14341;
        System.out.printf("The number %d is %s", number, isPalindrome(number) ? "palindrome" : "not palindrome");
        System.out.println();
        String s = "malayalam";
        System.out.printf("The word %s is %s", s, isPalindrome(s) ? "palindrome" : "not palindrome");
        System.out.println();
        number = 143412;
        System.out.printf("The number %d is %s", number, isPalindrome(number) ? "palindrome" : "not palindrome");
        System.out.println();
        s = "pot";
        System.out.printf("The word %s is %s", s, isPalindrome(s) ? "palindrome" : "not palindrome");
    }
}
