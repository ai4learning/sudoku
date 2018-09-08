package com.goldfish.file;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 文件POJO的 field注解
 * Created by david on 2015/10/21.
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileField {

    /**
     * 如果为空，以文件变量名做key
     * @return
     */
   String field() default  "";

    /** 文件后缀名*/
    String ext() default "";

}
