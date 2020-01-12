
public class AccountRecord 
{
	private int account;
	private String firstName;
	private String lastName;
	private double balance;
	
	public AccountRecord() {
		this(0, "", "", 0.0);
	}
	public AccountRecord(int acct, String first, String last, double bal)
	{
		setAccount(acct);
		setBalance(bal);
		setFirstName(first);
		setLastName(last);
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public int getAccount() {
		return account;
	}
	public double getBalance() {
		return balance;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	
}
