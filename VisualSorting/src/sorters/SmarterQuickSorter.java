package sorters;

public class SmarterQuickSorter extends QuickSorter {

	public SmarterQuickSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	protected int pickPivotIndex(int start, int end) {
		return (start + end) / 2;
	}
}
