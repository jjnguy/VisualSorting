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
	private double scale;

	public ArrayPanel(Sorter sorter) {
		super(null);
		this.sorter = sorter;
		setPreferredSize(new Dimension(sorter.length(), 400));
		int maxValue = Integer.MIN_VALUE;
		for (int i = 0; i < sorter.length(); i++) {
			if (sorter.get(i) > maxValue)maxValue = sorter.get(i);
		}
		scale = 1/ (double) maxValue;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		int itemWidth = getWidth() / sorter.length();
		
		g2.setColor(Color.BLACK);
		g2.drawString("Name: " + sorter.sortName(), 5, 20);
		g2.drawString("Time: " + sorter.time(), 5, 40);
		g2.drawString("Reads: " + sorter.readCount(), 5, 60);
		g2.drawString("Writes: " + sorter.writeCount(), 5, 80);
		
		g2.setColor(Color.RED);
		for (int i = 0; i < sorter.length(); i++) {
			if (arrayContains(sorter.currentIdexOperatedOn(), i)) {
				g2.setColor(Color.GREEN);
			}
			g2.fillRect(itemWidth * i, getHeight(), itemWidth,
					(int) -(getHeight() *scale * sorter.get(i)));
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
