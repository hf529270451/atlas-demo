package com.hfyd.atlas.framework.natlv.event.bundle;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Author: hfyd
 * Date: 2018/12/19
 * Description:
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface TransactionLevel {

    TransactionType value();
}