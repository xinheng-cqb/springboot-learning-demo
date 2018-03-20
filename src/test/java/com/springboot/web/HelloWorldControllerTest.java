package com.springboot.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.learning.web.HelloWorldController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class HelloWorldControllerTest {

	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new HelloWorldController()).build();
	}

	/**
	 * @introduce:对应HelloWorldController中的hello()方法
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void hello() throws Exception {
		String name = "test";
		mvc.perform(MockMvcRequestBuilders.get("/hello?name=" + name).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("hello1 world , " + name)));

	}

	/**
	 * @introduce:对应HelloWorldController中的helloTwo()方法
	 * @throws Exception
	 * @return void
	 */
	@Test
	public void helloTwo() throws Exception {
		String name = "test";
		mvc.perform(MockMvcRequestBuilders.get("/hello/" + name).accept(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.status().is(200))
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("hello2 world , " + name)));
	}
}
