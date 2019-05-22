package com.windea.commons.base.utils;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * 集合的工具类。
 * NOTE 不要新建集合连接的工具方法，使用removeAll()和addAll()
 */
public class CollectionUtils {
	private CollectionUtils() {}

	/**
	 * 判断集合是否为null、为空。
	 * 空值安全。
	 * @param collection 指定的泛型集合
	 */
	@Contract(value = "null -> true", pure = true)
	public static <C extends Collection<E>, E> boolean isEmpty(@Nullable C collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 判断集合是否为null、小于等于指定长度。
	 * 空值安全。
	 * @param collection 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <C extends Collection<E>, E> boolean isLessE(@Nullable C collection, int length) {
		return collection == null || collection.size() <= length;
	}

	/**
	 * 判断集合是否大于等于指定长度。
	 * 空值安全。
	 * @param collection 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <C extends Collection<E>, E> boolean isGreaterE(@Nullable C collection, int length) {
		return collection != null && collection.size() <= length;
	}


	/**
	 * 判断映射是否为null、为空。
	 * 空值安全。
	 * @param map 指定的泛型映射
	 */
	@Contract(value = "null -> true", pure = true)
	public static <M extends Map<K, V>, K, V> boolean isEmpty(@Nullable M map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断映射是否为null、小于等于指定长度。
	 * 空值安全。
	 * @param map 指定的泛型映射
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <M extends Map<K, V>, K, V> boolean isLessE(@Nullable M map, int length) {
		return map == null || map.size() <= length;
	}

	/**
	 * 判断映射是否大于等于指定长度。
	 * 空值安全。
	 * @param map 指定的泛型映射
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <M extends Map<K, V>, K, V> boolean isGreaterE(@Nullable M map, int length) {
		return map != null && map.size() <= length;
	}
}
