package net.nifoo.tij.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

public class FileRW {
	private static final int BSIZE = 1024;
	private static Charset charset = Charset.forName("UTF-8");// 创建GBK字符集  
	private static final String FNAME = "D:\\NIO_Temp.txt";

	public static void main(String[] args) throws IOException {

		File file = new File(FNAME);
		if (!file.exists()) {
			file.createNewFile();
		}

		ByteBuffer buff = null;

		// Write a file
		buff = ByteBuffer.wrap("Hello world!".getBytes("UTF-8"));
		FileChannel channel = new FileOutputStream(file).getChannel();
		channel.write(buff);
		channel.close();

		// append to a file
		buff = ByteBuffer.wrap("Some more...".getBytes("UTF-8"));
		channel = new RandomAccessFile(FNAME, "rw").getChannel();
		channel.position(channel.size());
		channel.write(buff);
		channel.close();

		// Read the file
		buff = ByteBuffer.allocate(BSIZE);
		channel = new FileInputStream(file).getChannel();
		channel.read(buff);
		buff.flip();
		while (buff.hasRemaining()) {
			System.out.print(charset.decode(buff));
		}
		channel.close();

		if (file.exists()) {
			file.deleteOnExit();
		}
	}

}
