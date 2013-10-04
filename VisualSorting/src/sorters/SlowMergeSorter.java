package sorters;

public class SlowMergeSorter extends Sorter {

	public SlowMergeSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	public void sortInternal() {
		mergeSort(ifYouMustAbsolutelyHaveAccessToTheUnderlyingArrayUseThisMethodButPleaseDontIfYouReallyDontHaveToKThxBye());
	}
	
	private void mergeSort(int[] array){
		if (array.length <= 1) return;
		
		int[] firstHalf = new int[array.length / 2];
		int[] secondHalf = new int[array.length - firstHalf.length];
		
		System.arraycopy(array, 0, firstHalf, 0, firstHalf.length);
		System.arraycopy(array, firstHalf.length, secondHalf, 0, secondHalf.length);
		
		mergeSort(firstHalf);
		mergeSort(secondHalf);
		
		merge(array, firstHalf, secondHalf);
	}

	private void merge(int[] resultArray, int[] one, int[] two) {
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
		return "Slow Merge Sort";
	}
}
