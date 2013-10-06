package sorters;

public class SimpleQuickSorter extends QuickSorter {

	public SimpleQuickSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	protected int pickPivotIndex(int start, int end) {
		return start;
	}

	@Override
	public String sortName() {
		return "Simple Quick Sort";
	}
}
