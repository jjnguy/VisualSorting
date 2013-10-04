package sorter.test;

import sorters.BubbleSorter;
import sorters.Sorter;

public class BubbleSorterTester extends SorterTester {
	@Override
	public Sorter createSorter(int[] arr) {
		return new BubbleSorter(arr, 0);
	}
}
