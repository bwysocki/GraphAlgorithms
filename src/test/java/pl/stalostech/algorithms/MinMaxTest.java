package pl.stalostech.algorithms;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import pl.stalostech.algorithms.MinMax.Player;
import pl.stalostech.graph.domain.MinMaxNode;
import pl.stalostech.graph.repository.MinMaxRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-context.xml" })
public class MinMaxTest {

    @Autowired
    private MinMax minmax;

    @Autowired
    private MinMaxRepository minmaxRepository;

    @Autowired
    private Neo4jTemplate neo;

    @Test
    public void testGetScoreForNode() {
        assertEquals(0, minmax.getScoreForNode(minmaxRepository.findByName("INIT"), 0));
        assertEquals(0, minmax.getScoreForNode(minmaxRepository.findByName("A"), 0));
        assertEquals(10, minmax.getScoreForNode(minmaxRepository.findByName("B"), 0));
        assertEquals(30, minmax.getScoreForNode(minmaxRepository.findByName("B"), 2));
        assertEquals(2, minmax.getScoreForNode(minmaxRepository.findByName("C"), 0));
        assertEquals(-10, minmax.getScoreForNode(minmaxRepository.findByName("D"), 0));
        assertEquals(-20, minmax.getScoreForNode(minmaxRepository.findByName("D"), 1));
        assertEquals(0, minmax.getScoreForNode(minmaxRepository.findByName("E"), 0));
        assertEquals(0, minmax.getScoreForNode(minmaxRepository.findByName("F"), 0));
        assertEquals(-10, minmax.getScoreForNode(minmaxRepository.findByName("G"), 0));
        assertEquals(10, minmax.getScoreForNode(minmaxRepository.findByName("H"), 0));
        assertEquals(10, minmax.getScoreForNode(minmaxRepository.findByName("I"), 0));
    }

    @Test
    public void testMinmax() {

        List<MinMaxNode> visitedNodes;

        assertEquals(0, minmax.minmax(minmaxRepository.findByName("F"), 0, Player.COMPUTER_X, visitedNodes = new ArrayList<MinMaxNode>()));
        assertEquals(1, visitedNodes.size());

        assertEquals(10, minmax.minmax(minmaxRepository.findByName("INIT"), 1, Player.COMPUTER_X, visitedNodes = new ArrayList<MinMaxNode>()));
        assertEquals(4, visitedNodes.size());

        assertEquals(20, minmax.minmax(minmaxRepository.findByName("INIT"), 2, Player.COMPUTER_X, visitedNodes = new ArrayList<MinMaxNode>()));
        assertEquals(8, visitedNodes.size());

    }

}
