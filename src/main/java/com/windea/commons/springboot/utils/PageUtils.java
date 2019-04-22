package com.windea.commons.springboot.utils;

import org.springframework.data.domain.*;

import java.util.Arrays;
import java.util.LinkedList;

public class PageUtils {
	/**
	 * 连接多个分页。
	 */
	@SafeVarargs
	public static <T> Page<T> concat(Pageable pageable, Page<T>... pages) {
		var content = new LinkedList<T>();
		Arrays.stream(pages).map(Slice::getContent)
			.forEach(c -> {
				content.removeAll(c);
				content.addAll(c);
			});
		return new PageImpl<>(content, pageable, ((long) content.size()));
	}
}
