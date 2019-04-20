package com.windea.commons.base.utils;

import com.windea.commons.base.exception.NotImplementedException;
import org.jetbrains.annotations.Contract;

/**
 * 数学的工具类。
 */
public class MathUtils {
	private MathUtils() {}

	/**
	 * 夹值方法。
	 * @param num 指定的数字
	 * @param min 最小值
	 * @param max 最大值
	 */
	@Contract(pure = true)
	public static int clamp(int num, int min, int max) {
		return (int) clamp((double) num, (double) min, (double) max);
	}

	/**
	 * 夹值方法。
	 * @param num 指定的数字
	 * @param min 最小值
	 * @param max 最大值
	 */
	@Contract(pure = true)
	public static float clamp(float num, float min, float max) {
		return (float) clamp((double) num, (double) min, (double) max);
	}

	/**
	 * 夹值方法。
	 * @param num 指定的数字
	 * @param min 最小值
	 * @param max 最大值
	 */
	@Contract(pure = true)
	public static double clamp(double num, double min, double max) {
		if(min > max)
			throw new IllegalArgumentException("Min Value is greater than max Value.");
		if(num < min) {
			num = min;
		} else if(num > max) {
			num = max;
		}
		return num;
	}

	/**
	 * 夹值方法（从0到1）。
	 * @param num 指定的数字
	 */
	@Contract(pure = true)
	public static float clamp01(float num) {
		return (float) clamp01((double) num);
	}

	/**
	 * 夹值方法（从0到1）。
	 * @param num 指定的数字
	 */
	@Contract(pure = true)
	public static double clamp01(double num) {
		if(num < 0) {
			num = 0;
		} else if(num > 1) {
			num = 1;
		}
		return num;
	}

	/**
	 * 插值方法。
	 */
	public static float lerp(float num, float target, float speed) {
		throw new NotImplementedException();
	}
}
