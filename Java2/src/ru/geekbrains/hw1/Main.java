package ru.geekbrains.hw1;

public class Main {

	public static void main(String[] args) {
		GenericStack<String> ststr = new GenericStack<>();
		try {
			ststr.push("ОГО");
			ststr.push("Star");
			ststr.push("one");
			ststr.push("two");
			ststr.push(new Integer(983240189).toString());
			while (! ststr.isEmpty()) {
				System.out.println(ststr.pop());
			}
		} catch (StackException e) {
			e.printStackTrace();
		}

	}

}
