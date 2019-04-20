package com.windea.demo.csntportal;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DomainTests {

	private static final String prefix = "*****************";

	private void print(String arg) {
		System.out.println(prefix);
		System.out.println(arg);
		System.out.println(prefix);
	}

}
