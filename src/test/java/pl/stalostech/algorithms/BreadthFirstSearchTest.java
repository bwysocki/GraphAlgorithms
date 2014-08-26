package pl.stalostech.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml" })
public class BreadthFirstSearchTest {

    @Autowired
    private BreadthFirstSearch bfs;

    @Test
    public void testBFS() {
        assertEquals("A-B-D-G-E-F-C-H", bfs.getInspectedNodesAsString("A"));
        assertEquals("F-B-C-D-A-E-H-G", bfs.getInspectedNodesAsString("F"));
        assertEquals("G-A-E-B-D-F-C-H", bfs.getInspectedNodesAsString("G"));
    }

}
