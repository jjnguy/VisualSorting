package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingWorker;

import sorter.test.SorterTester;
import sorters.BubbleSorter;
import sorters.CompleteListener;
import sorters.InsertionSorter;
import sorters.MergeSorter;
import sorters.ProgressListener;
import sorters.SimpleQuickSorter;
import sorters.SmarterQuickSorter;
import sorters.Sorter;

public class SortFrame extends JFrame {

	private JMenuItem goOption;

	private Sorter sorter;
	private ArrayPanel arp;
	private ConfigDialog confid;
	
	private  String lastSortTypeSelected = "Merge Sort";

	public SortFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setUpMenu();
		
		confid = new ConfigDialog(this);
		
		pack();
		setVisible(true);
	}

	public void setSorter(Sorter s) {
		lastSortTypeSelected = s.sortName();
		if (arp != null) {
			remove(arp);
		}
		arp = new ArrayPanel(s);
		add(arp);
		pack();
		s.addProgressListener(new ProgressListener() {
			@Override
			public void stepPerformed() {
				arp.repaint();
			};
		});
		s.addCompleteListener(new CompleteListener() {
			@Override
			public void complete() {
				goOption.setEnabled(true);
			}
		});
		repaint();
		goOption.setEnabled(true);
		this.sorter = s;
	}

	private final void setUpMenu() {
		JMenuBar topMenu = new JMenuBar();

		JMenu fileMenu = new JMenu("file");
		goOption = new JMenuItem("go");
		goOption.setEnabled(false);
		goOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (sorter.hasRun()) {
					resetUsingConfig();
				}
				goOption.setEnabled(false);
				SwingWorker<Integer, Void> wurk = new SwingWorker<Integer, Void>() {
					@Override
					protected Integer doInBackground() throws Exception {
						sorter.sort();
						return null;
					}
				};
				wurk.execute();
			}
		});

		JMenu newSortOption = new JMenu("choose sort");

		JMenuItem mergeOption = new JMenuItem("Merge Sort");
		mergeOption.addActionListener(new AddSortActionListenrer(this, "Merge Sort"));
		newSortOption.add(mergeOption);

		JMenuItem simpQuickOption = new JMenuItem("Simple Quick Sort");
		simpQuickOption.addActionListener(new AddSortActionListenrer(this, "Simple Quick Sort"));
		newSortOption.add(simpQuickOption);

		JMenuItem smatQuikOpt = new JMenuItem("Smarter Quick Sort");
		smatQuikOpt.addActionListener(new AddSortActionListenrer(this, "Smarter Quick Sort"));
		newSortOption.add(smatQuikOpt);

		JMenuItem insertOption = new JMenuItem("Insertion Sort");
		insertOption.addActionListener(new AddSortActionListenrer(this, "Insertion Sort"));
		newSortOption.add(insertOption);

		JMenuItem bubbleOpt = new JMenuItem("Bubble Sort");
		bubbleOpt.addActionListener(new AddSortActionListenrer(this, "Bubble Sort"));
		newSortOption.add(bubbleOpt);

		fileMenu.add(newSortOption);

		fileMenu.add(goOption);
		topMenu.add(fileMenu);

		JMenuItem configItem = new JMenuItem("config");
		configItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				confid.setVisible(true);
			}
		});

		topMenu.add(configItem);

		setJMenuBar(topMenu);
	}
	
	public void resetUsingConfig(){
		resetUsingConfig(lastSortTypeSelected);
	}
	
	public void resetUsingConfig(String sortType){
		int[] arrayToSort = new int[0];
		String arrayType = confid.getArrayType();
		// "random", "sorted", "reverse sorted", "semi-sorted"
		if (arrayType.equals("random")) {
			arrayToSort = SorterTester.createRandomArray(confid.getArrayLenght());
		} else if (arrayType.equals("sorted")) {
			arrayToSort = SorterTester.createSortedArray(confid.getArrayLenght());
		} else if (arrayType.equals("reverse sorted")) {
			arrayToSort = SorterTester.createReverseSortedArray(confid.getArrayLenght());
		} else if (arrayType.equals("semi-sorted")) {
			arrayToSort = SorterTester.createAlmostSortedArray(confid.getArrayLenght(), confid.getNumberOfSwaps());
		}
		if (sortType.equals("Merge Sort")) {
			setSorter(new MergeSorter(arrayToSort, confid.getMiliDelay()));
		} else if (sortType.equals("Insertion Sort")) {
			setSorter(new InsertionSorter(arrayToSort, confid.getMiliDelay()));
		} else if (sortType.equals("Bubble Sort")) {
			setSorter(new BubbleSorter(arrayToSort, confid.getMiliDelay()));
		} else if (sortType.equals("Simple Quick Sort")) {
			setSorter(new SimpleQuickSorter(arrayToSort, confid.getMiliDelay()));
		} else if (sortType.equals("Smarter Quick Sort")) {
			setSorter(new SmarterQuickSorter(arrayToSort, confid.getMiliDelay()));
		} else {
			throw new RuntimeException("Did not understand sort type: " + sortType);
		}
	}

	private static class AddSortActionListenrer implements ActionListener {
		SortFrame parent;
		String sortType;

		public AddSortActionListenrer(SortFrame parent, String sortType) {
			this.parent = parent;
			this.sortType = sortType;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			parent.resetUsingConfig(sortType);
		}
	}
}
