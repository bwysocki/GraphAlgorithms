package pl.stalostech.graph.domain;

import java.util.Set;

/**
 * DB entity - representing graph node.
 * @author Bartosz Wysocki
 */
public abstract class AbstractNode<T> {

    protected Long id;

    protected String name;

    protected int x;

    protected int y;

    public abstract Set<T> getConnections();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
