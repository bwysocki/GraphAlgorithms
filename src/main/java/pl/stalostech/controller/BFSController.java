package pl.stalostech.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.stalostech.algorithms.BreadthFirstSearch;
import pl.stalostech.graph.repository.BFSRepository;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.transformer.BFSToViewGraph;

/**
 * Breadth First Search
 * @author Bartosz Wysocki
 */
@RestController
public class BFSController {

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
    public List<String> getSolution(@PathVariable String startNode) {
        return new ArrayList<String>(Arrays.asList(bfs.getInspectedNodesAsString(startNode).split("-")));
    }

}
