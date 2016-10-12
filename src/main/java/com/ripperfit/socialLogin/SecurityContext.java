package com.ripperfit.socialLogin;

import com.ripperfit.model.Employee;


public class SecurityContext {

	private static final ThreadLocal<Employee> currentEmployee = new ThreadLocal<Employee>();

	public static Employee getCurrentUser() {
		Employee employee = currentEmployee.get();
		if (employee == null) {
			throw new IllegalStateException("No user is currently signed in");
		}
		return employee;
	}

	public static void setCurrentUser(Employee employee) {
		currentEmployee.set(employee);
	}

	public static boolean userSignedIn() {
		return currentEmployee.get() != null;
	}

	public static void remove() {
		currentEmployee.remove();
	}

}
