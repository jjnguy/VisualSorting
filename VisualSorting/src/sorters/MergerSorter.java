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
		System.out.println("start: " + start + ", end: " + end);
		if (end - start <= 1) return;
		
		int firstHalf = (end - start) / 2 + start;
		
		mergeSort(start, firstHalf);
		mergeSort(firstHalf, end);
		
		merge(start, firstHalf, firstHalf, end);
	}

	private void merge(int firstStart, int firstEnd, int secondStart, int secondEnd) {
		int idxOne = firstStart;
		int idxTwo = secondStart;

		while (idxOne < firstEnd && idxTwo < secondEnd) {
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
