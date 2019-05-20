package com.windea.demo.csntportal;

import com.windea.demo.csntportal.domain.vo.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.service.DynamicService;
import com.windea.demo.csntportal.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTests {

	@Autowired private DynamicService dynamicService;
	@Autowired private UserService userService;

	@Test
	public void test1() {
		var vo = new DynamicSearchVo();
		vo.setSubject("主题123");
		vo.setCategorySet(Set.of(DynamicCategory.CHAT));
		vo.setSponsorUsername("abc");
		var pageable = PageRequest.of(0, 10);
		var rs = dynamicService.advanceSearch(vo, pageable).getContent();
		var user = rs.get(0).getSponsorUser();
		System.out.println(rs);
		System.out.println(user);
	}

	@Test
	public void test2() {
		var pageable = PageRequest.of(0, 10);
		var rs = userService.list(pageable);
		System.out.println(rs);
	}
}
