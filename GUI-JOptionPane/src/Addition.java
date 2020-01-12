import javax.swing.JOptionPane;
public class Addition 
{
	public static void  main(String[] args)
	{
		String firstNumber = JOptionPane.showInputDialog("Enter First Integer");
		String secondNumber = JOptionPane.showInputDialog("Enter second Integer");
		
		int number1 = Integer.parseInt(firstNumber);
		int number2 = Integer.parseInt(secondNumber);
		
		int sum = number1 +  number2;
		
		//first argument = where to put our frame in the screen (null = center of the screen)
		//second argument message to display: the sum is + sum = message
		//sum of two integers = frame name
		//showing message in plain message type --> (there are other types of message with images look at the slides)
		JOptionPane.showMessageDialog(null,"The sum is " + sum, "Sum of Two Integers", JOptionPane.PLAIN_MESSAGE);
	}
}
