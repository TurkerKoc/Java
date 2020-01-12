import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListFrame extends JFrame 
{
	private JList colorJlist;
	private static final String[] colorNames = {"Black","Blue","Cyan","Dark Gray","Gray","Green","Red"};
	private static final Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,Color.GREEN,Color.RED};
	public ListFrame() {
		super("List test");
		setLayout(new FlowLayout());
		colorJlist = new JList(colorNames);
		colorJlist.setVisibleRowCount(5);
		colorJlist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		add(new JScrollPane(colorJlist)); //adding with scroll capability
		colorJlist.addListSelectionListener(
				new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) {
						getContentPane().setBackground(colors[colorJlist.getSelectedIndex()]);						
					}
				}
			);
	}
	
}
