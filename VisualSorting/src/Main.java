import javax.swing.JFrame;
import javax.swing.JPanel;

import sorter.test.SorterTester;
import sorters.BubbleSorter;
import sorters.InsertionSorter;
import sorters.ProgressListener;
import sorters.QuickSorter;
import sorters.SlowMergeSorter;
import sorters.Sorter;
import visual.ArrayPanel;

public class Main {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		JFrame frame = new JFrame();
		Sorter s = new QuickSorter(SorterTester.createRandomArray(500), 1);
		final JPanel arPanel = new ArrayPanel(s);
		s.addProgressListener(new ProgressListener() {
			@Override
			public void stepPerformed() {
				arPanel.repaint();
			}
		});
		frame.add(arPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		s.sort();
		/*
		
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
