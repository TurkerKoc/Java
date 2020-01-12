import java.util.PriorityQueue;

public class PriorityQueueTest 
{
	public static void main(String[] args)
	{	
		PriorityQueue<Double> queue = new PriorityQueue<Double>();
		queue.offer(3.2);
		queue.offer(9.8);
		queue.offer(1.2);
		queue.offer(-1.2);
		queue.offer(-2.2);
		System.out.println("Polling from queue: ");
		while(!queue.isEmpty())
		{
			System.out.println(queue.peek());
			System.out.println("queue: "+queue);
			queue.poll();
		}
	}

}
