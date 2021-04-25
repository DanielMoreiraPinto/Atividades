package sampleobjects;

public class KIntVIntArr implements Comparable<KIntVIntArr> {

	// Classe de dado com chave int e valor int[]
	
	private int key;
	private int [] value;
	
	public KIntVIntArr(int key, int[] value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int[] getValue() {
		return value;
	}

	public void setValue(int[] value) {
		this.value = value;
	}
	
	public String toString() {
		return (this.getKey() + ": " + this.getValue()[0] + ", " 
				+ this.getValue()[1] + ", " + this.getValue()[2] + "...");
	}


	@Override
	public int compareTo(KIntVIntArr arg0) {
		return (this.key - arg0.getKey());
	}
	
}
