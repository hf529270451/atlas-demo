package com.hfyd.atlas.framework.natlv.event.bundle;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hfyd on 2018/8/31.
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface ReturnName {
    String value();

}
