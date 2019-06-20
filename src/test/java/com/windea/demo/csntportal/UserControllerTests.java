package com.windea.demo.csntportal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTests {
	@Autowired PasswordEncoder passwordEncoder;

	@Test
	public void testEncode() {
		//存储到数据库中的应该是编码后的密码
		var psw = passwordEncoder.encode("123123");
		System.out.println(psw);
		System.out.println(passwordEncoder.matches("123123", psw));
	}
}
