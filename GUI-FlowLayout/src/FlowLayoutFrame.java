import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class FlowLayoutFrame extends JFrame 
{
	private JButton leftJButton;
	private JButton centerJButton;
	private JButton rightJButton;
	private FlowLayout layout;
	private Container container;
	
	public FlowLayoutFrame() {
		super("FlowLayout Frame");
		layout = new FlowLayout();
		container = getContentPane();
		setLayout(layout);
		
		leftJButton = new JButton("Left");
		add(leftJButton);
		leftJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						layout.setAlignment(FlowLayout.LEFT);
						layout.layoutContainer(container);
					}
				}
			);
		
		centerJButton = new JButton("Center");
		add(centerJButton);
		centerJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						layout.setAlignment(FlowLayout.CENTER);
						layout.layoutContainer(container);
					}
				}
			);
		rightJButton = new JButton("Right");
		add(rightJButton);
		rightJButton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						layout.setAlignment(FlowLayout.RIGHT);
						layout.layoutContainer(container);
					}
				}
			);
	}
}
