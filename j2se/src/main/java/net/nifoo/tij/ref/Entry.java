package net.nifoo.tij.ref;

public class Entry {
	String value;

	public Entry(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}

	@Override
	public void finalize() {
		System.out.println(this.value + " finalized");
	}
}