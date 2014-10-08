package de.example.sql.deadlocks.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DeadlockRetry {

	/**
	 * Tries max number. Default value 3.
	 * 
	 * @return tries max number.
	 */
	int maxTries() default 3;
	
	/**
	 * Interval in milliseconds between subsequent repeats. Default value 1000.
	 * 
	 * @return interval in milliseconds
	 */
	int interval() default 1000;
}