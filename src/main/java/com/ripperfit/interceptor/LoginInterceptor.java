package com.ripperfit.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

@WebFilter({"/dashboard.html"})
public class LoginInterceptor extends GenericFilterBean {


	/*private UserService userService;

	*//**
	 * method to get UserServiceObject
	 * @return : UserService object
	 *//*
	public UserService getUserService() {
		return userService;
	}

	*//**
	 * method to set UserService object
	 * @param UserService
	 *//*
	@Autowired(required = true)
	public void setUserService(UserService userService) {
		this.userService = userService;
	}*/

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain filterChain) throws IOException, ServletException {

		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final HttpServletResponse httpResponse = (HttpServletResponse) response;

		HttpSession session = httpRequest.getSession(false);
		try {
			
			String email = (String)session.getAttribute("email");
			if (session == null || email == null) {
				httpResponse.sendRedirect("signIn.html");
			}/*else{

				System.out.println("uswsjd: "+userService);
				Employee employee = userService.getEmployeeByEmail(email);
				if(employee != null){
					if(employee.getDesignation().getDesignationName().toUpperCase() == "ADMIN"){
						httpResponse.sendRedirect("admin");
					}else if(employee.getDesignation().getDesignationName().toUpperCase() == "HELPDESK"){
						httpResponse.sendRedirect("helpdesk");
					}else{
						httpResponse.sendRedirect("employee");
					}
				}else{
					httpResponse.sendRedirect("login");
				}
			}*/
			filterChain.doFilter(request, response);
		} catch(NullPointerException npe) {
			httpResponse.sendRedirect("signIn.html");
		}
	}
}