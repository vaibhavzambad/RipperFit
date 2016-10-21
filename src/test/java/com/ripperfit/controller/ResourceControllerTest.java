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
import com.ripperfit.model.Organization;
import com.ripperfit.model.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class ResourceControllerTest {
	
	private MockMvc mockMvc;

	@Autowired
	MockHttpSession session;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	Resource resource;
	Organization organization;
	
	@Before
	public void setUp(){

		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
		resource = new Resource();
		organization = new Organization();
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestCaseAddResource() throws Exception{

		organization.setOrganizationId(2);
		organization.setOrganizationName("Google");
		
		resource.setResourceId(1);
		resource.setResourceName("mouse");
		resource.setFinalApprovalLevel(2);
		resource.setOrganization(organization);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String resourceJson=ow.writeValueAsString(resource);

		this.mockMvc.perform(post("/resource/addResource").
				contentType(MediaType.APPLICATION_JSON).content(resourceJson))
				.andExpect(status().isCreated());
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestGetAllResources() throws Exception{

		this.mockMvc.perform(get("/resource/getAllResources"))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestGetResourcesByOrganizationId() throws Exception{

		this.mockMvc.perform(get("/resource/getResourcesByOrganizationId/1"))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestGetResourcesById() throws Exception{

		this.mockMvc.perform(get("/resource/getResourceById/1"))
		.andExpect(status().isOk());
		
	}
	
	@Test
	@Rollback(true)
	@Transactional
	public void positiveTestUpdateResource() throws Exception{
		
		organization.setOrganizationId(2);
		organization.setOrganizationName("Google");
		
		resource.setResourceId(1);
		resource.setResourceName("mouse");
		resource.setFinalApprovalLevel(2);
		resource.setOrganization(organization);

		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
		String resourceJson=ow.writeValueAsString(resource);
		
		this.mockMvc.perform(put("/resource/updateResource").contentType(MediaType.APPLICATION_JSON)
				.content(resourceJson))
		.andExpect(status().isOk());
		
		
		
	}
	
	
}