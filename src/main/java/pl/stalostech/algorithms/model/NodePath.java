package pl.stalostech.algorithms.model;

import pl.stalostech.graph.domain.AbstractNode;

/**
 * Represents node and path in BFS algorithm
 * @author Bartosz Wysocki
 */
public class NodePath <T>{

    private AbstractNode<T> node;

    private String path;

    public AbstractNode<T> getNode() {
        return node;
    }

    public NodePath(AbstractNode<T> node, String path) {
        super();
        this.node = node;
        this.path = path;
    }

    public void setNode(AbstractNode<T> node) {
        this.node = node;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "BFSNodePath [node=" + node + ", path=" + path + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((node == null) ? 0 : node.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        NodePath<T> other = (NodePath<T>) obj;
        if (node == null) {
            if (other.node != null)
                return false;
        } else if (!node.equals(other.node))
            return false;
        return true;
    }

}
