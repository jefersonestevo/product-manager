package br.com.manager.infra.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.enterprise.inject.Stereotype;
import javax.interceptor.InvocationContext;

public class AnnotationUtils {

    public static <T extends Annotation> T findBindingAnnotation(Class<T> bindingType, InvocationContext ic) {
        Method method = ic.getMethod();
        if (method != null && method.isAnnotationPresent(bindingType)) {
            return method.getAnnotation(bindingType);
        }
        Class<? extends Object> type = ic.getTarget().getClass();
        if (type.isAnnotationPresent(bindingType)) {
            return type.getAnnotation(bindingType);
        }

        T annotationFromStereoType = annotationFromStereoType(bindingType, type);
        if (annotationFromStereoType != null) {
            return annotationFromStereoType;
        }

        throw new UnsupportedOperationException("no binding annotation found: " + bindingType.getCanonicalName());
    }

    private static <T extends Annotation> T annotationFromStereoType(Class<T> bindingType, Class<? extends Object> type) {
        for (Annotation annotation : type.getAnnotations()) {
            Class<? extends Annotation> annotationType = annotation.annotationType();
            if (annotationType.isAnnotationPresent(Stereotype.class)) {
                if (annotationType.isAnnotationPresent(bindingType)) {
                    return annotationType.getAnnotation(bindingType);
                }
                else {
                    T recursiveLookup = annotationFromStereoType(bindingType, annotationType);
                    if (null != recursiveLookup) {
                        return recursiveLookup;
                    }
                }
            }
        }
        return null;
    }
}
