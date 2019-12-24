public class Card {
	private String name; // Holds card's name Ex: "4 of Clubs"
	private int value; // Holds card's value Ex: 1 - 13
	
	public Card(String name, int value) { // Constructor method
		this.name = name;
		this.value = value;
	}
	
	public int getValue() { // Returns cards value
		return value;
	}
	
	public String getName() { // Returns cards name
		return name;
	}
	
	
	public String toString() { // Returns name
		return name;
	}
}
