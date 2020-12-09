package recitation;

import java.util.Arrays;

public class QuickSort
{
	public static <T extends Comparable<T>> void sort(T[] array)
		{
		quicksort(array, 0, array.length - 1);
		}

	private static <T extends Comparable<T>> void quicksort(T[] array, int first, int last)
		{
		if(first < last)
			{
			System.out.println(Arrays.toString(array));
			int pivIndex = partition(array, first, last);
			quicksort(array, first, pivIndex - 1);
			quicksort(array, pivIndex + 1, last);			
			}
		}
	
	private static <T extends Comparable<T>> int partition(T[] array, int first, int last)
		{
		T pivot = array[first];
		int i = first; //increasing index
		int j = last; //decreasing index
		
		do
			{
			//increment i
			while((i < j) && pivot.compareTo(array[i]) >= 0)
				{i++;}
			//decrement j
			while(pivot.compareTo(array[j]) < 0)
				{j--;}
			
			if(i < j)
				{
				swap(array, i, j);
				}
			
			}
		while(i < j);
		
		swap(array, first, j);
		return j;
		
		}
	
	private static <T extends Comparable<T>> void swap(T[] array, int i, int j)
		{
		T item = array[i];
		array[i] = array[j];
		array[j] = item;
		}

	
	public static void main(String args[])
		{
		Integer[] test = {4, 7, 2, 6, 9, 8, 5, 3, 1, 0};
		System.out.println(Arrays.toString(test));
		sort(test);
		System.out.println(Arrays.toString(test));
		}

}