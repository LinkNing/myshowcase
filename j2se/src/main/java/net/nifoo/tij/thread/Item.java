package net.nifoo.tij.thread;

public class Item {

	private String info;

	/**
	 * Method getInfo.
	 * 
	 * @return String
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * Method setInfo.
	 * 
	 * @param info
	 *            String
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return info;
	}

}
