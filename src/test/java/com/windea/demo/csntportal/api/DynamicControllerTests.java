package com.windea.demo.csntportal.api;

import com.alibaba.fastjson.JSONObject;
import com.windea.demo.csntportal.domain.entity.Dynamic;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.security.JwtUserDetailsServiceImpl;
import com.windea.demo.csntportal.service.DynamicService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//NOTE 最好注解@SprignBootTest和@AutoConfigureMockMvc，因为控制层还会向下依赖。
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class DynamicControllerTests {
	//使用mockMvc进行对于Mvc的单元测试
	@Autowired MockMvc mockMvc;
	@Autowired DynamicService dynamicService;
	@Autowired JwtUserDetailsServiceImpl jwtUserDetailsService;


	//TESTED 基本需求，添加当前用户
	//必须传入principal，并且对参数进行验证
	//通过@WithMockUser可以非常方便地模拟用户权限
	@WithMockUser("abc")
	@Test
	public void createTest1() throws Exception {
		//传递的仍然是json数据，不要使用requestAttr，官方库如果有字段为null则NPE
		//推荐使用FastJson，自动去除为null的字段
		var dynamic = new Dynamic("动态1", DynamicCategory.CHAT, "没有内容");
		var jsonStr = JSONObject.toJSONString(dynamic);

		mockMvc.perform(post("/dynamic/create").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isOk())
			.andDo(print());
	}

	//TESTED 参数验证错误
	@WithMockUser("abc")
	@Test
	public void createTest4() throws Exception {
		var dynamic = new Dynamic("", DynamicCategory.CHAT, "没有内容");
		var jsonStr = JSONObject.toJSONString(dynamic);

		mockMvc.perform(post("/dynamic/create").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isBadRequest())
			.andDo(print());
	}

	//TESTED notFound
	@WithMockUser("noob")
	@Test
	public void createTest2() throws Exception {
		var dynamic = new Dynamic("动态1", DynamicCategory.CHAT, "没有内容");
		var jsonStr = JSONObject.toJSONString(dynamic);

		mockMvc.perform(post("/dynamic/create").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isNotFound())
			.andDo(print());
	}

	//TESTED 明确注上@EnableGlobalMethodSecurity(prePostEnabled = true)
	@WithMockUser(roles = "ADMIN")
	@Test
	public void createTest3() throws Exception {
		var dynamic = new Dynamic("动态1", DynamicCategory.CHAT, "没有内容");
		var jsonStr = JSONObject.toJSONString(dynamic);

		mockMvc.perform(post("/dynamic/create").contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isForbidden())
			.andDo(print());
	}

	//TESTED 明确注上@EnableGlobalMethodSecurity(prePostEnabled = true)
	@WithMockUser(roles = "STUDENT")
	@Test
	public void deleteTest1() throws Exception {
		mockMvc.perform(delete("/dynamic/5"))
			.andExpect(status().isForbidden())
			.andDo(print());
	}

	//TESTED 明确注上@EnableGlobalMethodSecurity(prePostEnabled = true)
	@WithMockUser(roles = "ADMIN")
	@Test
	public void deleteTest2() throws Exception {
		mockMvc.perform(delete("/dynamic/4"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	//TESTED
	@Test
	public void getTest() throws Exception {
		//通过参数发送请求，例如：get(),post(),param(),params()
		mockMvc.perform(get("/dynamic/1"))
			//通过参数进行断言，例如：status(),content()
			.andExpect(status().isOk())
			//通过参数进行处理，例如：log(),print()
			.andDo(print());
	}

	//TESTED notFound 通过全局错误处理器，通过通过优先度更低的@ResponseStatus
	@Test
	public void getTest2() throws Exception {
		mockMvc.perform(get("/dynamic/300"))
			.andExpect(status().isNotFound())
			.andDo(print());
	}

	//TESTED
	@Test
	public void getSponsorUserTest() throws Exception {
		mockMvc.perform(get("/dynamic/1/sponsor-user"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	//TESTED
	@Test
	public void listTest() throws Exception {
		mockMvc.perform(get("/dynamic/list"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	//TESTED
	@Test
	public void searchBySubjectTest1() throws Exception {
		mockMvc.perform(get("/dynamic/search").param("method", "subject").param("subject", "主题"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	//TESTED noContent
	@Test
	public void searchByBySubjectTest2() throws Exception {
		mockMvc.perform(get("/dynamic/search").param("method", "subject").param("subject", "XXX"))
			.andExpect(status().isNoContent())
			.andDo(print());
	}

	//TESTED
	@Test
	public void searchByCategoryTest1() throws Exception {
		mockMvc.perform(get("/dynamic/search").param("method", "category").param("categorySet", "CHAT"))
			.andExpect(status().isOk())
			.andDo(print());
	}

	//TESTED
	@Test
	public void searchByCategoryTest2() throws Exception {
		mockMvc.perform(get("/dynamic/search").param("method", "category").param("categorySet", "NOTICE"))
			.andExpect(status().isNoContent())
			.andDo(print());
	}

	//TESTED 需要指定contentType为application/json，指定content为json字符串，可以和param同时使用
	@Test
	public void advanceSearchTest() throws Exception {
		var jsonStr = JSONObject.toJSONString(Map.of("subject", "主题", "category", "CHAT", "sponsorUsername", "123"));

		mockMvc.perform(post("/dynamic/advanceSearch").param("page", "1").param("size", "4")
			.contentType(MediaType.APPLICATION_JSON_UTF8).content(jsonStr))
			.andExpect(status().isOk())
			.andDo(print());
	}


	//TESTED 明确注上@EnableGlobalMethodSecurity(prePostEnabled = true)
	@WithMockUser("abc")
	@Test
	public void listUserTest() throws Exception {
		var auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println(auth);
		var role = new ArrayList<GrantedAuthority>(auth.getAuthorities()).get(0).getAuthority();
		System.out.println(role);

		mockMvc.perform(get("/user/list"))
			.andExpect(status().isForbidden())
			.andDo(print());
	}

	//TESTED unauthorized
	@Test
	public void getStudyColumnTest() throws Exception {
		mockMvc.perform(get("/study-column/1"))
			.andExpect(status().isUnauthorized())
			.andDo(print());
	}
}
