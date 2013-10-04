package sorters;

public class MergerSorter extends Sorter {
	
	public MergerSorter(int[] arr, long milisecondDelay) {
		super(arr, milisecondDelay);
	}

	@Override
	public void sortInternal() {
		mergeSort(0, length());
	}
	
	private void mergeSort(int start, int end){
		if (end - start < 2) return;
		
		int middle = (end + start) / 2;
		
		mergeSort(start, middle);
		mergeSort(middle, end);
		
		merge(start, middle, end);
	}

	private void merge(int firstStart, int middle, int secondEnd) {
		int idxOne = firstStart;
		int idxTwo = middle;

		indicateCurrentIdx(firstStart, secondEnd, middle, idxOne, idxTwo);
		while (idxOne < secondEnd && idxTwo < secondEnd) {
			indicateCurrentIdx(firstStart, middle, secondEnd);
			if (get(idxOne) > get(idxTwo)) {
				swap(idxOne, idxTwo);
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
