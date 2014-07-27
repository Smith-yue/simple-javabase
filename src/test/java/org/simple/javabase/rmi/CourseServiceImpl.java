package org.simple.javabase.rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.assertj.core.util.Lists;

public class CourseServiceImpl extends UnicastRemoteObject implements CourseService {

	public CourseServiceImpl() throws RemoteException {
		super();
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3545557435775832169L;

	public List<Course> getCourseList() throws RemoteException{
		Course c1 = new Course();
		c1.setId(1L);
		c1.setName("course 1");
		Course c2 = new Course();
		c2.setId(2L);
		c2.setName("course 2");
		List<Course> courseList = Lists.newArrayList(c1, c2);
		return courseList;
	}

}
