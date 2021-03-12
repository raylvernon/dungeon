// UI.java
// one layer above Keyboard.java
// prints the numbered lists used for selecting options
// and whatever other stuff needs to be refactored out
// of Dungeon.java



import java.util.ArrayList;



public class UI
{
	// often we need a numbered list of available options for the user to choose
	public static <T> void printOptions(T elems[])
	{
		for(int i = 0; i < elems.length; i++)
			System.out.println(i + ". " + elems[i]);
	}
	
	public static <T> void printOptions(ArrayList<T> elems)
	{
		for(int i = 0; i < elems.size(); i++)
			System.out.println(i + ". " + elems.get(i));		
	}

	// and when we do, we want to know what option the user would like
	public static <T> T promptOptions(ArrayList<T> elems)
	{
		printOptions(elems);
		return elems.get(Keyboard.readInt() % elems.size());
	}

	public static <T> T promptOptions(T elems[])
	{
		printOptions(elems);
		return elems[Keyboard.readInt() % elems.length];
	}
}