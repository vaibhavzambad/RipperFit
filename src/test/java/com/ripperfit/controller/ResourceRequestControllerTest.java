package com.ripperfit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;
import com.ripperfit.model.Resource;
import com.ripperfit.model.ResourceRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class ResourceRequestControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	ResourceRequest resourceRequest;
	Resource resource;
	Organization organization;
	Department department;
	Designation designation;
	Employee employee;
	
	@Before
	public void setUp(){

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		resourceRequest = new ResourceRequest();
		resource = new Resource();
		organization = new Organization();
		department = new Department();
		designation = new Designation();
		employee = new Employee();
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestViewResourceRequestByEmployeeId() throws Exception{

		this.mockMvc.perform(get("/request/getRequestByEmployee/29").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetAllResourceRequestsByOrganization() throws Exception{

		this.mockMvc.perform(get("/request/getRequestsByOrganization/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetRequestByRequestId() throws Exception{

		this.mockMvc.perform(get("/request/getRequest/19").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetAllRequestToApprove() throws Exception{

		this.mockMvc.perform(get("/request/getAllRequestToApprove/10").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}
	
	
}
