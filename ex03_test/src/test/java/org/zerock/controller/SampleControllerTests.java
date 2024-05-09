package org.zerock.controller;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.Ticket;

import com.google.gson.Gson;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)

@WebAppConfiguration

@ContextConfiguration({
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
})
@Log4j
public class SampleControllerTests {
	
	@Setter(onMethod_=@Autowired)
	private WebApplicationContext ctx;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		
		//this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
		
		DefaultMockMvcBuilder a = MockMvcBuilders.webAppContextSetup(ctx);
		
		this.mockMvc = a.build();
		
	}
	
	@Test
	public void testTest() {
		log.info("test.....");
		log.info(ctx);
		log.info(mockMvc);
	}
	
	@Test
	public void testConvert() throws Exception{
		Ticket ticket = new Ticket();
		ticket.setTno(123);
		ticket.setOwner("Admin");
		ticket.setGrade("AAA");
		
		String jsonStr = new Gson().toJson(ticket);
		
		log.info(jsonStr);
		
		mockMvc.perform(post("/sample/ticket")
				.contentType(MediaType.APPLICATION_JSON)
				.content(jsonStr))
		.andExpect(status().is(200));
	}

	@Test
	public void testRegister() throws Exception{
		
//		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/register")
//				.param("title", "테스트 새글 제목")
//				.param("content", "테스트 새글 내용")
//				.param("writer", "user00")
//			).andReturn().getModelAndView().getViewName();
//		
//		
//		log.info(resultPage);
		
		MockHttpServletRequestBuilder a = MockMvcRequestBuilders.post("/board/register");
		a.param("title", "테스트 새글 제목");
		a.param("content", "테스트 새글 내용");
		a.param("writer", "user00");
		
		ResultActions b = mockMvc.perform(a);
		
		MvcResult c = b.andReturn();
		
		ModelAndView d = c.getModelAndView();
		
		String e = d.getViewName();
		
		log.info(e);
		
//		
//		MvcResult b = a.andReturn();
//		
//		ModelAndView c = b.getModelAndView();
//		
//		ModelMap d = c.getModelMap();
//		
//		log.info(d);
	}
	

	

}
