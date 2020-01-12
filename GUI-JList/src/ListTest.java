import javax.swing.JFrame;

public class ListTest {

	public static void main(String[] args) 
	{		
		/*ListFrame listFrame = new ListFrame();
		listFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		listFrame.setSize(350,150);
		listFrame.setVisible(true);*/
		MultipleSelectionListFrame multipleSelectionListFrame = new MultipleSelectionListFrame();
		multipleSelectionListFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		multipleSelectionListFrame.setSize(350,150);
		multipleSelectionListFrame.setVisible(true);
	}

}
