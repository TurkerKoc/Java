import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class CheckBoxFrame extends JFrame 
{
	private JTextField textField; //displays text in changing fonts
	private JCheckBox boldJCheckBox;
	private JCheckBox italicJCheckBox;
	
	public CheckBoxFrame() {
		// TODO Auto-generated constructor stub
		super("JCheckBox Test");
		setLayout(new FlowLayout());
		
		textField = new JTextField("Watch the font style change", 20);
		textField.setFont(new Font("Arial", Font.PLAIN, 14));
		add(textField);
		
		boldJCheckBox = new JCheckBox("Bold");
		italicJCheckBox = new JCheckBox("Italic");
		add(boldJCheckBox);
		add(italicJCheckBox);
		
		CheckBoxHandler handler = new CheckBoxHandler();
		boldJCheckBox.addItemListener(handler);
		italicJCheckBox.addItemListener(handler);
	}
	private class CheckBoxHandler implements ItemListener
	{
		@Override
		public void itemStateChanged(ItemEvent e) {
			// TODO Auto-generated method stub
			Font font = null;
			if(boldJCheckBox.isSelected() && italicJCheckBox.isSelected())
				font = new Font("Turker", Font.BOLD + Font.ITALIC, 14);
			else if(boldJCheckBox.isSelected())
				font = new Font("Turker", Font.BOLD, 14);
			else if(italicJCheckBox.isSelected())
				font = new Font("Turker", Font.ITALIC, 14);
			else
				font = new Font("Turker", Font.PLAIN, 14);
			textField.setFont(font);
		}
	}
}
