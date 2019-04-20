package com.windea.commons.base.utils;

import org.jetbrains.annotations.*;

import java.util.Arrays;

/**
 * 数组的工具类。
 */
public class ArrayUtils {
	private ArrayUtils() {}

	/**
	 * 判断数组是否为null、为空。
	 * <p>空值安全。
	 * @param array 指定的泛型数组
	 */
	@Contract(value = "null -> true", pure = true)
	public static <T> boolean isEmpty(@Nullable T[] array) {
		return array == null || array.length == 0;
	}

	/**
	 * 判断数组是否为null、小于等于指定长度。
	 * <p>空值安全。
	 * @param array 指定的泛型数组
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <T> boolean isLessE(@Nullable T[] array, int length) {
		return array == null || array.length <= length;
	}

	/**
	 * 判断数组是否大于等于指定长度。
	 * <p>空值安全。
	 * @param array 指定的泛型数组
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <T> boolean isGreaterE(@Nullable T[] array, int length) {
		return array != null && array.length <= length;
	}

	/**
	 * 将数组转化为字符串数组。
	 * <p>空值安全。
	 * @param array 指定的泛型数组
	 * @return 转化后的字符串数组
	 */
	@Contract("null -> null")
	@Nullable
	public static <T> String[] toStrArray(@Nullable T[] array) {
		if(array == null)
			return null;
		String[] result = new String[array.length];
		for(int i = 0; i < array.length; i++) {
			result[i] = array[i] != null ? array[i].toString() : "";
		}
		return result;
	}

	/**
	 * 将基本类型数组转化为包装对象数组。
	 * @param array 指定的基本类型数组
	 * @return 包装后的数组
	 */
	@NotNull
	public static Integer[] toBoxedArray(int[] array) {
		return (Integer[]) Arrays.stream(array).boxed().toArray();
	}

	/**
	 * 将基本类型数组转化为包装对象数组。
	 * @param array 指定的基本类型数组
	 * @return 包装后的数组
	 */
	@NotNull
	public static Long[] toBoxedArray(long[] array) {
		return (Long[]) Arrays.stream(array).boxed().toArray();
	}

	/**
	 * 将基本类型数组转化为包装对象数组。
	 * @param array 指定的基本类型数组
	 * @return 包装后的数组
	 */
	@NotNull
	public static Float[] toBoxedArray(double[] array) {
		return (Float[]) Arrays.stream(array).boxed().toArray();
	}
}

