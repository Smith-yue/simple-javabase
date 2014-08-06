package org.simple.javabase.nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.Pipe;

import org.junit.Test;

public class SimpleJavaNIOTest {

	@Test
	public void testReadWithFileChannel() {

		try {
			RandomAccessFile aFile = new RandomAccessFile("D:\\nio-data.txt",
					"rw");

			FileChannel inChannel = aFile.getChannel();

			ByteBuffer buf = ByteBuffer.allocate(16);

			int bytesRead = inChannel.read(buf);
			while (bytesRead != -1) {

				System.out.println("Read " + bytesRead);
				buf.flip();

				while (buf.hasRemaining()) {
					System.out.print((char) buf.get());
				}

				buf.clear();
				bytesRead = inChannel.read(buf);
			}
			aFile.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Test
	public void testWriteWithFileChannel() {
		try {
			RandomAccessFile writeFile = new RandomAccessFile(
					"D:\\nio-data.txt", "rw");
			FileChannel writeChannel = writeFile.getChannel();
			String str = "test write";
			ByteBuffer buf = ByteBuffer.allocate(10);
			buf.clear();
			buf.put(str.getBytes());
			buf.flip();
			while (buf.hasRemaining()) {
				writeChannel.write(buf, writeChannel.size());
			}
			writeChannel.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testCopyFileWithChannel() {

		try {
			RandomAccessFile fromFile = new RandomAccessFile(
					"D:\\nio-data.txt", "rw");
			FileChannel inChannel = fromFile.getChannel();

			RandomAccessFile toFile = new RandomAccessFile(
					"D:\\nio-data-des.txt", "rw");
			FileChannel toChannel = toFile.getChannel();
			toChannel.transferFrom(inChannel, 0, inChannel.size());
			// inChannel.transferTo(0, inChannel.size(), toChannel);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testTruncateWithChannel() {

		try {
			RandomAccessFile writeFile = new RandomAccessFile(
					"D:\\nio-data.txt", "rw");
			FileChannel writeChannel = writeFile.getChannel();
			writeChannel.truncate(8);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testPipe() {
		try {
			String demo = "hello";
			Pipe pipe = Pipe.open();
			Pipe.SinkChannel sinkChannel = pipe.sink();
			ByteBuffer buffer = ByteBuffer.allocate(1024);
			buffer.put(demo.getBytes());
			sinkChannel.write(buffer);
			sinkChannel.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
