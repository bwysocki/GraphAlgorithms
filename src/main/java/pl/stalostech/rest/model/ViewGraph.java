package pl.stalostech.rest.model;

import java.util.List;

/**
 * Class representing graph sent to view.
 * @author Bartosz Wysocki
 */
public class ViewGraph {

    private List<ViewNode> nodes;

    private List<ViewEdge> edges;

    public List<ViewNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ViewNode> nodes) {
        this.nodes = nodes;
    }

    public List<ViewEdge> getEdges() {
        return edges;
    }

    public void setEdges(List<ViewEdge> edges) {
        this.edges = edges;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((edges == null) ? 0 : edges.hashCode());
        result = prime * result + ((nodes == null) ? 0 : nodes.hashCode());
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
        ViewGraph other = (ViewGraph) obj;
        if (edges == null) {
            if (other.edges != null)
                return false;
        } else if (!edges.equals(other.edges))
            return false;
        if (nodes == null) {
            if (other.nodes != null)
                return false;
        } else if (!nodes.equals(other.nodes))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ViewGraph [nodes=" + nodes + ", edges=" + edges + "]";
    }

}
