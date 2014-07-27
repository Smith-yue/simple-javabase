package org.simple.javabase.io;

import java.io.Serializable;

public class SimpleIoBean extends SimpleIoBaseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7655931831049442331L;

	private long uid;

	private String name;

	private transient String password;

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

	@Override
	public String toString() {
		return "name:" + this.name + " uid:" + this.uid + " password:"
				+ this.password + " version:" + this.getVersion();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
