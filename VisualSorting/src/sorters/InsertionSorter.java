package sorters;


public class InsertionSorter extends Sorter {

	public InsertionSorter(int[] arr, long milis) {
		super(arr, milis);
	}

	@Override
	protected void sortInternal()  {
		for (int i = 1; i < length(); i++) {
			indicateCurrentIdx(i);
			int valToInsert = get(i);
			int holePos = i;
			while (holePos > 0 && valToInsert < get(holePos - 1)) {
				indicateCurrentIdx(holePos, i);
				set(holePos, get(holePos - 1));
				holePos--;
				indicateProgress();
			}
			set(holePos, valToInsert);
		}
	}

	@Override
	public String sortName() {
		return "Insertion Sort";
	}
}
