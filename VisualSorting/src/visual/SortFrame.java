package visual;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import sorters.ProgressListener;
import sorters.Sorter;

public class SortFrame extends JFrame {

	private ArrayPanel arPane;
	private Sorter sorter;

	public SortFrame(Sorter s) {
		super();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.sorter = s;
		arPane = new ArrayPanel(s);
		this.add(arPane);
		s.addProgressListener(new ProgressListener() {
			@Override
			public void stepPerformed() {
				arPane.repaint();
			};
		});
		setUpMenu();
		pack();
		setVisible(true);
	}

	private final void setUpMenu() {
		JMenuBar topMenu = new JMenuBar();
		JMenu fileMenu = new JMenu("file");
		JMenuItem goOption = new JMenuItem("go");
		goOption.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
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
		fileMenu.add(goOption);
		topMenu.add(fileMenu);
		setJMenuBar(topMenu);
	}
}
