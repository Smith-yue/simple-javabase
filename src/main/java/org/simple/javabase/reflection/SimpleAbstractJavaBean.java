package org.simple.javabase.reflection;

import java.util.List;

public abstract class SimpleAbstractJavaBean {
	
//	public SimpleAbstractJavaBean instance=new SimpleJavaBean();
	
	
    public List<String> beanNames;
    
    
    public String str="world";
    
    
    private void hello(){
    	System.out.println("hello");
    }
    
    public  abstract void hello(String arg);
    
}
