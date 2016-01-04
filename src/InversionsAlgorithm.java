import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class InversionsAlgorithm 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		InversionsAlgorithm inversionsAlgorithm = new InversionsAlgorithm();
		ArrayList<Integer> inputIntegetList = readFile("/home/sundeep/Desktop/IntegerArray.txt") /*new ArrayList<Integer>()*/;
//		inputIntegetList.add(1);
//		inputIntegetList.add(8);
//		inputIntegetList.add(2);
//		inputIntegetList.add(1);
//		inputIntegetList.add(4);
//		inputIntegetList.add(2);
//		inputIntegetList.add(3);
//		inputIntegetList.add(7);
		System.out.println("Input Integer List Size " + inputIntegetList.size());
		System.out.println("Number of inversions "+ inversionsAlgorithm.countInversions(inputIntegetList));

	}
	
	public long countInversions(ArrayList<Integer> inputIntegerList)
	{
		@SuppressWarnings("unchecked")
		ArrayList<Integer> copy = (ArrayList<Integer>) inputIntegerList.clone();
		return countInversions(inputIntegerList, copy, 0, inputIntegerList.size());
	}
	
	private long countInversions(ArrayList<Integer> input, ArrayList<Integer> copy, int startIndex, int endIndex)
	{
		if((endIndex - startIndex) <= 1)
		{
			return 0;
		}
		long count  = 0;		
		int middleIndex = (startIndex + endIndex)/2;
		count += countInversions(copy, input, startIndex, middleIndex);
		count += countInversions(copy, input, middleIndex, endIndex);
		count += countSplitInversions(input, copy, startIndex, endIndex);
		return count;
	}
	
	private long countSplitInversions(ArrayList<Integer> input, ArrayList<Integer> copy, int startIndex, int endIndex)
	{
		int middleIndex = (startIndex + endIndex)/2;
		long count = 0;
		int p1 = startIndex, p2 = middleIndex, p3 = startIndex;
		while(p1 < middleIndex && p2 < endIndex)
		{
			if(input.get(p2) < input.get(p1))
			{
				copy.set(p3, input.get(p2));
				count += (middleIndex - p1);
				p2 ++;
			}
			else
			{
				copy.set(p3, input.get(p1));
				p1 ++;
			}
			p3 ++;
		}
		
		while(p1 < middleIndex)
		{
			copy.set(p3, input.get(p1));
			p1 ++;
			p3 ++;
		}
		
		while(p2 < endIndex)
		{
			copy.set(p3, input.get(p2));
			p2 ++;
			p3 ++;
		}
		
		return count;
	}
	
	
	public static ArrayList<Integer> readFile(String fileName)
	{
		ArrayList<Integer> integerList = new ArrayList<Integer>();
		try 
		{
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			String line = null;
			while((line = fileReader.readLine()) != null)
			{
				integerList.add(Integer.parseInt(line));
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		return integerList;
	}

}
