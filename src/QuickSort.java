import java.util.ArrayList;
import java.util.List;


public class QuickSort {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
		ArrayList<Integer> a = InversionsAlgorithm.readFile("/home/sundeep/Desktop/QuickSort.txt") /*new ArrayList<>()*/;
		/*a.add(8);
		a.add(7);
		a.add(6);
		a.add(5);
		a.add(4);
		a.add(3);
		a.add(2);
		a.add(1);*/		
		int numOfComparisions = new QuickSort().sort(a);
		System.out.println("Num of comparisions " + numOfComparisions);
	}
	
	public int sort(ArrayList<Integer> a)
	{
		return sort(a, 0, a.size());
	}
	
	private int sort(ArrayList<Integer> a, int startIndex, int endIndex)
	{
		if(endIndex - startIndex <= 1)
		{
			return 0;
		}
		
		int pivotIndex = getPivotIndex(a, startIndex, endIndex);
		int numOfComparisions = endIndex - startIndex - 1;
		if(pivotIndex != startIndex)
		{
			swap(a, startIndex, pivotIndex);
			pivotIndex = startIndex;
		}

		for(int i=startIndex + 1; i < endIndex; i++)
		{
			if(a.get(i) < a.get(startIndex))
			{
				if(i > pivotIndex + 1)
				{
					swap(a, pivotIndex + 1, i);
				}
				pivotIndex += 1;
			}			
		}
		
		if(pivotIndex != startIndex)
		{
			swap(a, startIndex, pivotIndex);
		}
		
		numOfComparisions += sort(a, startIndex, pivotIndex);
		numOfComparisions += sort(a, pivotIndex + 1, endIndex);
		
		return numOfComparisions;
	}
	
	private void swap(ArrayList<Integer> a, int index1, int index2)
	{
		int temp = a.get(index1);
		a.set(index1, a.get(index2));
		a.set(index2, temp);		
	}
	
	private int getPivotIndex(List<Integer> a, int startIndex, int endIndex)
	{
		/*return startIndex;*/
		/*return endIndex - 1;*/
		return getMedianIndex(a, startIndex, endIndex);

	}
	
	private int getMedianIndex(List<Integer> a, int startIndex, int endIndex)
	{
		int middleIndex = startIndex + (endIndex - startIndex - 1)/2;
		int startElement = a.get(startIndex);
		int endElement = a.get(endIndex - 1);		
		int middleElement = a.get(middleIndex);
		ArrayList<Integer> tempList = new ArrayList<>();
		tempList.add(startElement);
		tempList.add(endElement);
		tempList.add(middleElement);
		selectionSort(tempList);
		if(tempList.get(1) == startElement)
		{
			return startIndex;
		}
		else if(tempList.get(1) == endElement)
		{
			return endIndex - 1;
		}
		else
		{
			return middleIndex;
		}		
	}
	
	public void selectionSort(ArrayList<Integer> a)
	{
		for(int i=0; i < a.size() - 1; i++)
		{			
			int selectionIndex = i;			
			for(int j=i + 1; j < a.size(); j ++)
			{
				if(a.get(selectionIndex) > a.get(j))
				{
					selectionIndex = j;
				}
			}
			swap(a, i, selectionIndex);
		}		
	}
	
	public void insertionSort(ArrayList<Integer> a)
	{
		for(int i=0; i < a.size() - 1; i++)
		{
			for(int j=0; j < a.size(); j++)
			{
				if(a.get(i) > a.get(j))
				{
					swap(a, i, j);
				}
			}
		}
	}
}
