package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
	private ConfigDialog confid = new ConfigDialog();

	public SortFrame() {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setUpMenu();
		pack();
		setVisible(true);
	}

	public void setSorter(Sorter s) {
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
					setSorter(sorter);
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
		mergeOption.addActionListener(new AddSortActionListenrer(this, "merge sort", confid));
		newSortOption.add(mergeOption);

		JMenuItem simpQuickOption = new JMenuItem("Simple Quick Sort");
		simpQuickOption.addActionListener(new AddSortActionListenrer(this, "simple quick sort", confid));
		newSortOption.add(simpQuickOption);

		JMenuItem smatQuikOpt = new JMenuItem("Smarter Quick Sort");
		smatQuikOpt.addActionListener(new AddSortActionListenrer(this, "smarter quick sort", confid));
		newSortOption.add(smatQuikOpt);

		JMenuItem insertOption = new JMenuItem("Insertion Sort");
		insertOption.addActionListener(new AddSortActionListenrer(this, "insertion sort", confid));
		newSortOption.add(insertOption);

		JMenuItem bubbleOpt = new JMenuItem("Bubble Sort");
		bubbleOpt.addActionListener(new AddSortActionListenrer(this, "bubble sort", confid));
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

	private static class AddSortActionListenrer implements ActionListener {
		SortFrame parent;
		String sortType;
		ConfigDialog config;

		public AddSortActionListenrer(SortFrame parent, String sortType, ConfigDialog config) {
			this.parent = parent;
			this.sortType = sortType;
			this.config = config;
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			int[] arrayToSort = new int[0];
			String arrayType = config.getArrayType();
			// "random", "sorted", "reverse sorted", "semi-sorted"
			if (arrayType.equals("random")) {
				arrayToSort = SorterTester.createRandomArray(config.getArrayLenght());
			} else if (arrayType.equals("sorted")) {
				arrayToSort = SorterTester.createSortedArray(config.getArrayLenght());
			} else if (arrayType.equals("reverse sorted")) {
				arrayToSort = SorterTester.createReverseSortedArray(config.getArrayLenght());
			} else if (arrayType.equals("semi-sorted")) {
				arrayToSort = SorterTester.createAlmostSortedArray(config.getArrayLenght(), config.getNumberOfSwaps());
			}
			if (sortType.equals("merge sort")) {
				parent.setSorter(new MergeSorter(arrayToSort, config.getMiliDelay()));
			} else if (sortType.equals("insertion sort")) {
				parent.setSorter(new InsertionSorter(arrayToSort, config.getMiliDelay()));
			} else if (sortType.equals("bubble sort")) {
				parent.setSorter(new BubbleSorter(arrayToSort, config.getMiliDelay()));
			} else if (sortType.equals("simple quick sort")) {
				parent.setSorter(new SimpleQuickSorter(arrayToSort, config.getMiliDelay()));
			} else if (sortType.equals("smarter quick sort")) {
				parent.setSorter(new SmarterQuickSorter(arrayToSort, config.getMiliDelay()));
			} else {
				throw new RuntimeException("Did not understand sort type: " + sortType);
			}
		}
	}
}
