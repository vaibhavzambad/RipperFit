package com.ripperfit.socialLogin;

import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.NativeWebRequest;

import com.ripperfit.model.Employee;


@Service
public class SimpleSignInAdapter implements SignInAdapter {


	private final UserCookieGenerator userCookieGenerator = new UserCookieGenerator();

	public String signIn(String employeeEmail, Connection<?> connection, NativeWebRequest request) {
		SecurityContext.setCurrentUser(new Employee());
		userCookieGenerator.addCookie(employeeEmail, request.getNativeResponse(HttpServletResponse.class));
		return null;
	}
}
