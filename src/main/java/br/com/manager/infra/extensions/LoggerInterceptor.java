package br.com.manager.infra.extensions;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.util.Arrays;

import br.com.manager.infra.utils.AnnotationUtils;

@Logged
@Interceptor
public class LoggerInterceptor {

    @AroundInvoke
    public Object log(InvocationContext ctx) throws Exception {
        if (Modifier.isPublic(ctx.getMethod().getModifiers())) {
            Class realClass = this.getRealClass(ctx);
            String message = this.getMessage(realClass.getSimpleName(), ctx);
            this.log(this.getSeverity(ctx), realClass, message);
        }
        return ctx.proceed();
    }

    private Class getRealClass(InvocationContext ctx) {
        if (ctx.getTarget().getClass().getSimpleName().contains("$")) {
            return ctx.getTarget().getClass().getSuperclass();
        }
        return ctx.getTarget().getClass();
    }

    private String getMessage(String className, InvocationContext ctx) {
        StringBuilder builder = new StringBuilder();
        builder.append(className);
        builder.append(".").append(ctx.getMethod().getName());
        if (ctx.getParameters().length > 0) {
            builder.append(": ");
            for (int i = 0; i < ctx.getParameters().length; i++) {
                if (i > 0) {
                    builder.append(", ");
                }
                builder.append("[").append(i).append("]=").append(this.getParameterAsString(ctx.getParameters()[i]));
            }
        } else {
            builder.append(": no parameters");
        }
        return builder.toString();
    }

    private String getParameterAsString(Object param) {
        if (param == null) {
            return "null";
        }
        // TODO - Alterar para arrays ou collection
        return param.toString();
    }

    private Logged.Severity getSeverity(InvocationContext ctx) {
        return AnnotationUtils.findBindingAnnotation(Logged.class, ctx).severity();
    }

    private void log(Logged.Severity severity, Class realClass, String message) {
        Logger logger = LoggerFactory.getLogger(realClass);
        switch (severity) {
            case TRACE:
                logger.trace(message);
                break;
            case DEBUG:
                logger.debug(message);
                break;
            case INFO:
                logger.info(message);
                break;
            case WARN:
                logger.warn(message);
                break;
            case ERROR:
                logger.error(message);
                break;
        }
    }

}
