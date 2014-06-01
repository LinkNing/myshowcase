package net.nifoo.tij.io;

import java.io.File;
import java.util.Arrays;

public class FileFoo {
	public static void main(String[] args) {
		File f = new File("c:/", "mi/test.txt");
		File f2 = new File("c:/mi", "/test.txt");
		System.out.println(f.equals(f2));

		System.out.println(f.isAbsolute());
		System.out.println(f.getName());
		System.out.println(f.getPath());
		System.out.println(f.getParent());
		System.out.println(f.getAbsolutePath());
		System.out.println(f.toURI());
		System.out.println(Arrays.toString(f.listRoots()));

	}
}
