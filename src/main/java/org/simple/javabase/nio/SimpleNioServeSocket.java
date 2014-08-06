package org.simple.javabase.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class SimpleNioServeSocket {

	public static void handle() {

		ServerSocketChannel serverSocket;
		try {
			Selector selector = Selector.open();
			serverSocket = ServerSocketChannel.open();
			serverSocket.configureBlocking(false);
			serverSocket.socket().bind(new InetSocketAddress(InetAddress.getLocalHost(),19999));
			serverSocket.register(selector, SelectionKey.OP_ACCEPT);
			while (true) {
				System.out.println("listen for event ...");
				int select = selector.select();
				System.out.println("ready channel num:" + select);
				Set<SelectionKey> keys = selector.selectedKeys();
				Iterator<SelectionKey> iterator = keys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					switch (key.readyOps()) {
					case SelectionKey.OP_ACCEPT:
						doAccept(key);
						break;
					case SelectionKey.OP_CONNECT:
						doConnect(key);
						break;
					case SelectionKey.OP_READ:
						doRead(key);
						break;
					case SelectionKey.OP_WRITE:
						doWrite(key);
						break;
					}
					iterator.remove();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void doAccept(SelectionKey key) {
		System.out.println("do accept thing...");
		try {
			ServerSocketChannel server = (ServerSocketChannel) key.channel();
			SocketChannel sc = server.accept();
			sc.configureBlocking(false);
			// 注册读事件
			sc.register(key.selector(),SelectionKey.OP_READ,ByteBuffer.allocate(1024));
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void doConnect(SelectionKey key) {
		System.out.println("do connect thing...");
	}

	private static void doRead(SelectionKey key) {
		System.out.println("do read thing...");
		try {
			SocketChannel socketChannel = (SocketChannel) key.channel();
			if (socketChannel != null) {
				ByteBuffer buffer = (ByteBuffer) key.attachment();
				int size = socketChannel.read(buffer);
				StringBuffer strinBuffer=new StringBuffer();
				while (size != -1) {
					buffer.flip();
					while (buffer.hasRemaining()) {
//						System.out.println((char) buffer.get());
						strinBuffer.append((char)buffer.get());
					}
					buffer.clear();
					size = socketChannel.read(buffer);
				}
				socketChannel.close();
				System.out.println("accept data from client:"+strinBuffer.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void doWrite(SelectionKey key) {
		System.out.println("do write thing...");
	}

	public static void main(String[] args) {
		SimpleNioServeSocket.handle();
	}

}
