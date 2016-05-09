package cn.edu.hpu.yuan.yuannews.main.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by yuan on 16-5-9.
 * Scope ，指定注解范围
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {
}
