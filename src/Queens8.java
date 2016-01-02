import java.lang.Integer;import java.lang.Math;import java.lang.String;import java.lang.System;import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: SThirumuru
 * Date: 31/12/15
 * Time: 9:10 AM
 * To change this template use File | Settings | File Templates.
 */
public class Queens8 {

    public List<Integer> get8Queens(int startPosition)
    {
        List<Integer> queenPositions = new ArrayList<Integer>(8);
        for(int i=0; i < 8; i++)
        {
            queenPositions.add(-1);
        }

        queenPositions.set(0, startPosition - 1);
        int i=0, j=0;
        while(i < 8)
        {
            j = queenPositions.get(i);
            j = j + 1;
            queenPositions.set(i, -1);
            boolean foundPosition = false;
            while(j < 8)
            {
                if(checkNoConflict(i, j, queenPositions))
                {
                    queenPositions.set(i, j);
                    foundPosition = true;
                    break;
                }
                j ++;
            }
            if(!foundPosition)
            {
                queenPositions.set(i, -1);
                i --;
            }
            else
            {
                i ++;
            }
        }
        return queenPositions;
    }

    public void printQueenPositions(List<Integer> queenPositions)
    {
        for(int i=0; i<queenPositions.size(); i++)
        {
            for (int j=0; j<8; j++)
            {
                if(j != queenPositions.get(i))
                {
                    System.out.print("0 ");
                }
                else
                {
                    System.out.print("X ");
                }
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        Queens8 queens8 = new Queens8();
        for(int startPos = 0; startPos < 8; startPos ++)
        {
            queens8.printQueenPositions(queens8.get8Queens(startPos));
            System.out.println("");
            System.out.println("");
        }
    }

    private boolean checkNoConflict(int row, int column, List<Integer> queenPositions)
    {
        for(int i=0; i < queenPositions.size(); i++)
        {
            int j = queenPositions.get(i);
            if(j== -1)
            {
                break;
            }

            if(i == row || j == column)
            {
                return false;
            }

            //diagonal check
            if(Math.abs(i - row) == Math.abs(j - column))
            {
                return false;
            }

        }
        return true;
    }
}
