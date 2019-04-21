package com.windea.commons.base.utils;

import org.jetbrains.annotations.*;

import java.util.Collection;
import java.util.Map;

/**
 * 集合的工具类。
 */
public class CollectionUtils {
	private CollectionUtils() {}

	/**
	 * 判断集合是否为null、为空。<br>
	 * 空值安全。
	 * @param collection 指定的泛型集合
	 */
	@Contract(value = "null -> true", pure = true)
	public static <C extends Collection<E>, E> boolean isEmpty(@Nullable C collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * 判断集合是否为null、小于等于指定长度。<br>
	 * 空值安全。
	 * @param collection 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <C extends Collection<E>, E> boolean isLessE(@Nullable C collection, int length) {
		return collection == null || collection.size() <= length;
	}

	/**
	 * 判断集合是否大于等于指定长度。<br>
	 * 空值安全。
	 * @param collection 指定的泛型集合
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <C extends Collection<E>, E> boolean isGreaterE(@Nullable C collection, int length) {
		return collection != null && collection.size() <= length;
	}


	/**
	 * 连接两个集合。可以指定是否去掉重复项。
	 */
	public static <C extends Collection<E>, E> C concat(boolean allowsDuplicate,
		@NotNull C collection, @NotNull C other) {
		if(!allowsDuplicate) {
			collection.removeAll(other);
		}
		collection.addAll(other);
		return collection;
	}

	/**
	 * 连接多个集合。可以指定是否去掉重复项。
	 */
	@SafeVarargs
	public static <C extends Collection<E>, E> C concat(boolean allowsDuplicate,
		@NotNull C collection, @NotNull C... others) {
		for(var other : others) {
			if(!allowsDuplicate) {
				collection.removeAll(other);
			}
			collection.addAll(other);
		}
		return collection;
	}


	/**
	 * 判断映射是否为null、为空。<br>
	 * 空值安全。
	 * @param map 指定的泛型映射
	 */
	@Contract(value = "null -> true", pure = true)
	public static <M extends Map<K, V>, K, V> boolean isEmpty(@Nullable M map) {
		return map == null || map.isEmpty();
	}

	/**
	 * 判断映射是否为null、小于等于指定长度。<br>
	 * 空值安全。
	 * @param map 指定的泛型映射
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static <M extends Map<K, V>, K, V> boolean isLessE(@Nullable M map, int length) {
		return map == null || map.size() <= length;
	}

	/**
	 * 判断映射是否大于等于指定长度。<br>
	 * 空值安全。
	 * @param map 指定的泛型映射
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static <M extends Map<K, V>, K, V> boolean isGreaterE(@Nullable M map, int length) {
		return map != null && map.size() <= length;
	}


	/**
	 * 连接两个映射。可以指定是否覆盖重复项。
	 */
	public static <M extends Map<K, V>, K, V> M concat(boolean allowsMerge,
		@NotNull M map, @NotNull M other) {
		for(var entry : other.entrySet()) {
			if(map.containsKey(entry.getKey())) {
				if(allowsMerge) {
					map.put(entry.getKey(), entry.getValue());
				}
			}
			map.put(entry.getKey(), entry.getValue());
		}
		return map;
	}

	/**
	 * 连接多个映射。可以指定是否覆盖重复项。
	 */
	@SafeVarargs
	public static <M extends Map<K, V>, K, V> M concat(boolean allowsMerge,
		@NotNull M map, @NotNull M... others) {
		for(var other : others) {
			for(var entry : other.entrySet()) {
				if(map.containsKey(entry.getKey())) {
					if(allowsMerge) {
						map.put(entry.getKey(), entry.getValue());
					}
				}
				map.put(entry.getKey(), entry.getValue());
			}
		}
		return map;
	}
}
