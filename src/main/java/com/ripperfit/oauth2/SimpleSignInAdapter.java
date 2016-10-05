package com.ripperfit.oauth2;

import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.context.request.NativeWebRequest;

public class SimpleSignInAdapter implements SignInAdapter {
	
private final EmployeeCookieGenerator employeeCookieGenerator = new EmployeeCookieGenerator();
	
	public String signIn(String employeeId, Connection<?> connection, NativeWebRequest request) {
		SecurityContext.setCurrentEmployee(new Employee1(employeeId));
		employeeCookieGenerator.addCookie(employeeId, request.getNativeResponse(HttpServletResponse.class));
		return null;
	}

}
