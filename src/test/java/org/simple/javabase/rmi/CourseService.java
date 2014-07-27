package org.simple.javabase.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface CourseService extends Remote{
	
	List<Course> getCourseList() throws RemoteException;

}
