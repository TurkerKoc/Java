import java.util.EmptyStackException;
import java.util.Stack;

public class StackTest 
{
	public static void main(String args[])
	{
		Stack<Number> stack = new Stack<Number>();
		stack.push(12L);
		printStack(stack);
		stack.push(3421);
		printStack(stack);
		stack.push(1.0F);
		printStack(stack);
		stack.push(1234.5678);
		printStack(stack);
		
		try {
			Number removedObject = null;
			while(true)
			{
				removedObject = stack.pop();
				System.out.println("Popped: "+removedObject);
				printStack(stack);
			}
		} catch (EmptyStackException e) 
		{
			System.out.println("empty!");
		}
		
	}
	private static void printStack(Stack<Number> stack)
	{
		if(stack.isEmpty())
			System.out.println("stack is empty!");
		else {
			System.out.println("stack: "+stack);
		}
			
	}

}
