package com.windea.commons.base.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 随机数的工具类。
 */
public class RandomUtils {
	private static final Random random = new SecureRandom();

	private RandomUtils() {}

	/**
	 * 生成指定范围内的随机数。<br>
	 * 包含最大值和最小值。
	 * @param min 最小值
	 * @param max 最大值
	 */
	public static int range(int min, int max) {
		if(min > max)
			throw new IllegalArgumentException("Min Value is greater than max Value.");

		return min + random.nextInt(max + 1 - min);
	}

	/**
	 * 生成指定范围内的随机数（从0到指定值）。<br>
	 * 包含最大值和最小值。
	 * @param max 最大值
	 */
	public static int range(int max) {
		return random.nextInt(max + 1);
	}

	/**
	 * 生成指定范围内的随机数（指定小数位数）。<br>
	 * 包含最大值和最小值。
	 * @param min 最小值
	 * @param max 最大值
	 * @param bit 精确度，小数点后位数
	 */
	public static float range(float min, float max, int bit) {
		if(bit < 0 || bit > 10)
			throw new IllegalArgumentException("Illegal bit of value.");

		float bitValue = (float) Math.pow((double) 10, (double) bit);
		return range((int) (min * bitValue), (int) (max * bitValue)) / bitValue;
	}

	/**
	 * 生成指定范围内的随机数（从0到指定值，指定小数位数）。<br>
	 * 包含最大值和最小值。
	 * @param max 最大值
	 * @param bit 精确度，小数点后位数
	 */
	public static float range(float max, int bit) {
		float bitValue = (float) Math.pow((double) 10, (double) bit);
		return range((int) (max * bitValue)) / bitValue;
	}

	/**
	 * 生成指定范围内的随机数（从0到1）。<br>
	 * 包含最大值和最小值。
	 */
	public static float range01() {
		return random.nextFloat();
	}

	/**
	 * 生成浮动范围内的随机数。<br>
	 * 包含最大值和最小值。
	 * @param num 指定的数字
	 * @param sub 向下浮动值
	 * @param add 向上浮动值
	 */
	public static int delta(int num, int sub, int add) {
		int randomDelta = range(sub + add);
		return num - sub + randomDelta;
	}

	/**
	 * 生成浮动范围内的随机数。<br>
	 * 包含最大值和最小值。
	 * @param num 指定的数字
	 * @param delta 浮动值
	 */
	public static int delta(int num, int delta) {
		return delta(num, delta, delta);
	}

	/**
	 * 生成浮动范围内的随机数（指定位数）。<br>
	 * 包含最大值和最小值。
	 * @param num 指定的数字
	 * @param sub 向下浮动值
	 * @param add 向上浮动值
	 * @param bit 精确度，小数点后位数
	 */
	public static float delta(float num, float sub, float add, int bit) {
		if(bit < 0 || bit > 10)
			throw new IllegalArgumentException("Illegal bit of value.");

		float bitValue = (float) Math.pow((double) 10, (double) bit);
		return delta((int) (num * bitValue), (int) (sub * bitValue), (int) (add * bitValue)) / bitValue;
	}

	/**
	 * 生成浮动范围内的随机数（指定位数）。<br>
	 * 包含最大值和最小值。
	 * @param num 指定的数字
	 * @param delta 浮动值
	 * @param bit 精确度，小数点后位数
	 */
	public static float delta(float num, float delta, int bit) {
		return delta(num, delta, delta, bit);
	}
}
