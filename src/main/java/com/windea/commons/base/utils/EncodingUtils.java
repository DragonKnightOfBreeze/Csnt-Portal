package com.windea.commons.base.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Properties;


/**
 * 编码的工具类。
 */
public class EncodingUtils {
	private EncodingUtils() {}

	private static final Log log = LogFactory.getLog(EncodingUtils.class);
	private static final String ISO_8859_1 = "ISO-8859-1";
	private static final String UTF_8 = "UTF-8";
	private static final String FAILED_TO_ENCODING = "Failed to encoding.";
	private static final String FILE_DOES_NOT_EXIST = "File does not exist, return the origin param 'file'.";
	private static final String FILE_CANNOT_READ_OR_WRITE = "File cannot read or write.";

	/**
	 * 重新编码指定的字符串。
	 */
	public static String encoding(String str) {
		return encoding(str, UTF_8);
	}

	/**
	 * 重新编码指定的字符串。
	 */
	public static String encoding(String str, String charsetName) {
		return encoding(str, ISO_8859_1, charsetName);
	}

	/**
	 * 重新编码指定的字符串。
	 */
	public static String encoding(String str, String rCharsetName, String charsetName) {
		try {
			return new String(str.getBytes(rCharsetName), charsetName);
		} catch(Exception e) {
			log.warn(FAILED_TO_ENCODING);
			return str;
		}
	}

	/**
	 * 重新编码指定的属性文件。
	 */
	public static Properties encoding(Properties properties) {
		return encoding(properties, UTF_8);
	}

	/**
	 * 重新编码指定的属性文件。
	 */
	public static Properties encoding(Properties properties, String charsetName) {
		return encoding(properties, ISO_8859_1, charsetName);
	}

	/**
	 * 重新编码指定的属性文件。
	 */
	public static Properties encoding(Properties properties, String rCharsetName, String charsetName) {
		try {
			var result = new Properties();
			for(var entry : properties.entrySet()) {
				var key = new String(entry.getKey().toString().getBytes(rCharsetName), charsetName);
				var value = new String(entry.getValue().toString().getBytes(rCharsetName), charsetName);
				result.setProperty(key, value);
			}
			return result;
		} catch(Exception e) {
			log.warn(FAILED_TO_ENCODING);
			return properties;
		}
	}

	/**
	 * 重新编码指定的文件。
	 * @param file 指定的文件。
	 * @return 编码后的文件。
	 */
	public static File encoding(File file) {
		return encoding(file, UTF_8);
	}

	/**
	 * 重新编码指定的文件。
	 * @param file 指定的文件。
	 * @param charsetName 用于编码的字符集。
	 * @return 编码后的文件。
	 */
	public static File encoding(File file, String charsetName) {
		return encoding(file, ISO_8859_1, charsetName);
	}

	/**
	 * 重新编码指定的文件。
	 * @param file 指定的文件。
	 * @param rCharsetName 用于解码的字符集。
	 * @param charsetName 用于编码的字符集。
	 * @return 编码后的文件。
	 */
	public static File encoding(File file, String rCharsetName, String charsetName) {
		//判断文件是否存在
		if(file.isDirectory() || !file.exists()) {
			log.warn(FILE_DOES_NOT_EXIST);
		}
		try {
			var path = file.toPath();
			var str = Files.readString(path, Charset.forName(rCharsetName));
			Files.writeString(path, str, Charset.forName(charsetName));
			file = path.toFile();
		} catch(IOException e1) {
			log.warn(FILE_CANNOT_READ_OR_WRITE);
		} catch(Exception e2) {
			log.warn(FAILED_TO_ENCODING);
		}
		return file;
	}
}
