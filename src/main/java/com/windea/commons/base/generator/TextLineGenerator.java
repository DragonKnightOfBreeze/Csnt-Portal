package com.windea.commons.base.generator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.LinkedList;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * 自定义的每行文本生成器。
 */
public class TextLineGenerator implements TGenerator {
	private static final Log log = LogFactory.getLog(TextLineGenerator.class);

	private Stream<String> lines;


	private TextLineGenerator(Stream<String> lines) {
		this.lines = lines;
	}

	/** 实例化每行文本生成器。 */
	public static TextLineGenerator gen(Stream<String> lines) {
		return new TextLineGenerator(lines);
	}


	/* 属性访问 */

	public String text() {
		return lines.reduce((a, b) -> a + "\n" + b).orElse("");
	}

	public int length() {
		return text().length();
	}


	/* 流处理方法 */

	/** 映射每行文本。 */
	public TextLineGenerator map(Function<String, String> function) {
		lines = lines.map(function);
		return this;
	}

	/** 提供空的文本生成器，映射每行文本。 */
	public TextLineGenerator mapBy(Function<TextGenerator, TextGenerator> function) {
		var gen = TextGenerator.gen();
		lines = lines.map(line -> function.apply(gen.empty().add(line)).text());
		return this;
	}


	/** 过滤每行文本。 */
	public TextLineGenerator filter(Predicate<String> predicate) {
		lines = lines.filter(predicate);
		return this;
	}

	/** 提供空的文本生成器，过滤每行文本。 */
	public TextLineGenerator filterBy(Predicate<TextGenerator> predicate) {
		var gen = TextGenerator.gen();
		lines = lines.filter(line -> predicate.test(gen.empty().add(line)));
		return this;
	}


	/** 累积每行文本。 */
	public String reduce(BinaryOperator<String> accumulator) {
		var text = lines.reduce(accumulator).orElse("");
		return text;
	}

	/** 提供空的文本生成器，累积每行文本。 */
	public String reduceBy(BinaryOperator<TextGenerator> accumulator) {
		var genA = TextGenerator.gen();
		var genB = TextGenerator.gen();
		var text = lines.reduce((a, b) -> accumulator.apply(genA.empty().add(a), genB.empty().add(b)).text())
			.orElse("");
		return text;
	}


	/* 特殊流处理方法 */

	/** 对于每行文本，适用自动换行。 */
	public TextLineGenerator wrapLines(int width) {
		var list = new LinkedList<String>();
		lines.forEach(line -> {
			if(line.length() > width) {
				list.add(line.substring(0, width));
				list.add(line.substring(width));
			} else {
				list.add(line);
			}
		});
		lines = list.stream();
		return this;
	}

	/** 对于每行文本，适用右空格去除。 */
	public TextLineGenerator rStripLines() {
		lines = lines.map(String::stripTrailing);
		return this;
	}


	/* 转到其他生成器 */

	/** 转到自定义的文本生成器。 */
	public TextGenerator and() {
		var gen = TextGenerator.gen().add(text());
		return gen;
	}


	@Deprecated
	@Override
	public String toString() {
		return text();
	}
}
