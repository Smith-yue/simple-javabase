package org.simple.javabase.reflection;

import java.io.Serializable;

public class SimpleJavaBean extends SimpleAbstractJavaBean implements SimpleJavaInterface, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5619707391185826255L;
	
	public SimpleJavaBean() {
		System.out.println("invoke default constructor method...");
	}
	
	public SimpleJavaBean(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	private long id;
	
	
	private String name;


	public long getId() {
		return id;
	}


	public void setId(long id) {
		System.out.println("SimpleJavaBean setId() method invoked...");
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void hello(String arg) {
		System.out.println(this.name);
	}
	
	private void test(){
		
	}

	public  String getSimpleName() {
		return "simpleJavaBean";
	}

	public String getNum() {
		return "one";
	}
}
