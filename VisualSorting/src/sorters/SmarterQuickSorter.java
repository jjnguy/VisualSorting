package sorters;

public class SmarterQuickSorter extends QuickSorter {

	public SmarterQuickSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	protected int pickPivotIndex(int start, int end) {
		if (start - end > 2) {
			int middle = get((start + end) / 2);
			int first = get(start);
			int last = get(end - 1);

			if (first > middle) {
				if (middle > last) {
					return middle;
				} else if (first > last) {
					return last;
				} else {
					return first;
				}
			} else {
				if (first > last) {
					return first;
				} else if (middle > last) {
					return last;
				} else {
					return middle;
				}
			}
		}
		return (start + end) / 2;
	}

	@Override
	public String sortName() {
		return "Smarter Quick Sort";
	}
}
