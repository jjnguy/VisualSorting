package sorters;

import java.util.ArrayList;
import java.util.List;

public abstract class Sorter {

	private int[] arr;
	private long readCount = 0;
	private long writeCount = 0;
	private boolean ran = false;
	private long totalMilis = 0;
	private long miliDelay;
	
	private List<ProgressListener> listeners = new ArrayList<>();

	public Sorter(int[] arr, long milisecondDelay) {
		this.arr = arr;
		this.miliDelay = milisecondDelay;
	}
	
	public void addProgressListener(ProgressListener pl){
		listeners.add(pl);
	}
	
	private void alertListeners(){
		for (ProgressListener lsitener : listeners) {
			lsitener.stepPerformed();
		}
	}
	
	protected void indicateProgress(){
		alertListeners();
		try {
			Thread.sleep(miliDelay);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sort() {
		if (ran)
			return;
		long start = System.currentTimeMillis();
		sortInternal();
		long end = System.currentTimeMillis();
		totalMilis = end - start;
		ran = true;
	}

	public long time() {
		if (!hasRun())
			throw new RuntimeException(
					"Cannot get time of sorter that has not yet been ran");
		return totalMilis;
	}

	public boolean hasRun() {
		return ran;
	}

	protected abstract void sortInternal();

	public abstract String sortName();

	protected void swap(int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		readCount += 2;
		writeCount += 2;
	}
	
	private int[] curIdx = new int[0];

	public int[] currentIdexOperatedOn(){
		return curIdx;
	}
	
	protected void indicateCurrentIdx(int... idx){
		curIdx = idx;
		alertListeners();
	}
	
	public int length() {
		return arr.length;
	}

	public int get(int idx) {
		readCount++;
		return arr[idx];
	}

	protected void set(int idx, int val) {
		writeCount++;
		arr[idx] = val;
	}

	protected void manualRead(){
		readCount++;
	}
	
	protected void manualWrite(){
		writeCount++;
	}
	
	public long writeCount() {
		return writeCount;
	}

	public long readCount() {
		return readCount;
	}

	protected int[] ifYouMustAbsolutelyHaveAccessToTheUnderlyingArrayUseThisMethodButPleaseDontIfYouReallyDontHaveToKThxBye() {
		return arr;
	}
}
