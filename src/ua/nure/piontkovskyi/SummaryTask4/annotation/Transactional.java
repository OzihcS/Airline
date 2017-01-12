package ua.nure.piontkovskyi.SummaryTask4.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that method should be invoked within a transaction.
 * Used to annotate methods of interfaces annotated with {@link Service} annotation
 *
 * @see Service
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Transactional {
}
