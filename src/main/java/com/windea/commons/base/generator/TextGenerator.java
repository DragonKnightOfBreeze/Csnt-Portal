package com.windea.commons.base.generator;

import com.windea.commons.base.annotation.*;
import com.windea.commons.base.exception.NotImplementedException;
import com.windea.commons.base.utils.MathUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.MessageFormat;
import java.util.Objects;
import java.util.function.*;
import java.util.stream.Stream;

/**
 * 自定义的文本生成器。
 */
public class TextGenerator implements TGenerator {
	private static final Log log = LogFactory.getLog(TextGenerator.class);

	private StringBuilder builder = new StringBuilder();


	private TextGenerator() {}

	/** 实例化空的文本生成器。 */
	public static TextGenerator gen() {
		return new TextGenerator();
	}


	/* 属性访问 */

	public String text() {
		return builder.toString();
	}


	/* 添加特殊文本 */

	/** 添加空格。 */
	public TextGenerator space() {
		return space(1);
	}

	/** 添加空格。 */
	public TextGenerator space(int times) {
		times = Math.max(0, times);
		builder.append(" ".repeat(times));
		return this;
	}

	/** 添加缩进。 */
	public TextGenerator indent() {
		return indent(1);
	}

	/** 添加缩进。 */
	public TextGenerator indent(int times) {
		times = Math.max(0, times);
		builder.append("\n".repeat(times));
		return this;
	}

	/** 添加换行。 */
	public TextGenerator newLine() {
		return newLine(1);
	}

	/** 添加换行。 */
	public TextGenerator newLine(int times) {
		times = Math.max(0, times);
		builder.append("\n".repeat(times));
		return this;
	}

	/** 添加左斜杠 {@code "/"}。 */
	public TextGenerator lSep() {
		builder.append("/");
		return this;
	}

	/** 添加右斜杠 {@code "\\"}。 */
	public TextGenerator rSep() {
		builder.append("\\");
		return this;
	}


	/* 添加文本 */

	/** 添加一段文本。 */
	public TextGenerator add(@Nullable String text) {
		if(text != null) {
			builder.append(text);
		}
		return this;
	}

	/** 提供空的文本生成器，添加一段文本。 */
	public TextGenerator add(@NotNull Function<TextGenerator, TextGenerator> textFunction) {
		var gen = TextGenerator.gen();
		var text = textFunction.apply(gen).text();
		return add(text);
	}

	/** 添加一段文本。 */
	@Deprecated
	public TextGenerator add(@NotNull Supplier<String> textSupplier) {
		var text = textSupplier.get();
		if(text != null) {
			builder.append(text);
		}
		return this;
	}


	/** 添加一行文本。 */
	public TextGenerator addLine(@Nullable String text) {
		return add(text).newLine();
	}

	/** 提供空的文本生成器，添加一行文本。 */
	public TextGenerator addLine(@NotNull Function<TextGenerator, TextGenerator> textFunction) {
		return add(textFunction).newLine();
	}


	/** 如果满足条件，添加一段文本。 */
	public TextGenerator addIf(@Nullable String text, boolean condition) {
		if(text != null && condition) {
			builder.append(text);
		}
		return this;
	}

	/** 提供空的文本生成器，如果满足条件，添加一段文本。 */
	public TextGenerator addIf(@NotNull Function<TextGenerator, TextGenerator> textFunction, boolean condition) {
		var gen = TextGenerator.gen();
		var text = textFunction.apply(gen).text();
		return addIf(text, condition);
	}


	/** 绑定指针对象，如果满足条件，则添加一段文本。 */
	public <T> TextGenerator addWhere(@Nullable String text,
		@Nullable T pointer, @NotNull Predicate<T> predicate) {
		var condition = pointer != null && predicate.test(pointer);
		if(text != null && condition) {
			builder.append(text);
		}
		return this;
	}

	/** 提供空的文本生成器，绑定指针对象，如果满足条件，则添加一段文本。 */
	public <T> TextGenerator addWhere(@NotNull Function<TextGenerator, TextGenerator> textFunction,
		@Nullable T pointer, @NotNull Predicate<T> predicate) {
		var gen = TextGenerator.gen();
		var text = textFunction.apply(gen).text();
		return addWhere(text, pointer, predicate);
	}


	/** 直到到达指定次数为止，重复添加一段文本。 */
	public TextGenerator addRepeat(@Nullable String text, int times) {
		if(text != null) {
			builder.append(text.repeat(times));
		}
		return this;
	}

	/** 提供空的文本生成器，直到到达指定次数为止，重复添加一段文本。 */
	public TextGenerator addRepeat(@NotNull Function<TextGenerator, TextGenerator> textFunction, int times) {
		var gen = TextGenerator.gen();
		var text = textFunction.apply(gen).text();
		return addRepeat(text, times);
	}


	/** 直到不满足条件为止，重复添加一段文本。 */
	@InfiniteLoopPossible
	public <T> TextGenerator addWhile(@Nullable String text,
		@Nullable T pointer, @NotNull Predicate<T> predicate, @NotNull Function<T, T> reduce) {
		if(text != null) {
			var condition = pointer != null && predicate.test(pointer);
			while((condition)) {
				builder.append(text);
				pointer = reduce.apply(pointer);
				condition = predicate.test(pointer);
			}
		}
		return this;
	}

	/** 提供空的文本生成器，直到不满足条件为止，重复添加一段文本。 */
	@InfiniteLoopPossible
	public <T> TextGenerator addWhile(@NotNull Function<TextGenerator, TextGenerator> textFunction,
		@Nullable T pointer, @NotNull Predicate<T> predicate, @NotNull Function<T, T> reduce) {
		var gen = TextGenerator.gen();
		var text = textFunction.apply(gen).text();
		return addWhile(text, pointer, predicate, reduce);
	}


	/** 指定需要映射的流，添加一组文本。 */
	public <E> TextGenerator addStream(@NotNull Stream<E> stream) {
		stream.filter(Objects::nonNull).forEach(e -> builder.append(e.toString()));
		return this;
	}

	/** 提供空的文本生成器，指定需要映射的流，添加一组文本。 */
	public <E> TextGenerator addStream(@NotNull BiFunction<TextGenerator, E, TextGenerator> textFunction,
		@NotNull Stream<E> stream) {
		var gen = TextGenerator.gen();
		stream.filter(Objects::nonNull).forEach(e -> builder.append(textFunction.apply(gen, e).text()));
		return this;
	}


	/* 包围文本。 */

	/** 根据前后缀，包围当前文本。指定当当前文本为空时，是否仍然适用。 */
	public TextGenerator surround(@Nullable String fix, boolean ignoreEmpty) {
		return surround(fix, fix, ignoreEmpty);
	}

	/** 提供空的文本生成器，根据前后缀，包围当前文本。指定当当前文本为空时，是否仍然适用。 */
	public TextGenerator surround(@NotNull Function<TextGenerator, TextGenerator> fixFunction, boolean ignoreEmpty) {
		return surround(fixFunction, fixFunction, ignoreEmpty);
	}

	/** 根据前缀和后缀，包围当前文本。指定当当前文本为空时，是否仍然适用。 */
	public TextGenerator surround(@Nullable String prefix, @Nullable String suffix, boolean ignoreEmpty) {
		var doSurround = !ignoreEmpty || !text().isEmpty();
		var gen = TextGenerator.gen()
			.addIf(prefix, doSurround)
			.add(text())
			.addIf(suffix, doSurround);
		return gen;
	}

	/** 提供空的文本生成器，根据前缀和后缀，包围当前文本。指定当当前文本为空时，是否仍然适用。 */
	public TextGenerator surround(@NotNull Function<TextGenerator, TextGenerator> prefixFunction,
		@NotNull Function<TextGenerator, TextGenerator> suffixFunction, boolean ignoreEmpty) {
		var doSurround = !ignoreEmpty || !text().isEmpty();
		var gen = TextGenerator.gen()
			.addIf(prefixFunction, doSurround)
			.add(text())
			.addIf(suffixFunction, doSurround);
		return gen;
	}


	/* 插入文本 */

	/** 插入一段文本。 */
	public TextGenerator insert(int offset, @Nullable String text) {
		if(text != null) {
			builder.insert(offset, text);
		}
		return this;
	}

	/** 提供空的文本生成器，插入一段文本。 */
	public TextGenerator insert(int offset, @NotNull Function<TextGenerator, TextGenerator> textFunction) {
		var gen = TextGenerator.gen();
		var text = textFunction.apply(gen).text();
		return insert(offset, text);
	}


	/* 删除文本 */

	/** 删除一段文本。 */
	public TextGenerator delete(int start) {
		return delete(start, text().length());
	}

	/** 删除一段文本。如果结束位置为负数，则从文本末尾开始计数。 */
	public TextGenerator delete(int start, int end) {
		start = Math.max(0, start);
		end = end < 0 ? text().length() - end : end;
		end = MathUtils.clamp(end, start, text().length());
		builder.delete(start, end);
		return this;
	}

	/** 清空当前文本。 */
	public TextGenerator empty() {
		builder.delete(0, builder.length());
		return this;
	}


	/* 修改文本 */

	/** 替换一段文本。 */
	public TextGenerator replace(int start, int end, @Nullable String text) {
		if(text != null) {
			start = Math.max(0, start);
			end = end < 0 ? text().length() - end : end;
			end = MathUtils.clamp(end, start, text().length());
			builder.replace(start, end, text);
		}
		return this;
	}

	/** 替换文本。 */
	public TextGenerator replace(@Nullable String origin, @Nullable String text) {
		if(origin != null && text != null) {
			var resultText = text().replace(origin, text);
			return TextGenerator.gen().add(resultText);
		}
		return this;
	}

	/** 根据正则表达式替换文本。 */
	public TextGenerator reReplace(@Nullable String pattern, @Nullable String text) {
		if(pattern != null && text != null) {
			var resultText = text().replaceAll(pattern, text);
			return TextGenerator.gen().add(resultText);
		}
		return this;
	}


	/**
	 * 格式化文本。使用索引占位符{@code "{0}"}。<br>
	 * 用法示例：{@code gen.add("hello {0}").format("world";)}。
	 * @see MessageFormat
	 */
	public TextGenerator formatByNum(Object... args) {
		try {
			var text = MessageFormat.format(text(), args);
			builder = new StringBuilder().append(text);
		} catch(IllegalArgumentException e) {
			log.warn("Format failed. Please check your args.");
			e.printStackTrace();
		}
		return this;
	}

	/**
	 * 格式化文本。使用名字占位符{@code "${name}"}。<br>
	 * 用法示例：{@code var name="world"; gen.add("hello ${name}").format(world)}。
	 * @see MessageFormat
	 */
	@PerformanceAffectPossible
	@NotImplemented
	public TextGenerator formatByName(Object... args) {
		throw new NotImplementedException();
	}


	/* 转到其他生成器 */

	/** 转到自定义的每行文本生成器。 */
	public TextLineGenerator withLines() {
		var gen = TextLineGenerator.gen(text().lines());
		return gen;
	}


	@Deprecated
	@Override
	public String toString() {
		return text();
	}
}
