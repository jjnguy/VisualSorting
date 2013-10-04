package sorters;

import java.util.Arrays;

public class MergeSorter extends Sorter {

	public MergeSorter(int[] arr, long milisecondDelay) {
		super(arr, milisecondDelay);
	}

	@Override
	public void sortInternal() {
		mergeSort(0, length());
	}

	private void mergeSort(int start, int end) {
		if (end - start < 2)
			return;

		int middle = (end + start) / 2;

		mergeSort(start, middle);
		mergeSort(middle, end);

		merge(start, middle, end);
	}

	private void merge(int firstStart, int middle, int secondEnd) {
		int idxOne = firstStart;

		indicateCurrentIdx(firstStart, secondEnd, middle, idxOne);
		while (idxOne < middle && middle < secondEnd) {
			indicateCurrentIdx(firstStart, middle, secondEnd, idxOne);
			if (get(idxOne) > get(middle)) {
				// move two in front of one, and shift the rest back
				int tempVal = get(middle);
				for (int i = middle; i > idxOne; i--) {
					set(i, get(i-1));
				}
				set(idxOne, tempVal);
				middle++;
			}
			idxOne++;
			indicateProgress();
		}

		// now grab the leftovers from the remaining list

	}

	@Override
	public String sortName() {
		return "Merge Sort";
	}
}
