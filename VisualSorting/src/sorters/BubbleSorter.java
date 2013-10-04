package sorters;

public class BubbleSorter extends Sorter {

	public BubbleSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	public void sortInternal() {
		boolean swapped = false;
		do {
			swapped = false;
			for (int i = 0; i < length() - 1; i++) {
				indicateCurrentIdx(i, i+1);
				if (get(i) > get(i + 1)) {
					swap(i, i + 1);
					swapped = true;
				}
				indicateProgress();
			}
		} while (swapped);
	}

	@Override
	public String sortName() {
		return "Bubble Sort";
	}
}
