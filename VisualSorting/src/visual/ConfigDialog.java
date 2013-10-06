package visual;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class ConfigDialog extends JDialog {

	private JLabel arrayLengthLabel, arrayTypeLabel, swapCountLabel, delayLabel;
	private JLabel arrayLengthValueLAbel, swapCountValueLabel, delayValuelabel;
	private JSlider arrayLengthSlider, swapCountSlider, delaySlider;
	private JComboBox<String> arrayTypeCombo;
	
	private SortFrame parent;

	public ConfigDialog(SortFrame parent) {
		super();
		this.parent = parent;
		
		instantiateComponents();
		layoutomponents();
		addActionListeners();
		pack();
	}

	private final void instantiateComponents() {
		arrayLengthLabel = new JLabel("array length");
		arrayTypeLabel = new JLabel("array type");
		swapCountLabel = new JLabel("swap count");
		delayLabel = new JLabel("miliseconds to delay");

		arrayLengthSlider = new JSlider(200, 1500, 1000);
		arrayLengthSlider.setPaintTicks(true);
		arrayLengthSlider.setMajorTickSpacing(100);

		swapCountSlider = new JSlider(0, arrayLengthSlider.getValue() / 2, arrayLengthSlider.getValue() / 4);
		swapCountSlider.setPaintTicks(true);
		swapCountSlider.setMajorTickSpacing(50);

		delaySlider = new JSlider(0, 50, 1);
		delaySlider.setPaintTicks(true);
		delaySlider.setMajorTickSpacing(2);

		arrayTypeCombo = new JComboBox<>(new String[] { "random", "sorted", "reverse sorted", "semi-sorted" });

		arrayLengthValueLAbel = new JLabel("" + arrayLengthSlider.getValue());
		swapCountValueLabel = new JLabel("" + swapCountSlider.getValue());
		delayValuelabel = new JLabel("" + delaySlider.getValue());
	}

	private final void layoutomponents() {
		JPanel parent = new JPanel();
		parent.setLayout(new BoxLayout(parent, BoxLayout.Y_AXIS));

		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p1.add(arrayLengthLabel);
		p1.add(arrayLengthSlider);
		p1.add(arrayLengthValueLAbel);
		parent.add(p1);

		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p2.add(arrayTypeLabel);
		p2.add(arrayTypeCombo);
		parent.add(p2);

		JPanel p3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p3.add(swapCountLabel);
		p3.add(swapCountSlider);
		p3.add(swapCountValueLabel);
		parent.add(p3);

		JPanel p4 = new JPanel(new FlowLayout(FlowLayout.LEFT));
		p4.add(delayLabel);
		p4.add(delaySlider);
		p4.add(delayValuelabel);
		parent.add(p4);

		add(parent);
	}

	private final void addActionListeners() {
		hookSliderToLabel(arrayLengthSlider, arrayLengthValueLAbel);
		arrayLengthSlider.addChangeListener(updateParentChangeListener);
		hookSliderToLabel(delaySlider, delayValuelabel);
		delaySlider.addChangeListener(updateParentChangeListener);
		hookSliderToLabel(swapCountSlider, swapCountValueLabel);
		swapCountSlider.addChangeListener(updateParentChangeListener);
		
		arrayTypeCombo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.resetUsingConfig();
			}
		});
	}

	public int getArrayLenght() {
		return arrayLengthSlider.getValue();
	}

	public String getArrayType() {
		return arrayTypeCombo.getSelectedItem().toString();
	}

	public int getNumberOfSwaps() {
		return swapCountSlider.getValue();
	}

	public long getMiliDelay() {
		return delaySlider.getValue();
	}
	
	private final ChangeListener updateParentChangeListener = new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
			parent.resetUsingConfig();
		}
	};

	private static void hookSliderToLabel(final JSlider slider, final JLabel label) {
		slider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				label.setText("" + slider.getValue());
			}
		});
	}
}
