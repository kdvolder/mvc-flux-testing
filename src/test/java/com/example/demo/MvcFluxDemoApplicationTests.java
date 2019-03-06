package com.example.demo;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MvcFluxDemoApplicationTests {

	@Autowired
	MockMvc mvc;
	
	@Test
	public void serverSentEvents() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/stream-stuff"))
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.TEXT_EVENT_STREAM))
		.andReturn();
		
		String content = result.getResponse().getContentAsString();
		assertEquals(
				"id:0\n" + 
				"event:Cool\n" + 
				"data:Event 0\n" + 
				"\n" + 
				"id:1\n" + 
				"event:Cool\n" + 
				"data:Event 1\n" + 
				"\n" + 
				"id:2\n" + 
				"event:Cool\n" + 
				"data:Event 2\n" + 
				"\n" + 
				"id:3\n" + 
				"event:Cool\n" + 
				"data:Event 3\n" + 
				"\n" + 
				"id:4\n" + 
				"event:Cool\n" + 
				"data:Event 4"
			, 
				content
		);
	}

}
