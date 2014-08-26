package pl.stalostech.utils;

import java.util.Arrays;

import org.springframework.stereotype.Component;

/**
 * Singleton containing graph utils.
 * @author Bartosz Wysocki
 */
@Component
public class GraphUtils {

    /**
     * In case of undirected graph, the edge from A to B is equivalent to the edge from B to A.
     */
    public String createUndirectedEdgeId(String sourceId, String targetId){
        String id = sourceId + targetId;
        char[] chars = id.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

}
