package ua.nure.piontkovskyi.SummaryTask4.annotation;

import ua.nure.piontkovskyi.SummaryTask4.db.AnnotationHandler;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates a service. Service must implement some interface for transaction handling.
 *
 * @see Transactional
 * @see AnnotationHandler
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Service {
}
