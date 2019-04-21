package com.windea.commons.base.annotation;

import java.lang.annotation.*;

/**
 * 可能导致无限循环的代码的注解。
 */
@Documented
@Retention(RetentionPolicy.SOURCE)
public @interface InfiniteLoopPossible {
}
