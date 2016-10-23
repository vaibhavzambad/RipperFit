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
import com.ripperfit.model.Login;
import com.ripperfit.model.Organization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class UserControllerTest {

	private MockMvc mockMvc;

	@Autowired
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;

	Employee employee;
	Organization organization;
	Department department;
	Designation designation;
	Login login;

	@Before
	public void setUp(){

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		employee = new Employee();
		organization = new Organization();
		department = new Department();
		designation = new Designation();
		login = new Login();
	}

	// Positive Test Case

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestGetEmployeeByEmail() throws Exception{

		this.mockMvc.perform(get("/employee/getEmployeeByEmail?email=vaibhav.zambad@metacube.com").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void negativeTestCaseGetEmployeeByEmail() throws Exception{

		this.mockMvc.perform(get("/employee/getEmployeeByEmail?email=sdfv.zambad@metacube.com").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetEmployeeById() throws Exception{

		this.mockMvc.perform(get("/employee/getEmployeeById?Id=10").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}


	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseLogin() throws Exception{

		login.setEmail("vaibhav.zambad@metacube.com");
		login.setPassword("123456");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String loginJson=ow.writeValueAsString(login);

		this.mockMvc.perform(post("/employee/login").
				contentType(MediaType.APPLICATION_JSON).content(loginJson))
				.andExpect(status().isAccepted());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void negativeTestCaseLogin() throws Exception{

		login.setEmail("vaibhav.zambad@metacube.com");
		login.setPassword("vaibhav123");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String loginJson=ow.writeValueAsString(login);

		this.mockMvc.perform(post("/employee/login").
				contentType(MediaType.APPLICATION_JSON).content(loginJson))
				.andExpect(status().isUnauthorized());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseAddEmployee() throws Exception{

		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Technical");
		department.setOrganization(organization);

		designation.setDesignationId(1);
		designation.setDesignationLevel(3);
		designation.setDesignationName("employee");

		employee.setEmployeeId(1);
		employee.setFirstName("vaibhav");
		employee.setLastName("zambad");
		employee.setGender("male");
		employee.setContactNumber("9874512365");
		employee.setEmail("vaibhav@gmail.com");
		employee.setPassword("123456");
		employee.setDesignation(designation);
		employee.setOrganization(organization);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String employeeJson=ow.writeValueAsString(employee);

		this.mockMvc.perform(post("/employee/addEmployee").
				contentType(MediaType.APPLICATION_JSON).content(employeeJson))
				.andExpect(status().isCreated());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void negativeTestCaseAddEmployee() throws Exception{

		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Technical");
		department.setOrganization(organization);

		designation.setDesignationId(1);
		designation.setDesignationLevel(3);
		designation.setDesignationName("employee");

		employee.setEmployeeId(1);
		employee.setFirstName("vaibhav");
		employee.setLastName("zambad");
		employee.setGender("male");
		employee.setContactNumber("9874512365");
		employee.setEmail("vaibhav@gmail.com");
		employee.setPassword("123456");
		employee.setDesignation(designation);
		employee.setOrganization(organization);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String employeeJson=ow.writeValueAsString(employee);

		this.mockMvc.perform(post("/employee/addEmployee").
				contentType(MediaType.APPLICATION_JSON).content(employeeJson))
				.andExpect(status().isCreated());

		this.mockMvc.perform(post("/employee/addEmployee").
				contentType(MediaType.APPLICATION_JSON).content(employeeJson))
				.andExpect(status().isConflict());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseUpdateEmployee() throws Exception{

		organization.setOrganizationId(1);
		organization.setOrganizationName("Metacube");

		department.setDepartmentId(1);
		department.setDepartmentName("Technical");
		department.setOrganization(organization);

		designation.setDesignationId(1);
		designation.setDesignationLevel(3);
		designation.setDesignationName("employee");

		employee.setEmployeeId(1);
		employee.setFirstName("vaibhav");
		employee.setLastName("zambad");
		employee.setGender("male");
		employee.setContactNumber("9874512365");
		employee.setEmail("vaibhav@gmail.com");
		employee.setPassword("123456");
		employee.setDesignation(designation);
		employee.setOrganization(organization);


		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String employeeJson=ow.writeValueAsString(employee);

		this.mockMvc.perform(post("/employee/addEmployee").
				contentType(MediaType.APPLICATION_JSON).content(employeeJson))
				.andExpect(status().isCreated());

		employee.setLastName("jain");
		
		ObjectMapper mapper1 = new ObjectMapper();
		mapper1.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow1 = mapper.writer().withDefaultPrettyPrinter();
		String updatedEmployeeJson=ow1.writeValueAsString(employee);

		this.mockMvc.perform(put("/employee/updateEmployee").
				contentType(MediaType.APPLICATION_JSON).content(updatedEmployeeJson))
				.andExpect(status().isOk());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetAllEmployeesByOrganization() throws Exception{

		this.mockMvc.perform(get("/employee/getEmployeesByOrganizationId/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCasegetCurrentEmployeeObject() throws Exception{

		session.setAttribute("email", "vaibhav.zambad@metacube.com");
		this.mockMvc.perform(get("/employee/getCurrentEmployeeObject").accept(MediaType.APPLICATION_JSON)
				.session(session))
				.andExpect(status().isOk());
	}

	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseChangePassword() throws Exception{

		session.setAttribute("email", "vaibhav.zambad@metacube.com");
		String oldPassword = "123456";
		String newPassword = "vaibhav1234";
		this.mockMvc.perform(post("/employee/changePassword?oldPassword="+oldPassword+
				"&newPassword="+newPassword).accept(MediaType.APPLICATION_JSON)
				.session(session))
				.andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseViewAllRequestsForApprove() throws Exception{
		
		session.setAttribute("email","vaibhav.zambad@metacube.com");
		
		this.mockMvc.perform(get("/employee/getEmployeeApprove").
				contentType(MediaType.APPLICATION_JSON).session(session))
				.andExpect(status().isNoContent());
	}

}
