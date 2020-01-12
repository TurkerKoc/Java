import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class MultipleSelectionListFrame extends JFrame
{
	private JList colorJlist;
	private JList copyJList;
	private JButton copyJButton;
	private static final String[] colorNames = {"Black","Blue","Cyan","Dark Gray","Gray","Green","Red"};
	private static final Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY, Color.GRAY,Color.GREEN,Color.RED};
	public MultipleSelectionListFrame() {
		super("Multiple Selection Lists");
		setLayout(new FlowLayout());
		colorJlist = new JList(colorNames);
		colorJlist.setVisibleRowCount(5);
		colorJlist.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		add(new JScrollPane(colorJlist));
		copyJButton = new JButton("copy >>>");
		copyJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						copyJList.setListData(colorJlist.getSelectedValues());
						
					}
				}
			);
		add(copyJButton);
		copyJList = new JList();
		copyJList.setVisibleRowCount(5);
		copyJList.setFixedCellWidth(100);
		copyJList.setFixedCellHeight(15);
		copyJList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		add(new JScrollPane(copyJList));
				
	}
}
