package sampleobjects;

public class KStringVDouble implements Comparable<KStringVDouble> {

	// Classe de dado com chave String e valor Double
	
	private String key;
	private Double value;
	
	public KStringVDouble(String key, Double value) {
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String toString() {
		return (this.getKey() + ": " + this.getValue());
	}
	
	@Override
	public int compareTo(KStringVDouble arg0) {
		return this.getKey().toLowerCase().compareTo(arg0.getKey().toLowerCase());
	}
	
}
