import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import sorter.test.SorterTester;
import sorters.BubbleSorter;
import sorters.InsertionSorter;
import sorters.MergeSorter;
import sorters.ProgressListener;
import sorters.QuickSorter;
import sorters.SimpleQuickSorter;
import sorters.SlowMergeSorter;
import sorters.SmarterQuickSorter;
import sorters.Sorter;
import visual.ArrayPanel;
import visual.SortFrame;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Sorter s = new SmarterQuickSorter(SorterTester.createAlmostSortedArray(1200, 10), 1);
		final SortFrame frame = new SortFrame();
		frame.resetUsingConfig();
/*
		long totalDumb = 0;
		long totalSmart = 0;
		for (int i = 0; i < 1000; i++) {
			int[] test = SorterTester.createAlmostSortedArray(100000, 10);
			Sorter dumb = new SimpleQuickSorter(Arrays.copyOf(test, test.length), 0);
			Sorter smart = new SmarterQuickSorter(Arrays.copyOf(test, test.length), 0);
			smart.sort();
			dumb.sort();
			totalDumb += dumb.time();
			totalSmart += smart.time();
		}
		System.out.println("Total smart: " + totalSmart);
		System.out.println("Total dumbb: " + totalDumb);
		*/
	}
}
