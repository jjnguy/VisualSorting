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
		if (end - start <= 1) return;
		
		int firstHalf = length() / 2;
		
		mergeSort(0, firstHalf);
		mergeSort(firstHalf, end);
		
		merge(0, firstHalf, firstHalf, end);
	}

	private void merge(int firstStart, int firstEnd, int secondStart, int secondEnd) {
		int idxOne = 0;
		int idxTwo = 0;

		int idxCombined = 0;

		if (resultArray.length != one.length + two.length){
			throw new RuntimeException("Sub array length did not add up to result array length");
		}

		while (idxOne < one.length && idxTwo < two.length) {
			if (one[idxOne] < two[idxTwo]) {
				resultArray[idxCombined++] = one[idxOne++];
			} else {
				resultArray[idxCombined++] = two[idxTwo++];
			}
			manualRead();
			manualRead();
			manualRead();
			manualWrite();
			indicateProgress();
		}

		// now grab the leftovers from the remaining list

		if (idxOne == one.length) {
			// grab the rest of two
			for (int i = idxTwo; i < two.length; i++) {
				resultArray[idxCombined++] = two[idxTwo++];
				manualRead();
				manualWrite();
				indicateProgress();
			}
		} else {
			// grab the rest of one
			for (int i = idxOne; i < one.length; i++) {
				resultArray[idxCombined++] = one[idxOne++];
				manualRead();
				manualWrite();
				indicateProgress();
			}
		}
	}

	@Override
	public String sortName() {
		return "Merge Sort";
	}
}
