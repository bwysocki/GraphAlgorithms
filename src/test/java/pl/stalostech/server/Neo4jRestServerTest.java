package pl.stalostech.server;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.graphdb.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Tests if Neo4j server runs correctly
 * @author Bartosz Wysocki
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml" })
public class Neo4jRestServerTest {

    public static final String SERVER_ROOT_URI = "http://localhost:7474/";
    private static final String UNIQUE = "AgsS21123HGSQAF";

    @Autowired
    private Neo4jTemplate neo;

    /**
     * Tests if it is possible to connect to SERVER with neo4j api.
     */
    @Test
    public void testServerConnection() {
        WebResource resource = Client.create().resource(SERVER_ROOT_URI);
        ClientResponse response = resource.get(ClientResponse.class);
        assertEquals(200, response.getStatus());
    }

    /**
     * Tests if it is possible to connect to SERVER and perform some
     * basic operations with spring data api.
     */
    @Test
    public void testSimpleCRUDOperations() {

        List<String> label = Arrays.asList(UNIQUE);

        //create example nodes
        Node mark = neo.createNode(map("name", "Mark"), label);
        Node thomas = neo.createNode(map("name", "Thomas"), label);
        neo.createRelationshipBetween(mark, thomas, "WORKS_WITH", map("project", "graphAlgorithms"));

        String queryForMark = "MATCH (m:" + UNIQUE + ")-[:WORKS_WITH]->(t) WHERE t.name = {name} RETURN m.name";

        //assert query selection
        assertEquals("Mark", neo.query(queryForMark, map("name", "Thomas")).to(String.class).single());

        //delete nodes
        neo.deleteRelationshipBetween(mark, thomas, "WORKS_WITH");
        neo.delete(mark);
        neo.delete(thomas);

        //assert that nothing is returned
        assertNull(neo.query(queryForMark, map("name", "Thomas")).to(String.class).singleOrNull());

    }

    private Map<String, Object> map(String key, String value) {
        Map<String, Object> r = new HashMap<String, Object>();
        r.put(key, value);
        return r;
    }

}
