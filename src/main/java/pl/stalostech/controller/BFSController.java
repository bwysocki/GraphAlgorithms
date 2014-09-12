package pl.stalostech.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.stalostech.algorithms.BreadthFirstSearch;
import pl.stalostech.algorithms.model.NodePath;
import pl.stalostech.graph.domain.BFS;
import pl.stalostech.graph.repository.BFSRepository;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.transformer.BFSToViewGraph;
import pl.stalostech.utils.GraphUtils;

/**
 * Breadth First Search
 * @author Bartosz Wysocki
 */
@RestController
public class BFSController {

    @Autowired
    private GraphUtils graphUtils;

    @Autowired
    private BreadthFirstSearch bfs;

    @Autowired
    private BFSRepository bfsRepository;

    @Autowired
    private BFSToViewGraph bfsToViewGraphTransformer;

    @RequestMapping(value = "/bfs", method = RequestMethod.GET)
    public ViewGraph getInitGraph() {
        return bfsToViewGraphTransformer.transform(bfsRepository.findAll());
    }

    @RequestMapping(value = "/bfsSolution/{startNode}", method = RequestMethod.GET)
    public List<Map<String, String>> getSolution(@PathVariable String startNode) {
        List<Map<String, String>> r = new ArrayList<Map<String, String>>();

        List<NodePath<BFS>> solution = bfs.getInspectedNodes(startNode);
        for (NodePath<BFS> bfsNodePath : solution){
            Map<String, String> m = new HashMap<String, String>();
            m.put("NODE", bfsNodePath.getNode().getName());
            m.put("PATH", graphUtils.createUndirectedEdgeId(bfsNodePath.getPath()));
            r.add(m);
        }

        return r;
    }

}
