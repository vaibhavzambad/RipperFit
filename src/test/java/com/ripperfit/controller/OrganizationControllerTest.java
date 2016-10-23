package com.ripperfit.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.ripperfit.model.Organization;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class OrganizationControllerTest {

	private MockMvc mockMvc;

	@Autowired
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	Organization organization;

	@Before
	public void setUp(){

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		organization = new Organization();
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestgetAllOrganizations() throws Exception{

		this.mockMvc.perform(get("/organization/getAllOrganizations"))
		.andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseAddOrganization() throws Exception{

		organization.setOrganizationId(2);
		organization.setOrganizationName("Google");
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String organizationJson=ow.writeValueAsString(organization);

		this.mockMvc.perform(post("/organization/addOrganization").
				contentType(MediaType.APPLICATION_JSON).content(organizationJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetOrganizationById() throws Exception{

		int organizationId = 1;

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String organizationIdJson=ow.writeValueAsString(organizationId);

		this.mockMvc.perform(get("/organization/getOrganizationById").
				contentType(MediaType.APPLICATION_JSON).content(organizationIdJson))
				.andExpect(status().isOk());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseGetOrganizationByName() throws Exception{

		String organizationName = "Metacube";

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String organizationNameJson=ow.writeValueAsString(organizationName);

		this.mockMvc.perform(get("/organization/getOrganizationByName").
				contentType(MediaType.APPLICATION_JSON).content(organizationNameJson))
				.andExpect(status().isNoContent());
	}
	
}