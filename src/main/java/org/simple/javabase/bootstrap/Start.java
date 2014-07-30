package org.simple.javabase.bootstrap;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
/**
 * -XX:+TraceClassLoading
 * 控制台跟踪输出类加载信息
 * @author shiya
 *
 */
public class Start {

	public static void test(int i) {
		++i;
	}

	public static void main(String[] args) throws FileNotFoundException {
		int a = 5;
		test(a);
		System.out.println(a);
		RandomAccessFile raf = new RandomAccessFile(new File(""), "rw");
	}

}
