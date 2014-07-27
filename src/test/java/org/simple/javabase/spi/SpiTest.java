package org.simple.javabase.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

import org.junit.Test;

public class SpiTest {
	
	
	@Test
	public void testSpi(){
		
		ServiceLoader<SimpleInterface> simpleService=ServiceLoader.load(SimpleInterface.class);
		
		Iterator<SimpleInterface> list=simpleService.iterator();
		
		while(list.hasNext()){
			SimpleInterface s=list.next();	
			s.hello();
		}
		
	}

}
