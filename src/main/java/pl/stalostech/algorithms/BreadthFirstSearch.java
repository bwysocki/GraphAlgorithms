package pl.stalostech.algorithms;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.stereotype.Component;
import pl.stalostech.graph.domain.BFS;
import pl.stalostech.graph.repository.BFSRepository;

/**
 * The class represents BFS algorithm.
 * As a data-structure it uses queue.
 * @author Bartosz Wysocki
 */
@Component
public class BreadthFirstSearch {

    @Autowired
    private Neo4jTemplate neo;

    @Autowired
    private BFSRepository repo;

    private final String graph = "CREATE\n" +
        "(a:BFS:_BFS {name : 'A', x: 0,y : 0}),\n" +
        "(b:BFS:_BFS {name : 'B', x: -3,y : -3}),\n" +
        "(c:BFS:_BFS {name : 'C', x: 5,y : -3}),\n" +
        "(d:BFS:_BFS {name : 'D', x: 1,y : -1}),\n" +
        "(e:BFS:_BFS {name : 'E', x: -2,y : 3}),\n" +
        "(f:BFS:_BFS {name : 'F', x: 3,y : -2}),\n" +
        "(g:BFS:_BFS {name : 'G', x: 1,y : 2}),\n" +
        "(h:BFS:_BFS {name : 'H', x: 6,y : -2}),\n" +
        "b-[:PATH]->a-[:PATH]->b,\n" +
        "d-[:PATH]->a-[:PATH]->d,\n" +
        "g-[:PATH]->a-[:PATH]->g,\n" +
        "e-[:PATH]->b-[:PATH]->e,\n" +
        "f-[:PATH]->b-[:PATH]->f,\n" +
        "h-[:PATH]->c-[:PATH]->h,\n" +
        "f-[:PATH]->c-[:PATH]->f,\n" +
        "f-[:PATH]->d-[:PATH]->f,\n" +
        "g-[:PATH]->e-[:PATH]->g"
        ;

    private final String graphIndex = "CREATE INDEX ON :BFS(name)";

    private final String deleteGraph = "MATCH (n:BFS) OPTIONAL MATCH (n)-[r]-() DELETE n,r";

    @PostConstruct
    public void init() {
        neo.query(graph, null);
        neo.query(graphIndex, null);
    }

    @PreDestroy
    public void clean() {
        neo.query(deleteGraph, null);
    }

    /**
     * Returns string representing the nodes visited by algorithm.
     * @param startNodeId - the value of 'name' property
     * @return Algorithm result as String
     */
    public String getInspectedNodesAsString(String startNodeId) {
        Queue<BFS> nodes = getInspectedNodesAsQueue(startNodeId);

        StringBuilder response = new StringBuilder("");

        while (!nodes.isEmpty()) {
            BFS node = nodes.remove();
            response.append(node.getName());
            if (!nodes.isEmpty()) {
                response.append("-");
            }
        }
        return response.toString();
    }

    /**
     * Returns nodes in visit order.
     * @param startNodeId - the value of 'name' property
     * @return visited nodes
     */
    public Queue<BFS> getInspectedNodesAsQueue(String startNodeId) {

        Queue<BFS> response = new LinkedList<BFS>();

        BFS start = repo.findByName(startNodeId);
        response.add(start);

        Queue<BFS> childernOfNodes = new LinkedList<BFS>();
        childernOfNodes.add(start);

        while (!childernOfNodes.isEmpty()) {
            BFS node = childernOfNodes.remove();
            Set<BFS> relationships = new TreeSet<BFS>(node.getConnections());
            for (BFS next : relationships) {
                if (!response.contains(next)) {
                    childernOfNodes.add(next);
                    response.add(next);
                }
            }
        }
        return response;
    }


}
