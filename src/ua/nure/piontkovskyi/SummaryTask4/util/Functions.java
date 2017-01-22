package ua.nure.piontkovskyi.SummaryTask4.util;

import ua.nure.piontkovskyi.SummaryTask4.model.enums.Role;

import java.util.Collection;

/**
 * Contains functions used for EL function in JSP.
 **/
public final class Functions {

    /**
     * Defines whether the specified entity presents in the collection.
     * Entity is considered as found if the entity with the same id is found.
     *
     * @param collection collection to look in
     * @param item       entity to look for.
     * @param <E>        type of the entity
     * @return {@code true} if entity found, {@code false} otherwise
     */
    public static <E extends Role> boolean contains(Collection<E> collection, String item) {
        for (Role entity : collection) {
            Role e = Role.valueOf(item);
            if (e != null && e.equals(entity)) {
                return true;
            }
        }
        return false;
    }

}
