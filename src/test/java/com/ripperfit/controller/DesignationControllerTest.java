package com.ripperfit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ripperfit.model.Department;
import com.ripperfit.model.Designation;
import com.ripperfit.model.Organization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class DesignationControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	Organization organization;
	Department department;
	Designation designation;
	

	@Before
	public void setUp(){
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		organization = new Organization();
		department = new Department();
		designation = new Designation();
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseAddDesignation() throws Exception{
		
		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Accounts");
		department.setOrganization(organization);
		
		designation.setDesignationId(1);
		designation.setDesignationName("abc");
		designation.setDesignationLevel(2);
		designation.setDepartment(department);
		designation.setOrganization(organization);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String designationJson=ow.writeValueAsString(designation);
	    
	    this.mockMvc.perform(post("/designation/addDesignation").
				contentType(MediaType.APPLICATION_JSON).content(designationJson))
		.andExpect(status().isCreated());
	}
	
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseUpdateLevels() throws Exception{
		
		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Accounts");
		department.setOrganization(organization);
		
		designation.setDesignationId(1);
		designation.setDesignationName("abc");
		designation.setDesignationLevel(4);
		designation.setDepartment(department);
		designation.setOrganization(organization);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String designationJson=ow.writeValueAsString(designation);
	    
	    this.mockMvc.perform(post("/designation/addDesignation").
				contentType(MediaType.APPLICATION_JSON).content(designationJson))
		.andExpect(status().isCreated());
	    
	    designation.setDesignationLevel(3);
	    
	    ObjectMapper mapper1 = new ObjectMapper();
	    mapper1.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow1 = mapper.writer().withDefaultPrettyPrinter();
	    String designationJson1=ow1.writeValueAsString(designation);
	    
		this.mockMvc.perform(put("/designation/updateLevels")
				.contentType(MediaType.APPLICATION_JSON).content(designationJson1))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetDesignations() throws Exception{
		
		this.mockMvc.perform(get("/designation/getDesignations/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void negativeTestCaseGetDesignations() throws Exception{
		
		this.mockMvc.perform(get("/designation/getDesignations/0")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetDesignationsByDepartment() throws Exception{
		
		this.mockMvc.perform(get("/designation/getDesignationsByDepartment/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void negativeTestCaseGetDesignationsByDepartment() throws Exception{
		
		this.mockMvc.perform(get("/designation/getDesignationsByDepartment/0")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
		
	}
}