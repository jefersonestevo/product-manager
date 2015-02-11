package br.com.manager.infra.extensions;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;
import java.lang.annotation.*;

@InterceptorBinding
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Logged {

    @Nonbinding public Severity severity() default Severity.DEBUG;

    public enum Severity {
        TRACE,
        DEBUG,
        INFO,
        WARN,
        ERROR
    }

}
