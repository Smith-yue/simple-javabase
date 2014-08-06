package org.simple.javabase.collection;

import java.util.HashMap;

public class SimpleMap {

	private HashMap hashMap;

	public HashMap getHashMap() {
		return hashMap;
	}

	public void setHashMap(HashMap hashMap) {
		this.hashMap = hashMap;
	}

	public static void main(String[] args) {
		SimpleMap map = new SimpleMap();

		HashMap param = new HashMap();

		param.put("same", 3);
		
		param.put(null, 2);

		param.put(null, 1);
		
		System.out.println("HashMap with same key:null,size is :"
				+ param.size());

		System.out
				.println("HashMap with same key:null ,get value is the last put value:"
						+ param.get(null));
		
	}

}
