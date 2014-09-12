package pl.stalostech.rest.model.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.stalostech.graph.repository.DFSRepository;
import pl.stalostech.rest.model.ViewEdge;
import pl.stalostech.rest.model.ViewGraph;
import pl.stalostech.rest.model.ViewNode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml" })
public class DFSToViewGraphTest {

    @Autowired
    private DFSRepository DFSRepository;

    @Autowired
    private DFSToViewGraph transformer;

    @Test
    public void testTransformation() {
        ViewGraph r = transformer.transform(DFSRepository.findAll());

        //check nodes
        assertEquals(8, r.getNodes().size());

        ViewNode expectedNode = new ViewNode();
        expectedNode.setId("A");
        expectedNode.setLabel("Node A");
        expectedNode.setSize(3);
        expectedNode.setX(0);
        expectedNode.setY(0);

        assertTrue(r.getNodes().contains(expectedNode));

        //check edges
        assertEquals(9, r.getEdges().size());

        ViewEdge expectedEdge = new ViewEdge();
        expectedEdge.setId("BF");
        expectedEdge.setSize(3);
        expectedEdge.setSource("A");
        expectedEdge.setTarget("F");

        assertTrue(r.getEdges().contains(expectedEdge));
    }

}
