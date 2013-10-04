package visual;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Arrays;

import javax.swing.JPanel;

import sorters.Sorter;

public class ArrayPanel extends JPanel {

	private Sorter sorter;

	public ArrayPanel(Sorter sorter) {
		super(null);
		this.sorter = sorter;
		setPreferredSize(new Dimension(1200, 400));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int itemWidth = getWidth() / sorter.length();
		g2.setColor(Color.RED);
		for (int i = 0; i < sorter.length(); i++) {
			if (arrayContains(sorter.currentIdexOperatedOn(), i)) {
				g2.setColor(Color.GREEN);
			}
			g2.fillRect(itemWidth * i, 0, itemWidth,
					(int) (getHeight() * .01 * sorter.get(i)));
			g2.setColor(Color.RED);
		}
	}
	
	private static boolean arrayContains(int[] arr, int search){
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == search){
				return true;
			}
		}
		return false;
	}
}
