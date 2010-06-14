package javaitzen.spring.liveconfig;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface LiveConfig.
 */
@Retention(RetentionPolicy.RUNTIME) 
@Target({ElementType.FIELD })
@Documented
public @interface LiveConfig {

	 /**
     * Config location.
     */
 	String configLocation() default "liveconfig.properties";
}

