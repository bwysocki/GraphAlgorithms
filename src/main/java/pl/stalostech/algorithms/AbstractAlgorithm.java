package pl.stalostech.algorithms;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;

/**
 * Parent for all algorithms
 * @author Bartosz Wysocki
 */
public abstract class AbstractAlgorithm {

    @Autowired
    private Neo4jTemplate neo;

    public abstract String getGraphString();

    public abstract String getIndexString();

    @PostConstruct
    public void init() {
        neo.query(getGraphString(), null);
        neo.query(getIndexString(), null);
    }

}
