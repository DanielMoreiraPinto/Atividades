package code;

@SuppressWarnings("rawtypes")
public class Item implements Comparable {

	private int id;
	private int[] data;
	
	public Item(int id, int[] data) {
		this.id = id;
		this.data = data;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int[] getData() {
		return data;
	}
	
	public void setData(int[] data) {
		this.data = data;
	}
	
	public String toString() {
		return "ID: " + this.id 
				+ " Dados: [" + this.data[0] + ", " 
				+ this.data[1] + ", " + this.data[2] + " , ...]";
	}

	@Override
	public int compareTo(Object arg0) {
		Item item  = (Item) arg0;
		return this.id - item.id;
	}
	
}
