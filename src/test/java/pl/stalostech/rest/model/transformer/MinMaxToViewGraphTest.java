package pl.stalostech.rest.model.transformer;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.stalostech.graph.domain.MinMaxNode;
import pl.stalostech.graph.repository.MinMaxRepository;
import pl.stalostech.rest.model.ViewGraph;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:test-context.xml" })
public class MinMaxToViewGraphTest {

    @Autowired
    private MinMaxRepository minmaxRepository;

    @Autowired
    private MinMaxToViewGraph minMaxToViewGraphTransformer;

    @Test
    public void testTransformation() {
        MinMaxNode r = minmaxRepository.findByName("INIT");
        ViewGraph graph = minMaxToViewGraphTransformer.transform(r);
        String label = graph.getNodes().get(0).getLabel();

        assertEquals(1, graph.getNodes().size());
        assertTrue(label.contains("o"));
        assertTrue(label.contains("x"));
        assertTrue(label.contains("|"));
        assertTrue(label.contains("-"));

        assertEquals(0, graph.getEdges().size());
    }

}
