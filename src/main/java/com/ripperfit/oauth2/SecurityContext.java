package com.ripperfit.oauth2;

public class SecurityContext {

	private static final ThreadLocal<Employee1> currentEmployee = new ThreadLocal<Employee1>();

	public static Employee1 getCurrentEmployee() {
		Employee1 employee = currentEmployee.get();
		if (employee == null) {
			
			throw new IllegalStateException("No user is currently signed in");
		}
		return employee;
	}

	public static void setCurrentEmployee(Employee1 employee) {
		currentEmployee.set(employee);
	}

	public static boolean employeeSignedIn() {
		return currentEmployee.get() != null;
	}

	public static void remove() {
		currentEmployee.remove();
	}
}