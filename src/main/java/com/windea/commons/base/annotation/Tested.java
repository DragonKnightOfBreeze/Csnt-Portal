package com.windea.commons.base.annotation;

import java.lang.annotation.*;

/**
 * 已测试的代码的注解。
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Tested {
	String value() default "";
}
