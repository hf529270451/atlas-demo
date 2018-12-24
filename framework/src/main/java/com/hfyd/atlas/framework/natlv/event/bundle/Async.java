package com.hfyd.atlas.framework.natlv.event.bundle;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by hfyd on 2018/8/31.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface Async {
    String action();
}
