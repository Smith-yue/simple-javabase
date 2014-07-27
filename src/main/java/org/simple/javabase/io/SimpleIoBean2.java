package org.simple.javabase.io;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class SimpleIoBean2 implements Externalizable {

	private long uid;

	private String name;

	private String password;
	
	public static int var=1;

	/**
	 * requried for deser if exist other constructors
	 */
	public SimpleIoBean2() {

	}

	public SimpleIoBean2(long uid, String name, String password) {
		super();
		this.uid = uid;
		this.name = name;
		this.password = password;
	}

	/**
	 * 序列化和反序列化保持一致顺序
	 */
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeLong(uid);
		out.writeObject(name);
		out.writeObject(password);
		out.writeInt(var);
	}

	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		this.uid = in.readLong();
		this.name = (String) in.readObject();
		this.password = (String) in.readObject();
		this.var=in.readInt();
	}

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "name:" + this.name + " uid:" + this.uid + " password:"
				+ this.password;
	}

}
