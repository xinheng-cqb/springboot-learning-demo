package com.springboot.web;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.learning.web.CityController;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MockServletContext.class)
public class CityControllerTest {
	private MockMvc mvc;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new CityController()).build();
	}

	// {\"id\":1,\"name\":\"HangZhou\",\"score\":88},{\"id\":2,\"name\":\"ShangHai\",\"score\":92}
	@Test
	public void allTest() throws Exception {
		MockHttpServletRequestBuilder requestBuilder = null;
		// 查询city列表，应该为空
		requestBuilder = MockMvcRequestBuilders.get("/city/");
		mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")));

		// post 提交一个city信息 其中andDo中的MockMvcResultHandlers.print()仅仅是为了通过控制台查看提交和响应的内容，可以不添加
		requestBuilder = MockMvcRequestBuilders.post("/city/").param("id", "1").param("name", "HangZhou").param("score", "88");
		mvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("SUCCESS")));

		// 再次查询city列表，应该有一个
		requestBuilder = MockMvcRequestBuilders.get("/city/");
		mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[{\"id\":1,\"name\":\"HangZhou\",\"score\":88}]")));

		// put 修改id为1的city
		requestBuilder = MockMvcRequestBuilders.put("/city/1").param("name", "BeiJing").param("score", "96");
		mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("SUCCESS")));

		// get 获取id为1的city
		requestBuilder = MockMvcRequestBuilders.get("/city/1");
		mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("{\"id\":1,\"name\":\"BeiJing\",\"score\":96}")));

		// delete 删除id为1的city
		requestBuilder = MockMvcRequestBuilders.delete("/city/1");
		mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("SUCCESS")));

		// 查询city列表，应该为空
		requestBuilder = MockMvcRequestBuilders.get("/city/");
		mvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("[]")));

	}
}
