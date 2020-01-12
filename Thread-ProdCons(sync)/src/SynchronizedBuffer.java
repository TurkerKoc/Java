
public class SynchronizedBuffer implements Buffer 
{
	private int buffer = -1; //shared int by threads
	private boolean occupied = false; //wheter the buffer is occupied
	
	@Override
	public synchronized void set(int value) throws InterruptedException 
	{	
		while(occupied) //if it is occupied while another thread to read it
		{
			System.out.println("Producer tries to write.");
			displayState("Buffer full producer waits.");
			wait();
		}
		buffer = value; //if read by cons then write new value
		occupied = true;
		
		displayState("Producer writes " + buffer);
		notifyAll(); //tell waiting threads to enter runnable state
	}
	
	@Override
	public synchronized int get() throws InterruptedException {
		while(!occupied)
		{
			System.out.println("Consumer tries to read");
			displayState("Buffer empty. Consumer waits");
			wait();
		}
		occupied = false; //read if it is written
		displayState("Consumer reads " + buffer);
		notifyAll(); // waiting threads to enter runnable state
		return buffer;				
	}
	public void displayState(String operation)
	{
		System.out.printf("%-40s%d\t\t%b\n\n", operation, buffer, occupied);
	}
}
