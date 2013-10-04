package sorters;

import java.util.Arrays;

public class BuiltInSorter extends Sorter {

	public BuiltInSorter(int[] arr) {
		super(arr, 0);
	}

	@Override
	public void sortInternal() {
		Arrays.sort(ifYouMustAbsolutelyHaveAccessToTheUnderlyingArrayUseThisMethodButPleaseDontIfYouReallyDontHaveToKThxBye());
	}

	@Override
	public String sortName() {
		return "Built In Sort";
	}
}
