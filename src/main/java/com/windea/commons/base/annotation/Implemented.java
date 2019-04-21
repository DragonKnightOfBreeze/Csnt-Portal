package com.windea.commons.base.annotation;

import java.lang.annotation.*;

/**
 * 已完成的代码的注解。
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface Implemented {
	String value() default "";
}
