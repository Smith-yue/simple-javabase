package org.simple.javabase.io;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;
import org.objenesis.strategy.StdInstantiatorStrategy;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Output;

public class SimpleIoSerializationTest {

	@Test
	public void testSer() {
		SimpleIoBean bean = new SimpleIoBean();
		bean.setUid(1);
		bean.setName("test");
		bean.setPassword("test");
		bean.setVersion(2);
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new FileOutputStream(new File("D:\\test.txt")));
			outputStream.writeObject(bean);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDser() {
		File file = new File("D:" + File.separator + "test.txt");
		boolean flag = file.exists();
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file));
			SimpleIoBean object = (SimpleIoBean) input.readObject();
			assertThat(object.getVersion()).isNotEqualTo(2);
			assertThat(object.getPassword()).isEqualTo(null);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSer2() {
		SimpleIoBean2 bean2 = new SimpleIoBean2(1, "test", "test");
		try {
			ObjectOutputStream outputStream = new ObjectOutputStream(
					new FileOutputStream(new File("D:\\test2.txt")));
			outputStream.writeObject(bean2);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testDser2() {
		File file = new File("D:" + File.separator + "test2.txt");
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file));
			SimpleIoBean2 object = (SimpleIoBean2) input.readObject();
			SimpleIoBean2.var = 2;
			System.out.println(object);
			assertThat(object.var).isEqualTo(2);
			System.out.println("static value:" + object.var);
			input.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSerTwice() {
		SimpleIoBean bean = new SimpleIoBean();
		bean.setName("test1");
		bean.setPassword("test1");
		bean.setUid(1);
		ObjectOutputStream outputStream;
		try {
			outputStream = new ObjectOutputStream(new FileOutputStream(
					new File("D:\\testSerTwice.txt")));
			outputStream.writeObject(bean);
			long length1 = new File("D:\\testSerTwice.txt").length();
			System.out.println("ser output length with jdk first :"+length1);
			bean.setName("test11");
			outputStream.writeObject(bean);
			long length2 = new File("D:\\testSerTwice.txt").length();
			System.out.println("ser output length with jdk second :"+length2);
			outputStream.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testDserTwice() {
		File file = new File("D:" + File.separator + "testSerTwice.txt");
		try {
			ObjectInputStream input = new ObjectInputStream(
					new FileInputStream(file));
			SimpleIoBean bean1 = (SimpleIoBean) input.readObject();
			SimpleIoBean bean2 = (SimpleIoBean) input.readObject();
			assertThat(bean1).isEqualTo(bean2);
			System.out.println(bean1);
			System.out.println(bean2);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testSerWithKryo() {
		File file = new File("D:" + File.separator + "testSerWihKryo.txt");
		SimpleIoBean bean = new SimpleIoBean();
		bean.setName("test1");
		bean.setPassword("test1");
		bean.setUid(1);

		Kryo kryo = new Kryo();
		kryo.setReferences(false);
		kryo.setRegistrationRequired(false);
		kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
		Output outPut;
		try {
			outPut = new Output(new FileOutputStream(file));
			kryo.writeClassAndObject(outPut, bean);
			outPut.flush();
			long lengthWithKryo = file.length();
			System.out.println("ser output length with Kryo:"+lengthWithKryo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
