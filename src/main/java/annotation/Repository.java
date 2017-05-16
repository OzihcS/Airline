package annotation;


import db.holder.ConnectionHolder;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * That annotated class is repository. Repositories must contain a constructor
 * taking {@link ConnectionHolder} object as parameter.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Repository {
}
