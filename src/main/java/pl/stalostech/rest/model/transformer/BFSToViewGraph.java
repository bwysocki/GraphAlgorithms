package pl.stalostech.rest.model.transformer;

import java.util.Set;

import org.springframework.stereotype.Component;
import pl.stalostech.graph.domain.BFS;
import pl.stalostech.rest.model.ViewEdge;
import pl.stalostech.rest.model.ViewNode;

/**
 * Transforms BFS domain into view model.
 * @author q1wy
 */
@Component
public class BFSToViewGraph extends AbstractTransformer<BFS, BFS> {

    @Override
    protected void addToVEdges(BFS node, Set<ViewEdge> vEdges) {
        for (BFS connected : node.getConnections()) {
            ViewEdge r = new ViewEdge();
            r.setId(graphUtils.createUndirectedEdgeId(node.getName(), connected.getName()));
            r.setSize(3);
            r.setSource(node.getName());
            r.setTarget(connected.getName());
            vEdges.add(r);
        }
    }

    @Override
    protected ViewNode transformToNode(BFS node) {
        ViewNode r = new ViewNode();
        r.setId(node.getName());
        r.setLabel("Node " + node.getName());
        r.setSize(3);
        r.setX(node.getX());
        r.setY(node.getY());
        return r;
    }

}
