package com.sweet.common.annotation;

import java.lang.annotation.*;

@Target(ElementType.FIELD) // 作用目标：属性字段
@Retention(RetentionPolicy.RUNTIME) // 生命周期：运行时保留
@Documented // 可被文档工具识别
public @interface QueryCondition {

    String type() default "eq";

    String column() default "";

    String pairWith() default "";
}
