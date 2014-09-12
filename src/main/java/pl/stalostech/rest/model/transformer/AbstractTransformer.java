package pl.stalostech.rest.model.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import pl.stalostech.graph.domain.AbstractNode;
import pl.stalostech.rest.model.ViewEdge;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.ViewNode;
import pl.stalostech.utils.GraphUtils;

/**
 * Contains common methods for all transformers.
 * @author Bartosz Wysocki
 * @param <T> - Type of node
 * @param <S> - Type of node with connections
 */
public abstract class AbstractTransformer<T extends AbstractNode<S>, S> {

    @Autowired
    protected GraphUtils graphUtils;

    public ViewGraph transform(Iterable<T> nodes) {

        List<ViewNode> vNodes = new ArrayList<ViewNode>();
        Set<ViewEdge> vEdges = new HashSet<ViewEdge>();

        for (T node : nodes) {
            vNodes.add(transformToNode(node));
            addToVEdges(node, vEdges);
        }

        ViewGraph graph = new ViewGraph();
        graph.setEdges(new ArrayList<ViewEdge>(vEdges));
        graph.setNodes(vNodes);

        return graph;
    }

    public ViewGraph transform(T node) {

        List<ViewNode> vNodes = new ArrayList<ViewNode>();
        Set<ViewEdge> vEdges = new HashSet<ViewEdge>();

        vNodes.add(transformToNode(node));
        addToVEdges(node, vEdges);

        ViewGraph graph = new ViewGraph();
        graph.setEdges(new ArrayList<ViewEdge>(vEdges));
        graph.setNodes(vNodes);

        return graph;
    }

    protected abstract void addToVEdges(T node, Set<ViewEdge> vEdges);

    protected abstract ViewNode transformToNode(T node);
}
