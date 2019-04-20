package com.windea.demo.csntportal;

import com.windea.demo.csntportal.domain.request.DynamicSearchVo;
import com.windea.demo.csntportal.enums.DynamicCategory;
import com.windea.demo.csntportal.service.DynamicService;
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

	@Test
	public void test1() {
		var vo = new DynamicSearchVo();
		vo.setSubject("主题123");
		vo.setCategorySet(Set.of(DynamicCategory.CHAT));
		vo.setSponsorUsername("abc");
		var pageable = PageRequest.of(0, 10);
		var rs = dynamicService.findAllByConditions(vo, pageable).getContent();
		System.out.println(rs);
	}
}
