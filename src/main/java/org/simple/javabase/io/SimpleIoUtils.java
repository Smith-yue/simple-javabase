package org.simple.javabase.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class SimpleIoUtils {

	private final PipedInputStream pipedInputStream = new PipedInputStream();

	private final PipedOutputStream pipedOutputStream = new PipedOutputStream();

	private final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
			new byte[1024]);

	private final ByteArrayOutputStream byteArrayOutPutStream = new ByteArrayOutputStream();

	/**
	 * 以字符流读
	 * 
	 * @param
	 * @return
	 */
	public static String readWithCharStream(File f) {
		try {
			InputStream input = new FileInputStream(f);
			byte[] b = new byte[1024];
			int temp, count = 0;
			while ((temp = input.read(b)) != -1) {
				b[count++] = (byte) temp;
			}
			input.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 以字节流度
	 * 
	 * @param f
	 * @return
	 */
	public static String readWithByteStream(File f) {
		try {
			FileReader reader = new FileReader(f);
			char[] c = new char[10];
			int temp, count = 0;
			while ((temp = reader.read(c)) != -1) {
				c[count++] = (char) temp;
			}
			reader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public static void write(File f) {

	}
	
	
	public static String readeByteArrayStream(File f){
		return "";
	}
	
	public static void writeByteArrayStream(File f){
		
	}

}
