package sorter.test;

import sorters.QuickSorter;
import sorters.SimpleQuickSorter;
import sorters.Sorter;

public class QuickSorterTest extends SorterTester {

	@Override
	public Sorter createSorter(int[] arr) {
		return new SimpleQuickSorter(arr, 0);
	}
}
