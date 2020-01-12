import java.awt.Dimension;
import java.awt.FlowLayout; //specifies how components will be arranged

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class LabelFrame extends JFrame 
{
	private JLabel label1;
	private JLabel label2;
	private JLabel label3;
	
	public LabelFrame() 
	{
		super("Testing JLabel");
		
		
		//FlowLayout: When there is no more room to fit components left to right, components continue 
		//to display left to right on the next line.
		setLayout(new FlowLayout()); //specifies how components will be arranged
		
		label1 = new JLabel("Label with text");
		label1.setToolTipText("This is label1");
		add(label1);
		
		Icon bug;
		try {
			bug = new ImageIcon(getClass().getResource("/sheet.png"));
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		label2 = new JLabel("Label with text and icon", bug, SwingConstants.LEFT);
		add(label2);
		
		label3 = new JLabel();
		label3.setText("Label with icon and text at bottom1");
		label3.setIcon(bug);
		label3.setHorizontalTextPosition(SwingConstants.CENTER);
		label3.setVerticalTextPosition(SwingConstants.BOTTOM);
		label3.setToolTipText("This is label3");
		add(label3);
		
	}


}
