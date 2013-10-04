package sorters;

public abstract class QuickSorter extends Sorter {

	public QuickSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	public void sortInternal() {
		sort(0, length());
	}

	private void sort(int start, int end) {
		if (Math.abs(start - end) <= 1) {
			return;
		}
		int pivIdx = partition(start, end - 1);
		sort(start, pivIdx);
		sort(pivIdx, end);
	}
	
	protected abstract int pickPivotIndex(int start, int end);

	private int partition(int start, int end) {
		int partitionIndex = pickPivotIndex(start, end);
		int partitionValue = get(partitionIndex);
		swap(partitionIndex, end);
		int high = end - 1;
		int low = start;
		indicateCurrentIdx(low, high);
		while (low <= high) {
			if (get(low) > partitionValue) {
				swap(low, high--);
			} else if (get(high) < partitionValue) {
				swap(low++, high);
			} else {
				low++;
				high--;
			}
			indicateProgress();
			indicateCurrentIdx(low, high);
		}
		return (low);
	}

	@Override
	public String sortName() {
		return "Quick Sort";
	}
}
