/*
 * Copyright (c) 2019.  DragonKnightOfBreeze Windea / 微风的龙骑士 风游迩
 * Email: dk_breeze@qq.com
 *
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */

package com.windea.commons.base.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 反射的工具类。
 */
public class ReflectionUtils {
	private ReflectionUtils() {}

	/**
	 * 创建一个对象。如果发生异常，则返回null。
	 * @param clazz 对象对应的Class类
	 * @param <T> 对象的类型
	 * @return 一个新的对象
	 */
	@Nullable
	public static <T> T create(@NotNull Class<T> clazz) {
		T obj = null;
		try {
			obj = clazz.getConstructor().newInstance();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 得到指定的字段。
	 */
	@Nullable
	public static <T> Field getField(@NotNull Class<T> clazz, @NotNull String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到指定字段的值。不能访问私有成员。
	 */
	@Nullable
	public static Object getValue(@NotNull Object obj, @NotNull Field field) {
		try {
			return field.get(obj);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 得到指定字段的值。不能访问私有成员。
	 */
	@Nullable
	public static Object getValue(@NotNull Object obj, @NotNull String fieldName) {
		try {
			return obj.getClass().getDeclaredField(fieldName).get(obj);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Nullable
	public static Object invoke(@NotNull Object obj, @NotNull Method method, Object... params) {
		try {
			return method.invoke(obj, params);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Nullable
	public static Object invoke(@NotNull Object obj, @NotNull String methodName, Object... params) {
		try {
			var typeArray = new Class[params.length];
			var method = obj.getClass().getMethod(methodName, typeArray);
			return method.invoke(obj, params);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}


	/**
	 * 得到指定类的属性映射。
	 */
	public static <T> Map<String, Object> getPropMap(@NotNull Object obj) {
		var methodList = Arrays.stream(obj.getClass().getMethods())
			.filter(method -> method.getName().startsWith("get"))
			.collect(Collectors.toList());
		var propMap = new HashMap<String, Object>();
		for(var prop : methodList) {
			var name = StringUtils.toCamelFormat(prop.getName().substring(3));
			var value = ReflectionUtils.invoke(obj, prop);
			propMap.put(name, value);
		}
		return propMap;
	}

	/**
	 * 判断某个字段的类型是否属于基础类型。
	 */
	public static boolean isBaseType(Field field) {
		try {
			return isBaseType(field.getType());
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 判断某个类型是否属于基础类型。
	 * 例如：Integer、Enum、String
	 */
	public static boolean isBaseType(Class<?> clazz) {
		var typeName = clazz.getTypeName();
		var result = StringUtils.startsWith(typeName, "java.lang")
			&& !StringUtils.endsWith(typeName, "Exception", "Error");
		if(!result) {
			result = StringUtils.equals(clazz.getSuperclass().getSimpleName(), "Enum");
		}
		return result;
	}
}
