package org.simple.javabase.reflection;

public interface SimpleJavaInterface {

	String getSimpleName();
	
	abstract String getNum();

	public String name = "";

	static int num = 1;

	final SimpleJavaBean bean = new SimpleJavaBean();
	

}
