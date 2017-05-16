package filter.security;

import java.io.Serializable;

/**
 * Class used to store and retrieve a pair of values.
 *
 * @param <T>
 * @param <R>
 */
public class Tuple<T, R> implements Serializable {
    private T firstEntity;
    private R secondEntity;

    public Tuple() {
    }

    public Tuple(T firstEntity, R secondEntity) {
        this.firstEntity = firstEntity;
        this.secondEntity = secondEntity;
    }

    public T getFirstEntity() {
        return firstEntity;
    }

    public void setFirstEntity(T firstEntity) {
        this.firstEntity = firstEntity;
    }

    public R getSecondEntity() {
        return secondEntity;
    }

    public void setSecondEntity(R secondEntity) {
        this.secondEntity = secondEntity;
    }
}
