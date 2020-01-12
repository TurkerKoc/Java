import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class RadioButtonFrame extends JFrame
{
	private JTextField textField;
	private Font plainFont;
	private Font boldFont;
	private Font italicFont;
	private Font boldItalicFont;
	private JRadioButton plainRadioButton;
	private JRadioButton boldRadioButton;
	private JRadioButton italicRadioButton;
	private JRadioButton boldItalicRadioButton;
	private ButtonGroup radioGroup;
	
	public RadioButtonFrame() {
		super("RadioButton Test");
		setLayout(new FlowLayout());
		textField = new JTextField("Watch the font style change",25);
		add(textField);
		
		plainRadioButton = new JRadioButton("Plain",true); //isSelected = true
		boldRadioButton = new JRadioButton("Bold",false);
		italicRadioButton = new JRadioButton("Italic",false);
		boldItalicRadioButton = new JRadioButton("Bold/italic",false);
		add(boldItalicRadioButton);
		add(italicRadioButton);
		add(boldRadioButton);
		add(plainRadioButton);
		
		radioGroup = new ButtonGroup();
		radioGroup.add(plainRadioButton);
		radioGroup.add(boldItalicRadioButton);
		radioGroup.add(italicRadioButton);
		radioGroup.add(boldRadioButton);
		
		plainFont = new Font("Arial",Font.PLAIN,14);
		boldFont = new Font("Arial",Font.BOLD,14);
		boldItalicFont = new Font("Arial",Font.BOLD+Font.ITALIC,14);
		italicFont = new Font("Arial",Font.ITALIC,14);
		
		plainRadioButton.addItemListener(new RadioButtonHandler(plainFont));
		boldRadioButton.addItemListener(new RadioButtonHandler(boldFont));
		boldItalicRadioButton.addItemListener(new RadioButtonHandler(boldItalicFont));
		italicRadioButton.addItemListener(new RadioButtonHandler(italicFont));
	}	
		private class RadioButtonHandler implements ItemListener
		{
			private Font font;
			public RadioButtonHandler(Font f) 
			{
				font = f;
			}
			@Override
			public void itemStateChanged(ItemEvent e) {
				textField.setFont(font);				
			}
			
		}
		
	
	
}
