import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class FindPrimes extends JFrame
{
	private final JTextField highestPrimeJTextField = new JTextField();
	private final JButton getPrimesJButton = new JButton("Get Primes");
	private final JTextArea displayPrimeJTextArea = new JTextArea();
	private final JButton cancelJButton = new JButton("Cancel");
	private final JProgressBar progressJProgressBar = new JProgressBar();
	private final JLabel statusJLabel = new JLabel();
	private PrimeCalculator calculator;
	
	public FindPrimes() {
		super("Finding Primes");
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		northPanel.add(new JLabel("Find Primes less than: "));
		highestPrimeJTextField.setColumns(5);
		northPanel.add(highestPrimeJTextField);
		
		getPrimesJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				progressJProgressBar.setValue(0);
				displayPrimeJTextArea.setText("");
				statusJLabel.setText("");
				
				int number;
				 try {
					number = Integer.parseInt(highestPrimeJTextField.getText());
				} catch (NumberFormatException e2) {
					statusJLabel.setText("Enter an integer.");
					return;
				}
				calculator = new PrimeCalculator(number, displayPrimeJTextArea, statusJLabel, getPrimesJButton, cancelJButton);
				calculator.addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						if(evt.getPropertyName().equals("progress"))
						{
							int newValue = (Integer)evt.getNewValue();
							progressJProgressBar.setValue(newValue);
						}
						
					}
				});
				getPrimesJButton.setEnabled(false);
				cancelJButton.setEnabled(true);
				
				calculator.execute();
			}
		});
		northPanel.add(getPrimesJButton);
		
		displayPrimeJTextArea.setEditable(false);
		add(new JScrollPane(displayPrimeJTextArea,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER));
		
		JPanel southJPanel = new JPanel(new GridLayout(1,3,10,10));
		cancelJButton.setEnabled(false);
		cancelJButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				calculator.cancel(true);
				
			}
		});
		southJPanel.add(cancelJButton);
		progressJProgressBar.setStringPainted(true);
		southJPanel.add(progressJProgressBar);
		southJPanel.add(statusJLabel);
		
		add(northPanel, BorderLayout.NORTH);
		add(southJPanel, BorderLayout.SOUTH);
		setSize(350,300);
		setVisible(true);
	}
	public static void main(String[] args)
	{
		FindPrimes app = new FindPrimes();
		app.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
