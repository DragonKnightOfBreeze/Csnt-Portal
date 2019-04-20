package com.windea.commons.base.utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.UUID;

/**
 * 路径的工具类。
 */
public class PathUtils {
	private PathUtils() {}

	/**
	 * 组成完整路径。
	 */
	@NotNull
	public static String join(@NotNull String... path) {
		return (String.join(File.separator, path));
	}

	/**
	 * 得到文件名（只有名字，没有路径）。
	 */
	@NotNull
	public static String getFileName(@NotNull String filePath) {
		int index = filePath.lastIndexOf("\\");
		if(index == -1)
			return filePath;
		return filePath.substring(index + 1);
	}

	/**
	 * 得到切去拓展名的文件名（只有名字，没有路径）。
	 */
	@NotNull
	public static String getShotFileName(@NotNull String filePath) {
		String fileName = getFileName(filePath);
		int index = filePath.lastIndexOf(".");
		if(index == -1)
			return filePath;
		return filePath.substring(0, index - 1);
	}

	/**
	 * 得到文件的扩展名。如果没有，则返回空字符串。
	 */
	@NotNull
	public static String getExtFileName(@NotNull String fileName) {
		int index = fileName.lastIndexOf(".");
		if(index == -1)
			return "";
		return fileName.substring(index + 1);
	}

	/**
	 * 生成随机的文件名（保留拓展名）。
	 */
	@NotNull
	public static String getRandomFileName(@NotNull String fileName) {
		String uuid = UUID.randomUUID().toString();
		//尝试得到扩展名
		int index = fileName.lastIndexOf(".");
		if(index != -1)
			return uuid + fileName.substring(index);
		return uuid;
	}

	/**
	 * 生成随机的文件目录名（一级+二级）。
	 */
	@NotNull
	public static String getRandomDirName(@NotNull String fileName) {
		int hashCode = fileName.hashCode();
		//一级目录
		int d1 = hashCode & 0xf;
		//二级目录
		int d2 = (hashCode >> 4) & 0xf;
		return "/" + d1 + "/" + d2;
	}
}
