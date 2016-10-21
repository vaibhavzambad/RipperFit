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
import com.ripperfit.model.Employee;
import com.ripperfit.model.Organization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class DepartmentControllerTest {

	private MockMvc mockMvc;

	@Autowired
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;

	Employee employee;
	Organization organization;
	Department department;
	Designation designation;

	@Before
	public void setUp(){
		
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		employee = new Employee();
		organization = new Organization();
		department = new Department();
		designation = new Designation();
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestGetAllDepartments() throws Exception{

		this.mockMvc.perform(get("/departments/getDepartments").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());

	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseAddDepartment() throws Exception{
		
		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Accounts");
		department.setOrganization(organization);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String departmentJson=ow.writeValueAsString(department);
	    
	    this.mockMvc.perform(post("/departments/addDepartment").
				contentType(MediaType.APPLICATION_JSON).content(departmentJson))
		.andExpect(status().isCreated());
	    
	    
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void negativeTestCaseAddDepartment() throws Exception{
		
		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("HR");
		department.setOrganization(organization);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String departmentJson=ow.writeValueAsString(department);
	    
	    this.mockMvc.perform(post("/departments/addDepartment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(departmentJson))
		.andExpect(status().isConflict());
	    
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetDepartmentById() throws Exception{
		
		this.mockMvc.perform(get("/departments/getDepartmentById/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}	
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseUpdateDepartment() throws Exception{
		
		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Tech");
		department.setOrganization(organization);
		
		ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
	    String departmentJson=ow.writeValueAsString(department);
	   
	    this.mockMvc.perform(post("/departments/addDepartment").
				contentType(MediaType.APPLICATION_JSON).content(departmentJson))
		.andExpect(status().isCreated()); 
	    
	    department.setDepartmentName("Techno");
	    
	    ObjectMapper mapper1 = new ObjectMapper();
	    mapper1.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
	    ObjectWriter ow1 = mapper.writer().withDefaultPrettyPrinter();
	    String departmentJson1=ow1.writeValueAsString(department);
	    
		this.mockMvc.perform(put("/departments/updateDepartment")
				.contentType(MediaType.APPLICATION_JSON).content(departmentJson1))
			.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCasegetAllDepartmentsInAnOrganization() throws Exception{
		
		this.mockMvc.perform(get("/departments/getAllDepartmentsInAnOrganization/1")
				.accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}
	
	
}
