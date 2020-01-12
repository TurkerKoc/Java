import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedBuffer implements Buffer 
{
	//lock for controlling synchronization on threads
	private final Lock accessLock = new ReentrantLock();
	
	//conditions to control read and write
	private final Condition canWrite = accessLock.newCondition();
	private final Condition canRead = accessLock.newCondition();
	
	private int buffer = -1;
	private boolean occupied = false;
	
	@Override
	public synchronized void set(int value) throws InterruptedException 
	{	
		accessLock.lock(); //Lock this object
		try {
			while(occupied)
			{
				System.out.println("Producer tries to write");;
				displayState("Buffer full, Producer waits.");
				canWrite.await(); //wait until buffer is empty //also releasing lock while waiting
			}
			
			buffer = value; // set new value
			
			//indicate that cannot store another value
			occupied = true;
			displayState("Producer writes " + buffer);
			
			canRead.signal(); //wakes up one waiting thread for reading
		} 
		finally {
			accessLock.unlock(); //unlock object no matter what happens
		}
	}
	
	@Override
	public synchronized int get() throws InterruptedException 
	{
		int readValue = 0;
		accessLock.lock(); //lock this object
		
		try {
			while(!occupied)
			{
				System.out.println("Consumer tries to read.");
				displayState("Buffer empty. Consumer waits");
				canRead.await(); //wait until buffer is occupied // also release lock
			}
			occupied = false;
			readValue = buffer;
			displayState("Consumer reads " + readValue);
			
			canWrite.signal(); //wakes up one waiting thread for writing
		}
		finally {
			accessLock.unlock();
		}
		
		return readValue;
	}
	public void displayState(String operation)
	{
		System.out.printf("%-40s%d\t\t%b\n\n", operation, buffer, occupied);
	}
}
