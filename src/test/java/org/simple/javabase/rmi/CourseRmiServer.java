package org.simple.javabase.rmi;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class CourseRmiServer {

	public static void main(String[] args) {

		try {
			CourseService service = new CourseServiceImpl();
			LocateRegistry.createRegistry(6666);
			Naming.rebind("rmi://localhost:6666/courseService", service);
			System.out.println("rmi Server start...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
