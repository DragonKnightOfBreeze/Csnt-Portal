package com.windea.commons.base.utils;

import org.jetbrains.annotations.*;

import java.text.MessageFormat;

/**
 * 字符串的工具类。
 */
public class StringUtils {
	public static final String WS2 = "  ";
	public static final String WS4 = "    ";
	public static final String T = "\t";
	public static final String N = "\n";


	private StringUtils() {}


	/**
	 * 判断字符串是否为null、为空。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 */
	@Contract(value = "null -> true", pure = true)
	public static boolean isEmpty(@Nullable String str) {
		return str == null || str.isEmpty();
	}

	/**
	 * 判断字符串是否为null、为空、为空白。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 */
	@Contract(value = "null -> true", pure = true)
	public static boolean isBlank(@Nullable String str) {
		return str == null || str.isBlank();
	}


	/**
	 * 判断字符串是否为null、小于等于指定长度。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> true", pure = true)
	public static boolean isLessE(@Nullable String str, int length) {
		return str == null || str.length() <= length;
	}

	/**
	 * 判断字符串是否大于等于指定长度。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param length 指定的长度
	 */
	@Contract(value = "null, _ -> false", pure = true)
	public static boolean isGreaterE(@Nullable String str, int length) {
		return str != null && str.length() >= length;
	}

	/**
	 * 判断字符串是否以指定的任意前缀开始。<br>
	 * 空值安全。
	 */
	public static boolean startsWith(@Nullable String str, String... prefixArray) {
		if(str == null) {
			return false;
		}
		for(var prefix : prefixArray) {
			if(str.startsWith(prefix)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断字符串是否以指定的任意后缀结束。<br>
	 * 空值安全。
	 */
	public static boolean endsWith(@Nullable String str, String... suffixArray) {
		if(str == null) {
			return false;
		}
		for(var suffix : suffixArray) {
			if(str.endsWith(suffix)) {
				return true;
			}
		}
		return false;
	}


	/**
	 * 判断两个字符串是否相等。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param secStr 第二个字符串
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static boolean equals(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.equals(secStr);
		}
	}

	/**
	 * 判断两个字符串是否相等，忽略空白。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param secStr 第二个字符串
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static boolean equalsIB(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.trim().equals(secStr.trim());
		}
	}

	/**
	 * 判断两个字符串是否相等，忽略大小写。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param secStr 第二个字符串
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static boolean equalsIC(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.equalsIgnoreCase(secStr);
		}
	}

	/**
	 * 判断两个字符串是否相等，忽略空白和大小写。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param secStr 第二个字符串
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static boolean equalsIBC(@Nullable String str, @Nullable String secStr) {
		if(str == null && secStr == null) {
			return true;
		} else if(str == null || secStr == null) {
			return false;
		} else {
			return str.trim().equalsIgnoreCase(secStr.trim());
		}
	}

	/**
	 * 判断指定的字符串和指定的枚举值是否相等。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param e 第二个枚举值
	 */
	@Contract(value = "null, null -> true; null, !null -> false; !null, null -> false", pure = true)
	public static <E extends Enum> boolean equalsE(@Nullable String str, @Nullable E e) {
		if(str == null && e == null) {
			return true;
		} else if(str == null || e == null) {
			return false;
		} else {
			return e.toString().equals(str);
		}
	}


	/**
	 * 将指定的字符串转化为整型数值（忽略空白）。如果失败，则转化为默认值。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param defaultVal 默认值
	 */
	public static int toInt(@Nullable String str, int defaultVal) {
		str = toStr(str);
		int result;
		try {
			result = Integer.parseInt(str);
		} catch(Exception e) {
			result = defaultVal;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为整型数值（忽略空白）。如果失败，则转化为0。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 */
	public static int toInt(@Nullable String str) {
		return toInt(str, 0);
	}

	/**
	 * 将指定的字符串转化为单精度数值（忽略空白）。如果失败，则转化为默认值。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param defaultVal 默认值
	 */
	public static float toFloat(@Nullable String str, float defaultVal) {
		str = toStr(str);
		float result;
		try {
			result = Float.parseFloat(str);
		} catch(Exception e) {
			result = defaultVal;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为单精度数值（忽略空白）。如果失败，则转化为默认值。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 */
	public static float toFloat(@Nullable String str) {
		return toFloat(str, 0.0f);
	}

	/**
	 * 将指定的字符串转化为双精度数值（忽略空白）。如果失败，则转化为默认值。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param defaultVal 默认值
	 */
	public static double toDouble(@Nullable String str, double defaultVal) {
		str = toStr(str);
		double result;
		try {
			result = Double.parseDouble(str);
		} catch(Exception e) {
			result = defaultVal;
		}
		return result;
	}

	/**
	 * 将指定的字符串转化为双精度数值（忽略空白）。如果失败，则转化为0。
	 * @param str 指定的字符串
	 */
	public static double toDouble(@Nullable String str) {
		return toDouble(str, 0.0);
	}

	/**
	 * 如果指定的字符串为null、为空、为空格，则转化为默认值。否则去除空白返回。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param defaultVal 默认值
	 */
	@Contract("null, _ -> param2")
	@NotNull
	public static String toStr(@Nullable String str, @NotNull String defaultVal) {
		return isBlank(str) ? defaultVal : str.trim();
	}

	/**
	 * 如果指定的字符串为null、为空、为空格，则转化为空字符串。否则去除空白返回。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 */
	@NotNull
	public static String toStr(@Nullable String str) {
		return toStr(str, "");
	}

	/**
	 * 将指定的字符串转化为枚举值。如果不匹配，则返回默认值。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 * @param defaultVal 默认值
	 */
	@Contract("null, _, _ -> null; !null, null, _ -> null")
	@Nullable
	public static <E extends Enum> E toEnum(@Nullable String str, @Nullable Class<E> clazz, @Nullable E defaultVal) {
		if(str == null || clazz == null)
			return null;
		E[] consts = clazz.getEnumConstants();
		for(E c : consts) {
			if(c.toString().equals(str)) {
				return c;
			}
		}
		return defaultVal;
	}

	/**
	 * 将指定的字符串转化为枚举值。如果不匹配，则返回null。<br>
	 * 空值安全。
	 * @param str 指定的字符串
	 * @param clazz 枚举类
	 */
	@Contract("null, _ -> null; !null, null -> null")
	@Nullable
	public static <E extends Enum> E toEnum(@Nullable String str, @Nullable Class<E> clazz) {
		return toEnum(str, clazz, null);
	}


	/**
	 * 模拟模版字符串。<br>
	 * 自动转义单引号。
	 * @param pattern 模版字符串
	 * @param args 参数
	 * @see MessageFormat
	 */
	@NotNull
	public static String format(@NotNull String pattern, @NotNull Object... args) {
		return MessageFormat.format(pattern.replace("'", "''"), args);
	}

	/**
	 * 模拟多行字符串。
	 * @param strArray 每行的字符串
	 */
	@NotNull
	public static String multiline(@NotNull String... strArray) {
		return String.join("\n", strArray);
	}

	public static String toCamelFormat(@NotNull String str) {
		return str.substring(0, 1).toLowerCase() + str.substring(1);
	}
}
