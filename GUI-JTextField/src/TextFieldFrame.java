import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class TextFieldFrame extends JFrame 
{
	private JTextField textField1;
	private JTextField textField2;
	private JTextField textField3;
	private JPasswordField passwordField;
	
	public TextFieldFrame() {
		super("Testing JTextField and JPasswordField");
		setLayout(new FlowLayout()); //can overflow the screen
		
		//10 columns occur
		textField1 = new JTextField(10);
		add(textField1);
		
		textField2 = new JTextField("Enter text here");
		add(textField2);
		
		//with default text uneditible text field with 21 columns
		textField3 = new JTextField("Uneditible text field", 21);
		textField3.setEditable(false);
		add(textField3);
		
		passwordField = new JPasswordField("Hidden Text");
		add(passwordField);
	
		//to display text written when enter is pressed
		TextFieldHandler handler = new TextFieldHandler();
		textField1.addActionListener(handler);
		textField2.addActionListener(handler);
		textField3.addActionListener(handler);
		passwordField.addActionListener(handler);
		
	}
	
	//event handler class
	private class TextFieldHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			String string = "";
			
			//user pressed enter in JTextField textfield1
			if(e.getSource() == textField1)
			{
				string = String.format("textField1: %s", e.getActionCommand()); //getActionC obtains the text user wrote
			}
			//enter in t2
			else if(e.getSource() == textField2)
			{
				string = String.format("textField2: %s", e.getActionCommand());
			}
			//enter in t3
			else if(e.getSource() == textField3)
			{
				string = String.format("textField3: %s", e.getActionCommand());
			}
			//enter in passwordField
			else if(e.getSource() == passwordField)
			{
				string = String.format("passwordField: %s", e.getActionCommand());
			}
			
			JOptionPane.showMessageDialog(null, string);
		}
	}

}

