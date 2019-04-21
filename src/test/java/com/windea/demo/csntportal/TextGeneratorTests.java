package com.windea.demo.csntportal;

import com.windea.commons.base.generator.TextGenerator;
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


		var text = TextGenerator.gen().add("12345").delete(2, -1);
		System.out.println(text);

	}
}
