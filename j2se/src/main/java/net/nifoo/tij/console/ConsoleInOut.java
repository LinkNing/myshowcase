package net.nifoo.tij.console;

import java.io.IOException;

import java.util.Arrays;

public class ConsoleInOut {

	public static void main(String[] args) {
		try {
			byte[] buff = new byte[1024];
			int len = System.in.read(buff);

			System.out.println(Arrays.toString(buff));

			System.out.write(buff);
			System.out.flush();

			System.out.print("\n$");
			System.out.write(13);
			System.out.write(10);

			String info = new String(buff, 0, len);
			System.out.print(info);
			System.out.print('$');
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
