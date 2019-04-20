package com.windea.commons.base.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * 集合的工具类。
 */
public class CollectionUtils {
	private CollectionUtils() {}

	/**
	 * 判断集合是否为null、为空。
	 * <p>空值安全。
	 * @param collection 指定的泛型集合
	 */
	@Contract(value = "null -> true", pure = true)
	public static <E> boolean isEmpty(@Nullable Collection<E> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 判断集合是否为null、小于等于指定长度。
	 * <p>空值安全。
	 * @param collection 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <E> boolean isLessE(@Nullable Collection<E> collection, int length) {
		return collection == null || collection.size() <= length;
	}

	/**
	 * 判断集合是否大于等于指定长度。
	 * <p>空值安全。
	 * @param collection 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <E> boolean isGreaterE(@Nullable Collection<E> collection, int length) {
		return collection != null && collection.size() <= length;
	}


	/**
	 * 判断映射是否为null、为空。
	 * <p>空值安全。
	 * @param map 指定的泛型映射
	 */
	@Contract(value = "null -> true", pure = true)
	public static <K, V> boolean isEmpty(@Nullable Map<K, V> map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断映射是否为null、小于等于指定长度。
	 * <p>空值安全。
	 * @param map 指定的泛型映射
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <K, V> boolean isLessE(@Nullable Map<K, V> map, int length) {
		return map == null || map.size() <= length;
	}

	/**
	 * 判断映射是否大于等于指定长度。
	 * <p>空值安全。
	 * @param map 指定的泛型映射
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <K, V> boolean isGreaterE(@Nullable Map<K, V> map, int length) {
		return map != null && map.size() <= length;
	}
}
