package ua.nure.piontkovskyi.SummaryTask4.annotation;

import ua.nure.piontkovskyi.SummaryTask4.util.serializer.StreamSerializer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that annotated class is serializer.
 * @see StreamSerializer
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Serializer {

    /**
     * Prefix that indicates the name of the returned type if strongly recommended
     * @return prefix for serializer
     */
    String value();

}
