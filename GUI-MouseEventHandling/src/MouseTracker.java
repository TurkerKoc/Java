import javax.swing.JFrame;

public class MouseTracker 
{
	public static void main(String[] args)
	{
		/*MouseTrackerFrame mouseTrackerFrame = new MouseTrackerFrame();
		mouseTrackerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mouseTrackerFrame.setSize(300,100);
		mouseTrackerFrame.setVisible(true);
		*/
		MouseDetailsFrame mouseDetailsFrame = new MouseDetailsFrame();
		mouseDetailsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mouseDetailsFrame.setSize(300,100);
		mouseDetailsFrame.setVisible(true);	}
}
