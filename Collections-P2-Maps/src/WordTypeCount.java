import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 * 
 */

/**
 * @author turkerkoc
 *
 */
public class WordTypeCount {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{		
		Map<String,Integer> myMap = new HashMap<String, Integer>();
		createMap(myMap);
		displayMap(myMap);		
	}
	public static void createMap(Map<String, Integer>  map)
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string:");
		String a = input.nextLine();
		String[] tokens = a.split(" ");
		for(String token : tokens)
		{
			String word = token.toLowerCase();
			if(map.containsKey(word))
			{
				int count = map.get(word);
				map.put(word, count+1);				
			}
			else {
				map.put(word, 1);
			}
		}
		
	}
	public static void displayMap(Map<String, Integer> map)
	{
		Set<String> keys = map.keySet();
		TreeSet<String> sortedKeys = new TreeSet<String>(keys);
		System.out.println("\nMap contains: \nKey\t\t Value");		
		for(String key : sortedKeys)
			System.out.printf("%-10s%10s\n",key,map.get(key));
		System.out.printf("\nsize: %d\nisEmpty: %b\n",map.size(),map.isEmpty());
	}

}
