package com.windea.demo.csntportal;

import com.windea.demo.csntportal.repository.DynamicRepository;
import com.windea.demo.csntportal.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RepositoryTests {
	@Autowired private DynamicRepository dynamicRepository;
	@Autowired private UserRepository userRepository;

	private static final String prefix = "*****************";

	private void print(String arg) {
		System.out.println(prefix);
		System.out.println(arg);
		System.out.println(prefix);
	}

	//TESTED 分页从0开始
	@Test
	public void test1() {
		var result = dynamicRepository.findAllBySponsorUsername("abc", PageRequest.of(0, 10));
		System.out.println(prefix + result.getContent());
	}


	//TESTED
	@Test
	public void test3() {
		var result = dynamicRepository.findAll();
		System.out.println(prefix + result);
	}

	//TESTED
	@Test
	public void test4() {
		var result = dynamicRepository.findById(1);
		System.out.println(prefix + result.orElse(null));
	}

	@Test
	public void test5() {
		var result = userRepository.getDynamicListById(1).getDynamicList();
		System.out.println(result);
	}
}
