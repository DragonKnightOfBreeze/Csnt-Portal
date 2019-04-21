package com.windea.commons.base.template;

import com.windea.commons.base.annotation.PerformanceAffectPossible;
import com.windea.commons.base.utils.MathUtils;
import com.windea.commons.base.utils.ReflectionUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 通用实体类接口 - 实现通用的equals、hashCode、toString方法。<br>
 * WARN 由于采用了反射技术，可能会影响性能。
 */
@PerformanceAffectPossible
public abstract class TBean<ID> implements Serializable {
	private static final long serialVersionUID = 7786447621429565474L;

	public abstract ID getId();

	/**
	 * 重写后的等于方法，比较实体类，实体类的类型和实体类的id。
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) {
			return true;
		}
		if(obj == null || getClass() != obj.getClass()) {
			return false;
		}
		TBean bean = (TBean) obj;
		return Objects.equals(getId(), bean.getId());
	}

	/**
	 * 重写后的哈希方法，比较实体类和实体类的id。
	 */
	@Override
	public int hashCode() {
		var hashcode = Objects.hash(getId());
		if(hashcode == 0) {
			hashcode = Objects.hashCode(this);
		}
		return hashcode;
	}

	/**
	 * 重写后的转化成字符串方法，写入所有匹配get方法的基本封装类型的属性。可以指定是否换行和缩进。<br>
	 * indent=-1使用tab缩进。
	 */
	@Override
	public String toString() {
		return toString(true, -1);
	}

	public String toString(boolean breakLine, int indent) {
		indent = MathUtils.clamp(indent, -1, 8);

		var rStripIndex = breakLine ? 3 : 2;
		var indentText = indent == -1 ? "\t" : " ".repeat(indent);

		var objName = getClass().getName();
		//不要将基本类型的数据作为字段
		//不能直接使用stream的toMap方法，因为值可能为空
		var basePropEntryList = ReflectionUtils.getPropMap(this).entrySet().stream()
			.filter(e -> e.getValue() == null || ReflectionUtils.isBaseType(e.getValue().getClass()))
			.collect(Collectors.toList());

		var builder = new StringBuilder();
		builder.append(objName).append("{");
		if(breakLine) {
			builder.append("\n");
		}
		for(var entry : basePropEntryList) {
			builder.append(indentText);
			builder.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
			if(breakLine) {
				builder.append("\n");
			}
		}
		if(!basePropEntryList.isEmpty()) {
			builder.replace(builder.length() - rStripIndex, builder.length() - rStripIndex + 2, "");
		}
		builder.append("}");
		return builder.toString();
	}
}
