package net.nifoo.tij.lang;

public class Case {

	private int case1 = 1;
	private int case2 = 2;

	public void runCase(int v) {
		switch (v) {
		// case case1: //error
		case 1:
			System.out.println(case1);
			break;
		// case case2: //error
		case 2:
			System.out.println(case2);
			break;
		default:
			break;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
