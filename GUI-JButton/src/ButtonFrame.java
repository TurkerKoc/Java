import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ButtonFrame extends JFrame
{
	private JButton plainJButton;
	private JButton fancyJButton;
	
	public ButtonFrame()
	{
		super("Testing Buttons");
		setLayout(new FlowLayout()); //it can overflow the frame
		
		plainJButton = new JButton("Plain Button");
		add(plainJButton);
		
		Icon bug1 = new ImageIcon(getClass().getResource("sheet.png"));
		Icon bug2 = new ImageIcon(getClass().getResource("bug2.png"));

		fancyJButton = new JButton("Fancy Button", bug1);
		fancyJButton.setRolloverIcon(bug2); //display bug2 image when the mouse position on fancyButton
		add(fancyJButton);
		
		//action performed when click occurs
		ButtonHandler handler = new ButtonHandler();
		fancyJButton.addActionListener(handler);
		plainJButton.addActionListener(handler);
	
		
	}
	//action specification
	private class ButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			JOptionPane.showMessageDialog(ButtonFrame.this,  String.format("you pressed: %s", e.getActionCommand()));
		}
	}

}
