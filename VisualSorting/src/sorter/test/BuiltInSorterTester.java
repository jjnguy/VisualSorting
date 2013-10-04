package sorter.test;

import sorters.BuiltInSorter;
import sorters.Sorter;

public class BuiltInSorterTester extends SorterTester {

	@Override
	public Sorter createSorter(int[] arr) {
		return new BuiltInSorter(arr);
	}
}
