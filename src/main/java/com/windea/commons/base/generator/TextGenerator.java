package com.windea.commons.base.generator;

import com.windea.commons.base.exception.NotImplementedException;

import java.util.function.Predicate;

/**
 * 自定义的文本生成器。
 */
public class TextGenerator {
	private StringBuilder builder = new StringBuilder();


	public static TextGenerator start() {
		return new TextGenerator();
	}


	public TextGenerator add(String text) {
		throw new NotImplementedException();
	}

	public TextGenerator addWhere(String text, Predicate predicate) {
		throw new NotImplementedException();
	}


	public TextGenerator repeat(String text, int times) {
		throw new NotImplementedException();
	}

	public TextGenerator repeatWhere(String text, Predicate predicate) {
		throw new NotImplementedException();
	}


	public TextGenerator join(String text, String sep) {
		throw new NotImplementedException();
	}

	public TextGenerator joinEach(String text, String sep) {
		throw new NotImplementedException();
	}
}
