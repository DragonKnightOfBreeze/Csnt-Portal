package com.windea.demo.csntportal;

import com.windea.commons.base.template.TBean;
import com.windea.commons.base.utils.ReflectionUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class CommonsTests {
	//TESTED stream中允许null的存在
	@Test
	public void test1() {
		var list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add(null);
		list.add("c");
		var result = list.stream().toArray();
		System.out.println(Arrays.toString(result));
	}

	//TESTED getTypeName()返回的是全路径的类名
	@Test
	public void test2() {
		var str = "132";
		System.out.println(str.getClass().getTypeName());
		System.out.println(str.getClass().getPackageName());
		System.out.println(str.getClass().getSimpleName());
		System.out.println(str.getClass().getSuperclass().getTypeName());
	}

	@Test
	public void test3() throws Exception {
		System.out.println(ReflectionUtils.isBaseType("123".getClass()));
		System.out.println(ReflectionUtils.isBaseType(Integer.valueOf(1).getClass()));
		System.out.println(ReflectionUtils.isBaseType(MyEnum.A.getClass()));


		var cls = new MyClass();
		cls.a = "123";
		cls.b = "456";

		System.out.println(cls.a.getClass());
		var field = cls.getClass().getDeclaredField("a");
		var value = field.get(cls);
		var value2 = ReflectionUtils.getValue(cls, field);
		var valueType = field.getType();
		var castedValue = valueType.cast(value);
		System.out.println("********" + ReflectionUtils.isBaseType(field));
		System.out.println(value instanceof String);
		System.out.println(value);
		//System.out.println(field.get(""));
		//var field = cls.getClass().getDeclaredFields()[0];
		//var value = ReflectionUtils.getValue(cls,"a");
		//System.out.println(field.getName());
		//System.out.println(value);
		System.out.println(cls.toString());
	}

	enum MyEnum {A, B, C}

	@Test
	public void test4() {
		var list = new ArrayList<>();
		list.add("a");
		list.add("b");
		list.add(null);
		list.add("c");
		var map = list.stream().collect(Collectors.toMap(e -> e + "123", e -> e));
		System.out.println(map);
	}

	public class MyClass extends TBean<Integer> {
		private static final long serialVersionUID = -1892848159505006501L;

		public String getA() {
			return a;
		}

		public void setA(String a) {
			this.a = a;
		}

		public String getB() {
			return b;
		}

		public void setB(String b) {
			this.b = b;
		}

		private String a;
		private String b;

		@Override
		public Integer getId() {
			return 12345;
		}

		@Override
		public String toString() {
			return super.toString();
		}
	}
}
