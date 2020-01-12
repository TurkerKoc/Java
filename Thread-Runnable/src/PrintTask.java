import java.util.Random;

public class PrintTask implements Runnable 
{
	private final int sleepTime;
	private final String taskName;
	private final static Random generator = new Random();
	
	public PrintTask(String name) 
	{
		taskName = name; 
		
		//random sleep time btw 0-5 sec
		sleepTime=generator.nextInt(5000); //milliseconds
	}
	
	//method run contains te code that a thread will execute
	@Override
	public void run() {
		try {
			System.out.printf("%s going to sleep for %d milliseconds.\n",taskName,sleepTime);
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.out.printf("%s %s\n",taskName,"terminated prematurely due to interruption");						
		}
		
		System.out.printf("%s done sleeping\n",taskName);
	}
}
