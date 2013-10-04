package sorter.test;

import sorters.SlowMergeSorter;
import sorters.Sorter;

public class SlowMergeSorterTester extends SorterTester {

	@Override
	public Sorter createSorter(int[] arr) {
		return new SlowMergeSorter(arr, 0);
	}
}
