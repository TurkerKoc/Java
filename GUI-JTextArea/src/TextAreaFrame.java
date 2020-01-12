import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextAreaFrame extends JFrame
{
	private JTextArea textArea1;
	private JTextArea textArea2;
	private JButton copyJbutton;
	 public TextAreaFrame() {
		super("TextArea");
		Box box = Box.createHorizontalBox();
		String demo = "hasjdkl\nsahdjklfhlfjkal\nhasdjadhajd\nahsdjk";
		textArea1 = new JTextArea(demo,10,15);
		box.add(new JScrollPane(textArea1));
		
		copyJbutton = new JButton("copy >>");
		box.add(copyJbutton);
		copyJbutton.addActionListener(
				new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						textArea2.setText(textArea1.getSelectedText());

					}
				}
			);
		textArea2 = new JTextArea(10,15);
		textArea2.setEditable(false);
		box.add(new JScrollPane(textArea2));
		add(box);
	}
}
