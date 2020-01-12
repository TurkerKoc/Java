
public interface Buffer
{
	//place int value into Buffer
	public void set(int value) throws InterruptedException;
	
	//return int value into Buffer
	public int get() throws InterruptedException;
}
