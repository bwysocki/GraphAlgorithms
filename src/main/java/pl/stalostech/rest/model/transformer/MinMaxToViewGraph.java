package pl.stalostech.rest.model.transformer;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.springframework.stereotype.Component;
import pl.stalostech.graph.domain.MinMaxNode;
import pl.stalostech.rest.model.ViewEdge;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.ViewNode;

/**
 * Transforms DFS domain into view model.
 * @author q1wy
 */
@Component
public class MinMaxToViewGraph extends AbstractTransformer<MinMaxNode, MinMaxNode> {

    @Override
    public ViewGraph transform(Iterable<MinMaxNode> nodes) {
        return removeNotVisibleEdges(super.transform(nodes));
    }

    @Override
    public ViewGraph transform(MinMaxNode node) {
        return removeNotVisibleEdges(super.transform(node));
    }

    @Override
    protected void addToVEdges(MinMaxNode node, Set<ViewEdge> vEdges) {
        for (MinMaxNode connected : node.getConnections()) {
            ViewEdge r = new ViewEdge();
            r.setId(graphUtils.createUndirectedEdgeId(node.getName(), connected.getName()));
            r.setSize(3);
            r.setSource(node.getName());
            r.setTarget(connected.getName());
            vEdges.add(r);
        }
    }

    @Override
    protected ViewNode transformToNode(MinMaxNode node) {
        ViewNode r = new ViewNode();
        r.setId(node.getName());

        StringBuilder label = new StringBuilder();
        label.append(ox(node.getTl()) + "|" + ox(node.getT()) + "|" + ox(node.getTr()) + "_NL_");
        label.append("----------" + "_NL_");
        label.append(ox(node.getMl()) + "|" + ox(node.getM()) + "|" + ox(node.getMr()) + "_NL_");
        label.append("----------" + "_NL_");
        label.append(ox(node.getBl()) + "|" + ox(node.getB()) + "|" + ox(node.getBr()));
        r.setLabel(label.toString());
        r.setSize(3);
        r.setX(node.getX());
        r.setY(node.getY());
        r.setScore(node.getScore());
        return r;
    }

    private String ox(String text) {
        return (text == null || text.isEmpty()) ? "_SP__SP__SP__SP_" : " " + text + " ";
    }

    private ViewGraph removeNotVisibleEdges(ViewGraph graph) {
        Set<String> visibleNodes = new HashSet<String>();
        for (ViewNode node : graph.getNodes()) {
            visibleNodes.add(node.getId());
        }

        Iterator<ViewEdge> edgesIterator = graph.getEdges().iterator();
        while (edgesIterator.hasNext()) {
            ViewEdge edge = edgesIterator.next();
            if (!visibleNodes.contains(edge.getTarget())) {
                edgesIterator.remove();
            }
        }
        return graph;
    }

}
