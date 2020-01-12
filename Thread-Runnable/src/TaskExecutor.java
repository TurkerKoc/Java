import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskExecutor {

	public static void main(String[] args) {
		Thread thread1 = new Thread(new PrintTask("task1"));
		Thread thread2 = new Thread(new PrintTask("task2"));
		Thread thread3 = new Thread(new PrintTask("task3"));
		
		System.out.println("Starting Executor");
		
		//executorService to manage threads
		ExecutorService threadExecutor = Executors.newCachedThreadPool();
		
		//start threads and place in runnable state
		threadExecutor.execute(thread1);//Start task1
		threadExecutor.execute(thread2);//start task2
		threadExecutor.execute(thread3);//start task3
		
		threadExecutor.shutdown();
		System.out.println("Tasks started, main ends.\n");
	}

}
