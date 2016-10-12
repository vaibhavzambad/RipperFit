package com.ripperfit.oauth2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.google.api.Google;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.servlet.view.RedirectView;

public class EmployeeInterceptor extends HandlerInterceptorAdapter {

	private final UsersConnectionRepository connectionRepository;

	private final EmployeeCookieGenerator employeeCookieGenerator = new EmployeeCookieGenerator();

	public EmployeeInterceptor(UsersConnectionRepository connectionRepository) {
		this.connectionRepository = connectionRepository;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		rememberUser(request, response);
		handleSignOut(request, response);			
		if (SecurityContext.employeeSignedIn() || requestForSignIn(request)) {
			System.out.println("pre handle employee signed in hello in pre handle");
			return true;
		} else {
			System.out.println("sign in require in pre handle");
			return requireSignIn(request, response);
		}
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		SecurityContext.remove();
	}

	// internal helpers

	private void rememberUser(HttpServletRequest request, HttpServletResponse response) {
		String employeeId = employeeCookieGenerator.readCookieValue(request);
		if (employeeId == null) {
			return;
		}
		if (!employeeNotFound(employeeId)) {
			employeeCookieGenerator.removeCookie(response);
			return;
		}
		SecurityContext.setCurrentEmployee(new Employee1(employeeId));
	}

	private void handleSignOut(HttpServletRequest request, HttpServletResponse response) {
		if (SecurityContext.employeeSignedIn() && request.getServletPath().startsWith("/signout")) {
			connectionRepository.createConnectionRepository(SecurityContext.getCurrentEmployee().getId()).removeConnections("google");
			employeeCookieGenerator.removeCookie(response);
			SecurityContext.remove();			
		}
	}

	private boolean requestForSignIn(HttpServletRequest request) {
		return request.getServletPath().startsWith("/signin");
	}

	private boolean requireSignIn(HttpServletRequest request, HttpServletResponse response) throws Exception {
		new RedirectView("/signin", true).render(null, request, response);
		return false;
	}

	private boolean employeeNotFound(String employeeId) {
		// doesn't bother checking a local user database: simply checks if the userId is connected to gmail
		return connectionRepository.createConnectionRepository(employeeId).findPrimaryConnection(Google.class) != null;
	}
}
