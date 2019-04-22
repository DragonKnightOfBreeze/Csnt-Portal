package com.windea.demo.csntportal;

import com.windea.commons.base.generator.TextGenerator;
import org.junit.Test;

import java.util.List;

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

		//var builder = new StringBuilder();
		//builder.append("12345");
		//System.out.println(builder);
		//builder.delete(1, builder.length());
		//System.out.println(builder);
		//System.out.println(builder.length());

		//var text1 = TextGenerator.gen().add("123").repeat(3).text();
		//System.out.println(text1);
		//
		//var point = 1;
		//var text2 = TextGenerator.gen().add("123").where(point == 1).text();
		//System.out.println(text2);
		//
		//var text3 = TextGenerator.gen().add("123").repeat(point, p -> p < 3, p -> p + 1);
		//System.out.println(text3);
	}

	@Test
	public void test2() {
		var text1 = TextGenerator.gen().addStream(List.of("aaa", "bbb", "ccc").stream(), e -> e).text();
		System.out.println(text1);

		var text2 = TextGenerator.gen().joinStream(List.of("aaa", "bbb", "ccc").stream(), e -> e, "***").text();
		System.out.println(text2);
	}
}
