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
import java.util.regex.PatternSyntaxException;
import java.util.stream.Stream;

/**
 * 自定义的文本生成器。
 */
public class TextGenerator implements TGenerator {
	private static final Log log = LogFactory.getLog(TextGenerator.class);

	private StringBuilder builder = new StringBuilder();


	private TextGenerator() {}

	/**
	 * 实例化空的文本生成器。
	 */
	public static TextGenerator gen() {
		return new TextGenerator();
	}


	public String text() {
		return builder.toString();
	}


	/**
	 * 添加空格。
	 */
	public TextGenerator space() {
		return space(1);
	}

	/**
	 * 添加空格。
	 */
	public TextGenerator space(int times) {
		times = Math.max(0, times);
		builder.append(" ".repeat(times));
		return this;
	}

	/**
	 * 添加缩进。
	 */
	public TextGenerator indent() {
		return indent(1);
	}

	/**
	 * 添加缩进。
	 */
	public TextGenerator indent(int times) {
		times = Math.max(0, times);
		builder.append("\n".repeat(times));
		return this;
	}

	/**
	 * 添加换行。
	 */
	public TextGenerator newLine() {
		return newLine(1);
	}

	/**
	 * 添加换行。
	 */
	public TextGenerator newLine(int times) {
		times = Math.max(0, times);
		builder.append("\n".repeat(times));
		return this;
	}

	/**
	 * 添加左斜杠 {@code "/"}。
	 */
	public TextGenerator lSep() {
		builder.append("/");
		return this;
	}

	/**
	 * 添加右斜杠 {@code "\\"}。
	 */
	public TextGenerator rSep() {
		builder.append("\\");
		return this;
	}


	/**
	 * 添加一段文本。
	 */
	public TextGenerator add(@Nullable String text) {
		if(text != null) {
			builder.append(text);
		}
		return this;
	}

	/**
	 * 提供空的文本生成器，添加一段文本。
	 */
	public TextGenerator add(@NotNull Function<TextGenerator, TextGenerator> textFunction) {
		var text = textFunction.apply(TextGenerator.gen()).text();
		return add(text);
	}


	/**
	 * 指定需要映射的字符串流，添加一组文本。
	 */
	public TextGenerator addStream(@NotNull Stream<String> stream) {
		stream.filter(Objects::nonNull)
			.forEach(e -> builder.append(e));
		return this;
	}

	/**
	 * 提供空的文本生成器，指定需要映射的字符串流，添加一组文本。
	 */
	public TextGenerator addStream(@NotNull Stream<String> stream,
		@NotNull BiFunction<TextGenerator, String, TextGenerator> textFunction) {
		var gen = TextGenerator.gen();
		stream.filter(Objects::nonNull).map(e -> textFunction.apply(gen.empty(), e).text())
			.forEach(e -> builder.append(e));
		return this;
	}


	/**
	 * 指定需要映射的字符串流，加入一组文本。
	 */
	public TextGenerator joinStream(@NotNull Stream<String> stream,
		@Nullable String join) {
		if(join == null) {
			return addStream(stream);
		}
		stream.filter(Objects::nonNull)
			.forEach(e -> builder.append(e).append(join));
		builder.delete(builder.length() - join.length(), builder.length());
		return this;
	}

	/**
	 * 提供空的文本生成器，指定需要映射的字符串流，加入一组文本。
	 */
	public TextGenerator joinStream(@NotNull Stream<String> stream,
		@NotNull BiFunction<TextGenerator, String, TextGenerator> textFunction,
		@NotNull Function<TextGenerator, TextGenerator> joinFunction) {
		var join = joinFunction.apply(TextGenerator.gen()).text();
		if(join == null) {
			return addStream(stream, textFunction);
		}
		var gen = TextGenerator.gen();
		stream.filter(Objects::nonNull).map(e -> textFunction.apply(gen.empty(), e).text())
			.forEach(e -> builder.append(e).append(join));
		builder.delete(builder.length() - join.length(), builder.length());
		return this;
	}


	/**
	 * 添加一行文本。不需要换行符。
	 */
	public TextGenerator addLine(@Nullable String line) {
		return add(line).newLine();
	}

	/**
	 * 提供空的文本生成器，添加一行文本。不需要换行符。
	 */
	public TextGenerator addLine(@NotNull Function<TextGenerator, TextGenerator> lineFunction) {
		return add(lineFunction).newLine();
	}


	/**
	 * 指定需要映射的字符串流，添加多行文本。
	 */
	public TextGenerator addLineStream(@NotNull Stream<String> stream) {
		stream.filter(Objects::nonNull)
			.forEach(e -> builder.append(e).append("\n"));
		return this;
	}

	/**
	 * 提供空的文本生成器，指定需要映射的字符串流，多行文本。
	 */
	public TextGenerator addLineStream(@NotNull Stream<String> stream,
		@NotNull BiFunction<TextGenerator, String, TextGenerator> lineFunction) {
		var gen = TextGenerator.gen();
		stream.filter(Objects::nonNull).map(e -> lineFunction.apply(gen.empty(), e).text())
			.forEach(e -> builder.append(e).append("\n"));
		return this;
	}


	/**
	 * 如果不满足指定条件，去除当前文本。
	 */
	public TextGenerator where(boolean condition) {
		if(!condition) {
			empty();
		}
		return this;
	}

	/**
	 * 绑定指针对象，如果不满足指定条件，去除当前文本。
	 */
	public <T> TextGenerator where(@Nullable T pointer, @NotNull Predicate<T> predicate) {
		var condition = pointer != null && predicate.test(pointer);
		if(!condition) {
			empty();
		}
		return this;
	}


	/**
	 * 直到到达指定次数为止，重复当前文本。
	 */
	public TextGenerator repeat(int times) {
		times = Math.max(1, times);
		var text = text();
		builder.append(text.repeat(times - 1));
		return this;
	}

	/** 直到不满足指定条件为止，重复当前文本。 */
	@InfiniteLoopPossible
	public <T> TextGenerator repeat(@Nullable T pointer,
		@NotNull Predicate<T> predicate, @NotNull Function<T, T> reduce) {
		if(pointer != null) {
			pointer = reduce.apply(pointer);
			var condition = predicate.test(pointer);
			var text = text();
			while((condition)) {
				builder.append(text);
				pointer = reduce.apply(pointer);
				condition = predicate.test(pointer);
			}
		}
		return this;
	}


	/**
	 * 根据前后缀，包围当前文本。可指定当当前文本为空时，是否仍然适用。
	 */
	public TextGenerator surround(@Nullable String fix, boolean ignoreEmpty) {
		return surround(fix, fix, ignoreEmpty);
	}

	/**
	 * 提供空的文本生成器，根据前后缀，包围当前文本。可指定当当前文本为空时，是否仍然适用。
	 */
	public TextGenerator surround(@NotNull Function<TextGenerator, TextGenerator> fixFunction, boolean ignoreEmpty) {
		return surround(fixFunction, fixFunction, ignoreEmpty);
	}

	/**
	 * 根据前缀和后缀，包围当前文本。可指定当当前文本为空时，是否仍然适用。
	 */
	public TextGenerator surround(@Nullable String prefix, @Nullable String suffix, boolean ignoreEmpty) {
		var doSurround = !ignoreEmpty || !text().isEmpty();
		var gen = TextGenerator.gen()
			.add(g -> g.add(prefix).where(doSurround))
			.add(text())
			.add(g -> g.add(suffix).where(doSurround));
		return gen;
	}

	/**
	 * 提供空的文本生成器，根据前缀和后缀，包围当前文本。可指定当当前文本为空时，是否仍然适用。
	 */
	public TextGenerator surround(@NotNull Function<TextGenerator, TextGenerator> prefixFunction,
		@NotNull Function<TextGenerator, TextGenerator> suffixFunction, boolean ignoreEmpty) {
		var doSurround = !ignoreEmpty || !text().isEmpty();
		var gen = TextGenerator.gen()
			.add(g -> g.add(prefixFunction).where(doSurround))
			.add(text())
			.add(g -> g.add(suffixFunction).where(doSurround));
		return gen;
	}


	/**
	 * 插入一段文本。
	 */
	public TextGenerator insert(int offset, @Nullable String insert) {
		if(insert != null) {
			builder.insert(offset, insert);
		}
		return this;
	}

	/**
	 * 提供空的文本生成器，插入一段文本。
	 */
	public TextGenerator insert(int offset, @NotNull Function<TextGenerator, TextGenerator> insertFunction) {
		var text = insertFunction.apply(TextGenerator.gen()).text();
		return insert(offset, text);
	}


	/**
	 * 删除一段文本。
	 */
	public TextGenerator delete(int start) {
		return delete(start, text().length());
	}

	/**
	 * 删除一段文本。如果结束位置为负数，则从文本末尾开始计数。
	 */
	public TextGenerator delete(int start, int end) {
		start = Math.max(0, start);
		end = end < 0 ? text().length() - end : end;
		end = MathUtils.clamp(end, start, text().length());
		builder.delete(start, end);
		return this;
	}

	/**
	 * 清空当前文本。
	 */
	public TextGenerator empty() {
		builder.delete(0, builder.length());
		return this;
	}


	/**
	 * 替换一段文本。
	 */
	public TextGenerator replace(int start, int end, @Nullable String repl) {
		if(repl != null) {
			start = Math.max(0, start);
			end = end < 0 ? text().length() - end : end;
			end = MathUtils.clamp(end, start, text().length());
			builder.replace(start, end, repl);
		}
		return this;
	}

	/**
	 * 提供空的文本生成器，替换一段文本。
	 */
	public TextGenerator replace(int start, int end, @NotNull Function<TextGenerator, TextGenerator> replFunction) {
		var repl = replFunction.apply(TextGenerator.gen()).text();
		return replace(start, end, repl);
	}


	/**
	 * 替换所有匹配的文本。可指定是否使用正则表达式。
	 */
	public TextGenerator replaceAll(@Nullable String originOrPattern, @Nullable String repl, boolean useRegex) {
		try {
			if(originOrPattern != null && repl != null) {
				var text = text();
				var resultText = useRegex ? text.replaceAll(originOrPattern, repl) : text
					.replace(originOrPattern, repl);
				return TextGenerator.gen().add(resultText);
			}
		} catch(PatternSyntaxException e) {
			log.warn("Regexp pattern syntax failed.");
		}
		return this;
	}

	/**
	 * 提供空的文本生成器，替换所有匹配的文本。可指定是否使用正则表达式。
	 */
	public TextGenerator replaceAll(@Nullable String originOrPattern,
		@NotNull Function<TextGenerator, TextGenerator> replFunction, boolean userRegex) {
		var repl = replFunction.apply(TextGenerator.gen()).text();
		return replaceAll(originOrPattern, repl, userRegex);
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


	/**
	 * 转到自定义的每行文本生成器。
	 */
	public TextLineGenerator withLines() {
		var gen = TextLineGenerator.gen().set(text().lines());
		return gen;
	}


	@Deprecated
	@Override
	public String toString() {
		return text();
	}
}
