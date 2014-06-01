package net.nifoo.tij.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.Arrays;

public class PipedStream {

	public static void main(String[] args) throws IOException {

		PipedOutputStream out = new PipedOutputStream();
		PipedInputStream in = new PipedInputStream(out);
		new Show(in);

		InputStream is = System.in;

		byte[] quit = "quit".getBytes();

		byte[] buff = new byte[1024];
		int count = 0;

		while ((count = is.read(buff)) != 0) {
			if (Arrays.equals(quit, Arrays.copyOf(buff, 4))) {
				in.close();
				out.close();
				System.out.println("exit system!");
				break;
			}
			out.write(buff, 0, count);
		}

	}

	private static class Show implements Runnable {

		private InputStream in;

		public Show(InputStream in) throws IOException {
			this.in = in;
			new Thread(this).start();
		}

		public void run() {
			byte[] buff = new byte[1024];
			int count = 0;
			byte[] prompt = ">>".getBytes();
			try {
				while ((count = in.read(buff)) > 0) {
					System.out.write(prompt);
					System.out.write(Arrays.copyOf(buff, count));
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("exit show");
		}
	}

}
