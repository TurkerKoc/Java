import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SharedBufferTest {

	public static void main(String[] args) 
	{
		//thread pool with 2 threads
		ExecutorService application  = Executors.newCachedThreadPool();
		
		//to store int shared by threads
		Buffer sharedLocation = new CircularBuffer();
		
		System.out.printf("%-40s%s\t\t%s\n%-40s%s\n\n","Operation","Buffer","Occupied","-----------","------\t\t------");
		
		
		application.execute(new Producer(sharedLocation));
		application.execute(new Consumer(sharedLocation));
		
		application.shutdown();
	}

}
