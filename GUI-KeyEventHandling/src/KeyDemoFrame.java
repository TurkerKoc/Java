import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class KeyDemoFrame extends JFrame implements KeyListener
{
	private String line1 = "";
	private String line2 = "";
	private String line3 = "";
	private JTextArea textArea;
	
	public KeyDemoFrame() 
	{
		super("Demonstrating key events");
		textArea = new JTextArea(10,15);
		textArea.setText("Press any key on keyboard");
		textArea.setEnabled(false);
		textArea.setDisabledTextColor(Color.BLACK);
		add(textArea);
		
		addKeyListener(this);
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		line1 = String.format("Key typed is: %s", e.getKeyChar());
		setLines2and3(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		line1 = String.format("Key released is: %s", e.getKeyChar());
		setLines2and3(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		KeyEvent.getKeyText(e.getKeyCode());
		setLines2and3(e);
		
	}
	
	private void setLines2and3(KeyEvent event)
	{
		boolean a = event.isActionKey();
		String tmp;
		if(a)
			tmp = "";
		else {
			tmp = "not ";
		}
		line2 = String.format("this key is" + tmp+ "an action key");
		String temp = KeyEvent.getKeyModifiersText(event.getModifiers());
		
		line3 = String.format("Modifier key pressed: %s", (temp.equals("")?"none ":temp));
		textArea.setText(String.format("%s\n%s\n%s\n",line1,line2,line3));
	}
	
}
