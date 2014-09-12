package pl.stalostech.graph.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * DB entity - representing DFS node.
 * @author Bartosz Wysocki
 */
@NodeEntity
public class DFS extends AbstractNode<DFS> implements Comparable<DFS>{

    @GraphId
    protected Long id;

    @Indexed
    protected String name;

    @Fetch
    @RelatedTo(type = "PATH")
    private Set<DFS> connections = new HashSet<DFS>();

    @Override
    public Set<DFS> getConnections() {
        return connections;
    }

    public void setConnections(Set<DFS> connections) {
        this.connections = connections;
    }

    @Override
    public int compareTo(DFS o) {
        return o.getName().compareTo(getName());
    }

}
