package net.nifoo.tij.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.channels.spi.SelectorProvider;
import java.nio.charset.Charset;
import java.util.Iterator;


public class SocketRW {
	private static final int BSIZE = 1024;
	private static Charset charset = Charset.forName("UTF-8");// 创建GBK字符集  

	public static void main(String[] args) {

	}

	static class Server {
		private String host = "localhost";
		private int port = 9999;
		private Selector selector;

		public Server() {
		}

		public Server(String host, int port) {
			this.host = host;
			this.port = port;
		}

		public void init() throws IOException {
			selector = SelectorProvider.provider().openSelector();
			ServerSocketChannel serverChannel = ServerSocketChannel.open();
			serverChannel.configureBlocking(false);
			InetSocketAddress isa = new InetSocketAddress(this.host, this.port);
			serverChannel.socket().bind(isa);
			serverChannel.register(selector, SelectionKey.OP_ACCEPT);
		}

		public void doServer() throws IOException {
			while (true) {
				selector.select();

				Iterator<SelectionKey> selectedKeys = selector.selectedKeys().iterator();
				while (selectedKeys.hasNext()) {
					SelectionKey key = selectedKeys.next();
					selectedKeys.remove();
					if (!key.isValid()) {
						continue;
					}
					if (key.isAcceptable()) {
						accept(key);
					} else if (key.isReadable()) {
						read(key);
					} else if (key.isWritable()) {
						write(key);
					}
				}
			}

		}

		public void accept(SelectionKey key) throws IOException {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
			SocketChannel socketChannel1 = serverSocketChannel.accept();
			socketChannel1.configureBlocking(false);
			socketChannel1.register(selector, SelectionKey.OP_READ);
		}

		public void read(SelectionKey key) {

		}

		public void write(SelectionKey key) {

		}
	}

	static class Client {
		private String host = "localhost";
		private int port = 9999;

		public Client(String host, int port) {
			this.host = host;
			this.port = port;
		}

		public void doWork() throws IOException {
			InetSocketAddress isa = new InetSocketAddress(host, port);
			SocketChannel sc = SocketChannel.open();
			sc.connect(isa);
			
			ByteBuffer data = ByteBuffer.wrap("hello!".getBytes(charset));
			sc.write(data);
			// do something...
			ByteBuffer buff = ByteBuffer.allocate(1024);
			sc.read(buff);
		}
	}

}
