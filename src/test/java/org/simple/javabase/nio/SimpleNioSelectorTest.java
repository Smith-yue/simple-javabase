package org.simple.javabase.nio;

import java.io.IOException;
import java.nio.channels.Selector;

import org.junit.Test;

public class SimpleNioSelectorTest {
	
	@Test
	public void testSelector(){
		try {
			Selector selector=Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
