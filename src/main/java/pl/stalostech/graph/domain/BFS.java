package pl.stalostech.graph.domain;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * DB entity - representing BFS node.
 * @author Bartosz Wysocki
 */
@NodeEntity
public class BFS extends AbstractNode<BFS> implements Comparable<BFS>{

    @GraphId
    protected Long id;

    @Indexed
    protected String name;

    @Fetch
    @RelatedTo(type = "PATH")
    private Set<BFS> connections = new HashSet<BFS>();

    @Override
    public Set<BFS> getConnections() {
        return connections;
    }

    public void setConnections(Set<BFS> connections) {
        this.connections = connections;
    }

    @Override
    public int compareTo(BFS o) {
        return getName().compareTo(o.getName());
    }

}
