package com.windea.demo.csntportal;

import org.junit.Test;

public class TextGeneratorTests {

	@Test
	public void test1() {
		//var point = "1";
		//var raw = "aaa";
		//var str1 = TextGenerator.gen().addWhere("123", 3);
		//System.out.println(str1);
		//
		//var str2 = TextGenerator.gen().addWhere(() -> raw.repeat(3), 3);
		//System.out.println(str2);
		//
		//var str3 = TextGenerator.gen().addWhere("123", point,
		//	p -> p.length() <= 3,
		//	p -> p += "1"
		//);
		//System.out.println(str3);
		//System.out.println(point);


		//var text = TextGenerator.gen().add("12345").delete(2, -1);
		//System.out.println(text);

		//var lines = "123\n1234\n12345\n";
		//var lines2 = lines.lines().reduce((a, b) -> a + b);
		//System.out.println(lines2);

		var builder = new StringBuilder();
		builder.append("12345");
		System.out.println(builder);
		builder.delete(1, builder.length());
		System.out.println(builder);
		System.out.println(builder.length());
	}
}
