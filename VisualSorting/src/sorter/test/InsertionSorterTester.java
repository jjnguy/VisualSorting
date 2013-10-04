package sorter.test;

import sorters.InsertionSorter;
import sorters.Sorter;

public class InsertionSorterTester extends SorterTester {

	@Override
	public Sorter createSorter(int[] arr) {
		return new InsertionSorter(arr, 0);
	}
}
