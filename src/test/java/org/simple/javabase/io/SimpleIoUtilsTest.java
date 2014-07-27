package org.simple.javabase.io;

import java.io.File;

import org.junit.Test;

public class SimpleIoUtilsTest {

	private final String path = "D://";

	public void testRead() {
		File testFile = new File(path);
		SimpleIoUtils.readWithCharStream(testFile);
	}

	public void testWrite() {
		File testFile = new File(path);
		SimpleIoUtils.write(testFile);
	}

	@Test
	public void testFileVariable() {
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);
	}

}
