package com.windea.commons.springboot.utils;

import com.windea.commons.base.utils.CollectionUtils;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.Arrays;

public class PageUtils {
	/**
	 * 连接多个分页。
	 */
	@SafeVarargs
	public static <T> Page<T> concat(Pageable pageable, Page<T>... pages) {
		var content = Arrays.stream(pages).map(Slice::getContent)
			.reduce((a, b) -> CollectionUtils.concat(false, a, b))
			.orElse(new ArrayList<>());
		return new PageImpl<>(content, pageable, ((long) content.size()));
	}
}
