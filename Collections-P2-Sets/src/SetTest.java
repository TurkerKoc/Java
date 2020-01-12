import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
public class SetTest 
{
	public static void main(String[] args) 
	{
		String[] colors = {"red", "white", "blue", "white", "gray", "orange", "red"};
		List<String> list = Arrays.asList(colors);
		System.out.println("List: "+list);
		printNonDuplicates(list);
		
	}
	private static void printNonDuplicates(Collection<String> values)
	{
		Set<String> set = new HashSet<String>(values);
		System.out.println("Non dublicates: ");
		for(String value : set)
			System.out.println(value);
		System.out.println();
	}

}
