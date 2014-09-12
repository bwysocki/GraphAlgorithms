package pl.stalostech.algorithms;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml" })
public class DepthFirstSearchTest {

    @Autowired
    private DepthFirstSearch dfs;

    @Test
    public void testDFS() {
        assertEquals("A-B-E-G-F-C-H-D", dfs.getInspectedNodesAsString("A"));
        assertEquals("F-B-A-D-G-E-C-H", dfs.getInspectedNodesAsString("F"));
        assertEquals("G-A-B-E-F-C-H-D", dfs.getInspectedNodesAsString("G"));
    }

}
