package com.windea.commons.base.annotation;

import java.lang.annotation.*;

/**
 * 可能显著影响性能的代码的注解。
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface PerformanceAffectPossible {
}
