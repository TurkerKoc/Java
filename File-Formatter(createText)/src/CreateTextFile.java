import java.io.FileNotFoundException;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CreateTextFile 
{
	private Formatter output; //object used to ouput text to file
	
	public void openFile()
	{
		try {
			output = new Formatter("/Users/turkerkoc/Desktop/client.txt");
		} catch (SecurityException e) {
			System.err.println("You do not have permission to acess this file");
		} catch (FileNotFoundException e2) {
			System.err.println("Error opening or creating file");
			System.exit(1);
		}		
	}
	
	public void addRecords()
	{
		AccountRecord record = new AccountRecord();
		Scanner input = new Scanner(System.in);
		System.out.printf("%s\n%s\n%s\n%s\n\n",
				"To terminate input, type the end-of-file indicator",
				"when you are promted to enter input.",
				"on UNIX/Linux/Mac OS x type <ctrl> d then press enter",
				"On windos ctrl z");
		System.out.printf("%s %s",
				"Enter account number(>0), first name, last name and balance.","? ");
		while(input.hasNext()) //ctrl d
		{
			try {
				record.setAccount(input.nextInt());
				record.setFirstName(input.next());
				record.setLastName(input.next());
				record.setBalance(input.nextDouble());
				
				if(record.getAccount()> 0)
				{
					output.format("%d %s %s %.2f\n", record.getAccount(),
							record.getFirstName(), record.getLastName(), record.getBalance());
				}
				else {
					System.out.println("Account number must be greater than 0.");
				}
			} catch (FormatterClosedException e) {
				System.err.println("Error writing to file.");
				return;
			} catch (NoSuchElementException e) {
				System.err.println("Invalid input. Please try again");
				input.nextLine(); //discard input so user can try again
			}
			System.out.printf("%s %s",
					"Enter account number(>0), first name, last name and balance.","? ");
		}
	}
	public void closeFile()
	{
		if(output != null)
			output.close();
	}
}
