package ru.geekbrains.finmanager.models;

/*import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
*/
public class Category {
/*	private static final Set<String> names = new HashSet<String>(Arrays.asList("other"));*/
	String name = "other";
	String description = "";

	public Category() {
	};

	public Category(String name) {
		this.name = name.toLowerCase();
	}

	public Category(String name, String description) {
		this.name = name.toLowerCase();
		this.description = description;
	}

	public String getName() {
		char ch = Character.toUpperCase(name.charAt(0));
		return name.replaceFirst("^.", Character.toString(ch));
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

/*	public static String[] getCategories() {
		return names.toArray(new String[0]);
	}*/

/*	public static boolean addCategory(String name) {
		boolean result = false;
		if (name != null) {
			result = names.add(name.toLowerCase());
		}
		return result;
	}*/
}
