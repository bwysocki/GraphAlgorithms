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
import pl.stalostech.algorithms.DepthFirstSearch;
import pl.stalostech.algorithms.model.NodePath;
import pl.stalostech.graph.domain.DFS;
import pl.stalostech.graph.repository.DFSRepository;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.transformer.DFSToViewGraph;
import pl.stalostech.utils.GraphUtils;

/**
 * Breadth First Search
 * @author Bartosz Wysocki
 */
@RestController
public class DFSController {

    @Autowired
    private GraphUtils graphUtils;

    @Autowired
    private DepthFirstSearch dfs;

    @Autowired
    private DFSRepository dfsRepository;

    @Autowired
    private DFSToViewGraph dfsToViewGraphTransformer;

    @RequestMapping(value = "/dfs", method = RequestMethod.GET)
    public ViewGraph getInitGraph() {
        return dfsToViewGraphTransformer.transform(dfsRepository.findAll());
    }

    @RequestMapping(value = "/dfsSolution/{startNode}", method = RequestMethod.GET)
    public List<Map<String, String>> getSolution(@PathVariable String startNode) {
        List<Map<String, String>> r = new ArrayList<Map<String, String>>();

        List<NodePath<DFS>> solution = dfs.getInspectedNodes(startNode);
        for (NodePath<DFS> DFSNodePath : solution) {
            Map<String, String> m = new HashMap<String, String>();
            m.put("NODE", DFSNodePath.getNode().getName());
            m.put("PATH", graphUtils.createUndirectedEdgeId(DFSNodePath.getPath()));
            r.add(m);
        }

        return r;
    }

}
