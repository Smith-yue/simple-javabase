package org.simple.javabase.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class SimpleNioClientSocket {

	public static void send() {
		try {
			SocketChannel socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress(19999));
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			String newData = "New String to write to file..."
					+ System.currentTimeMillis();
			buffer.clear();
			buffer.put(newData.getBytes());
			buffer.flip();
			if(socketChannel.isConnectionPending()){
				socketChannel.finishConnect();
				while (buffer.hasRemaining()) {
					socketChannel.write(buffer);
				}
			}
			socketChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		SimpleNioClientSocket.send();
	}

}
