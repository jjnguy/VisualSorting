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
		// frame.addSorter(s);
		//s.sort();
		//System.out.println("Time: " + s.time());
		
		/*
		long totalDumb = 0;
		long totalSmart = 0;
		for (int i = 0; i < 10000; i++) {
			int[] test = SorterTester.createRandomArray(10000);
			Sorter dumb = new SimpleQuickSorter(Arrays.copyOf(test, test.length), 0);
			Sorter smart = new SmarterQuickSorter(Arrays.copyOf(test, test.length), 0);
			smart.sort();
			dumb.sort();
			totalDumb += dumb.time();
			totalSmart += smart.time();
		}
		System.out.println("Total smart: " + totalSmart);
		System.out.println("Total dumbb: " + totalDumb);
		
		int[] toTest = SorterTester.createRandomArray(100000);
		SorterTester test = new BubbleSorterTester();
		test.test(toTest);
		SorterTester test5 = new InsertionSorterTester();
		test5.test(toTest);
		SorterTester test2 = new SlowMergeSorterTester();
		test2.test(toTest);
		SorterTester test3 = new QuickSorterTest();
		test3.test(toTest);
		SorterTester test4 = new BuiltInSorterTester();
		test4.test(toTest);
		
		*/
	}
}
