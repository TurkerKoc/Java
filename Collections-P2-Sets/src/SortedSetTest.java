import java.util.Arrays;
import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetTest {

	public static void main(String args[])
	{
		String[] colors = {"red", "white", "blue", "white", "gray", "orange", "red"};
		SortedSet<String> tree = new TreeSet<String>(Arrays.asList(colors));
		System.out.println("Sorted set: ");
		printSet(tree);
		System.out.println("headSet(\"red\"): ");
		printSet(tree.headSet("red"));
		System.out.println("tailSet(\"red\"): ");
		printSet(tree.tailSet("red"));
		System.out.println("First: "+tree.first());
		System.out.println("Last: "+tree.last());

	}
	private static void printSet(SortedSet<String> set)
	{
		for(String s : set)
			System.out.println(s);
		System.out.println();
	}
}
