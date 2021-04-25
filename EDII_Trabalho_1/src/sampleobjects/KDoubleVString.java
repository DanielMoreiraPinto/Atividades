package sampleobjects;

public class KDoubleVString implements Comparable<KDoubleVString> {

	// Classe de dado com chave Double e valor String
	
	private Double key;
	private String value;
	
	public KDoubleVString(Double key, String value) {
		this.key = key;
		this.value = value;
	}
	
	public Double getKey() {
		return key;
	}
	public void setKey(Double key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString() {
		return (this.getKey() + ": " + this.getValue());
	}

	@Override
	public int compareTo(KDoubleVString arg0) {
		if(this.getKey() > arg0.getKey()) return 1;
		if(this.getKey() < arg0.getKey()) return -1;
		return 0;
	}
	
}
