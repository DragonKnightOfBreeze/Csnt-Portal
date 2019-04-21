package com.windea.commons.base.template;

import com.windea.commons.base.annotation.PerformanceAffectPossible;

import java.lang.reflect.InvocationTargetException;

/**
 * 泛型单例模式 - 双重校验锁。<br>
 * WARN 由于采用了反射技术，可能会影响性能。
 */
@PerformanceAffectPossible
public class TSingleton {
	private static volatile TSingleton instance;

	private TSingleton() { }

	/**
	 * 得到单例实例。
	 */
	@SuppressWarnings("unchecked")
	public static <T extends TSingleton> T getInstance(Class<T> clazz)
	throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException,
		InstantiationException {
		if(instance == null) {
			synchronized(TSingleton.class) {
				if(instance == null) {
					instance = (TSingleton) Class.forName(clazz.getName()).getDeclaredConstructor().newInstance();
				}
			}
		}
		return (T) instance;
	}
}
