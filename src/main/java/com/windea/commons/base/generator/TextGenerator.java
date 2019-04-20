package com.windea.commons.base.generator;

import com.windea.commons.base.exception.NotImplementedException;
import org.springframework.cglib.core.internal.Function;

import java.util.function.Predicate;

public class TextGenerator {
	private StringBuilder builder = new StringBuilder();


	public static TextGenerator start() {
		return new TextGenerator();
	}


	public TextGenerator add(String text) {
		throw new NotImplementedException();
	}

	public TextGenerator addIf(String text, Predicate predicate) {
		throw new NotImplementedException();
	}

	public TextGenerator duplicate(String text, int times) {
		throw new NotImplementedException();
	}

	public TextGenerator duplicateForeach(String text, Function function) {
		throw new NotImplementedException();
	}
}
