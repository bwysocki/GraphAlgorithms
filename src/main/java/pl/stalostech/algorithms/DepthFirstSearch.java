package pl.stalostech.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.stalostech.algorithms.model.NodePath;
import pl.stalostech.graph.domain.DFS;
import pl.stalostech.graph.repository.DFSRepository;

/**
 * The class represents DFS algorithm.
 * As a data-structure it uses stack.
 * @author Bartosz Wysocki
 */
@Component
public class DepthFirstSearch extends AbstractAlgorithm {

    @Autowired
    private DFSRepository repo;

    private final String graph =
        "MERGE (a:DFS:_DFS {name : 'A', x: 0,y : 0})\n" +
        "MERGE (b:DFS:_DFS {name : 'B', x: -3,y : -3})\n" +
        "MERGE (c:DFS:_DFS {name : 'C', x: 5,y : -3})\n" +
        "MERGE (d:DFS:_DFS {name : 'D', x: 1,y : -1})\n" +
        "MERGE (e:DFS:_DFS {name : 'E', x: -2,y : 3})\n" +
        "MERGE (f:DFS:_DFS {name : 'F', x: 3,y : -2})\n" +
        "MERGE (g:DFS:_DFS {name : 'G', x: 1,y : 2})\n" +
        "MERGE (h:DFS:_DFS {name : 'H', x: 6,y : -2})\n" +
        "CREATE UNIQUE b-[:PATH]->a-[:PATH]->b\n" +
        "CREATE UNIQUE d-[:PATH]->a-[:PATH]->d\n" +
        "CREATE UNIQUE g-[:PATH]->a-[:PATH]->g\n" +
        "CREATE UNIQUE e-[:PATH]->b-[:PATH]->e\n" +
        "CREATE UNIQUE f-[:PATH]->b-[:PATH]->f\n" +
        "CREATE UNIQUE h-[:PATH]->c-[:PATH]->h\n" +
        "CREATE UNIQUE f-[:PATH]->c-[:PATH]->f\n" +
        "CREATE UNIQUE f-[:PATH]->d-[:PATH]->f\n" +
        "CREATE UNIQUE g-[:PATH]->e-[:PATH]->g"
        ;

    private final String graphIndex = "CREATE INDEX ON :DFS(name)";

    private class DFSAware{

        private DFS dfs;

        private DFS parent;

        public DFSAware(DFS dfs, DFS parent){
            this.dfs = dfs;
            this.parent = parent;
        }

        public DFS getDfs() {
            return dfs;
        }

        public DFS getParent() {
            return parent;
        }

    }

    /**
     * Returns string representing the nodes visited by algorithm.
     * @param startNodeId - the value of 'name' property
     * @return Algorithm result as String
     */
    public String getInspectedNodesAsString(String startNodeId) {
        List<NodePath<DFS>> nodes = getInspectedNodes(startNodeId);

        StringBuilder response = new StringBuilder("");

        for (int i = 0; i < nodes.size(); i++) {
            DFS node = (DFS) nodes.get(i).getNode();
            response.append(node.getName());
            if (i != nodes.size() - 1) {
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
    public List<NodePath<DFS>> getInspectedNodes(String startNodeId) {

        List<NodePath<DFS>> response = new ArrayList<NodePath<DFS>>();

        DFS start = repo.findByName(startNodeId);
        response.add(new NodePath<DFS>(start, ""));

        Stack<DFSAware> childernOfNodes = new Stack<DFSAware>();
        childernOfNodes.push(new DFSAware(start, start));

        while (!childernOfNodes.isEmpty()) {
            DFSAware dfsAware = childernOfNodes.pop();
            DFS node = dfsAware.getDfs();
            DFS previous = dfsAware.getParent();
            NodePath<DFS> nodePath = new NodePath<DFS>(node, previous.getName() + node.getName());
            if (!response.contains(nodePath)) {
                response.add(nodePath);
            }
            Set<DFS> relationships = new TreeSet<DFS>(node.getConnections());
            for (DFS next : relationships) {
                if (!response.contains(new NodePath<DFS>(next, ""))) {
                    childernOfNodes.add(new DFSAware(next, node));
                }
            }
            previous = node;
        }
        return response;

    }

    @Override
    public String getGraphString() {
        return graph;
    }

    @Override
    public String getIndexString() {
        return graphIndex;
    }

}
