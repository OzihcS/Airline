package entity;

import java.io.Serializable;

/**
 * Base class for all entity class.
 */
public abstract class AbstractEntity implements Serializable {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
