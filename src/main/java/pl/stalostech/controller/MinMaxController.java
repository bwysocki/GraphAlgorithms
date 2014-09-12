package pl.stalostech.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.stalostech.algorithms.MinMax;
import pl.stalostech.algorithms.MinMax.Player;
import pl.stalostech.graph.domain.MinMaxNode;
import pl.stalostech.graph.repository.MinMaxRepository;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.transformer.MinMaxToViewGraph;
import pl.stalostech.utils.GraphUtils;

/**
 * Breadth First Search
 * @author Bartosz Wysocki
 */
@RestController
public class MinMaxController {

    @Autowired
    private GraphUtils graphUtils;

    @Autowired
    private MinMax minmax;

    @Autowired
    private MinMaxRepository minmaxRepository;

    @Autowired
    private MinMaxToViewGraph minMaxToViewGraphTransformer;

    @RequestMapping(value = "/minmax", method = RequestMethod.GET)
    public ViewGraph getInitGraph() {
        return minMaxToViewGraphTransformer.transform(minmaxRepository.findByName("INIT"));
    }

    @RequestMapping(value = "/minmax/{depth}", method = RequestMethod.GET)
    public ViewGraph getMinMaxSteps(@PathVariable Integer depth) {
        List<MinMaxNode> visitedNodes = new ArrayList<MinMaxNode>();
        minmax.minmax(minmaxRepository.findByName("INIT"), depth, Player.COMPUTER_X, visitedNodes);
        return minMaxToViewGraphTransformer.transform(visitedNodes);
    }

}
