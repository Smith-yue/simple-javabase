package org.simple.javabase.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

public class CourseRmiClient {

	public static void main(String[] args) {
		try {
			CourseService courseService = (CourseService) Naming
					.lookup("rmi://localhost:6666/courseService");
			List<Course> result = courseService.getCourseList();
			System.out.println("rmi client request result size:"
					+ result.size());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
	}

}
