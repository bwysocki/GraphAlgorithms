package pl.stalostech.algorithms;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import pl.stalostech.graph.domain.MinMaxNode;

/**
 * This class provides an implementation of a general minimax search.
 * o|_|x
 * x|_|_
 * x|o|o
 *
 * @author Bartosz Wysocki
 */
@Component
public class MinMax extends AbstractAlgorithm {

    public enum Player {
        COMPUTER_X, USER_O
    }

    private enum Score {
        X_WIN(10), O_WIN(-10), DRAW(0), X_HAS_2(2), O_HAS_2(-2);

        private final int value;

        private Score(int value) {
            this.value = value;
        }

        public int getVal() {
            return value;
        }
    }

    private final String graph =
        "MERGE (init:MINMAX:_MINMAX {name : 'INIT', x: 3, y : 0, tr : 'x', ml : 'x', bl:'x',tl:'o',b:'o',br:'o'})\n" +
        "MERGE (a:MINMAX:_MINMAX {name : 'A', x: 1, y : 1, tr :'x',ml:'x',bl:'x',t:'x',tl:'o',b:'o',br:'o'})\n" +
        "MERGE (b:MINMAX:_MINMAX {name : 'B', x: 2, y : 1, tr :'x',ml:'x',bl:'x',m:'x',tl:'o',b:'o',br :'o'})\n" +
        "MERGE (c:MINMAX:_MINMAX {name : 'C', x: 3, y : 1, tr :'x',ml:'x',bl:'x',mr:'x',tl: 'o', b:'o',br:'o'})\n" +
        "MERGE (d:MINMAX:_MINMAX {name : 'D', x: 1, y : 2, tr :'x',ml:'x',bl:'x',t:'x',m :'o',tl:'o',b:'o',br:'o'})\n" +
        "MERGE (e:MINMAX:_MINMAX {name : 'E', x: 2, y : 2, tr :'x',ml:'x',bl:'x',t:'x',mr:'o',tl:'o',b:'o',br:'o'})\n" +
        "MERGE (f:MINMAX:_MINMAX {name : 'F', x: 3, y : 2, tr :'x',ml:'x',bl:'x',mr:'x',t:'o',tl:'o', b:'o',br:'o'})\n" +
        "MERGE (g:MINMAX:_MINMAX {name : 'G', x: 4, y : 2, tr :'x',ml:'x',bl:'x',mr:'x',m:'o',tl:'o',b:'o',br:'o'})\n" +
        "MERGE (h:MINMAX:_MINMAX {name : 'H', x: 1, y : 3, tr :'x',ml:'x',bl:'x',t:'x',m:'x',mr:'o',tl:'o', b:'o',br:'o'})\n" +
        "MERGE (i:MINMAX:_MINMAX {name : 'I', x: 2, y : 3, tr :'x',ml:'x',bl:'x',mr:'x',m:'x',t:'o',tl:'o', b:'o',br:'o'})\n" +
        "CREATE UNIQUE init-[:PATH]->a\n" +
        "CREATE UNIQUE init-[:PATH]->b\n" +
        "CREATE UNIQUE init-[:PATH]->c\n" +
        "CREATE UNIQUE a-[:PATH]->d\n" +
        "CREATE UNIQUE a-[:PATH]->e\n" +
        "CREATE UNIQUE c-[:PATH]->f\n" +
        "CREATE UNIQUE c-[:PATH]->g\n" +
        "CREATE UNIQUE e-[:PATH]->h\n" +
        "CREATE UNIQUE f-[:PATH]->i"
        ;

    private final String graphIndex = "CREATE INDEX ON :MINMAX(name)";

    @Override
    public String getGraphString() {
        return graph;
    }

    @Override
    public String getIndexString() {
        return graphIndex;
    }

    /**
     * Gets score for node.
     * @param node
     * @param depth
     * @return score
     */
    public int getScoreForNode(MinMaxNode node, int depth) {

        String xWins = "xxx";
        String oWins = "ooo";
        String xHasTwo = "xx";
        String oHasTwo = "oo";

        int occurancesX, occurancesO;
        if (getOccurances(node, xWins) > 0) {
            return Score.X_WIN.getVal() * (depth + 1);
        } else if (getOccurances(node, oWins) > 0) {
            return Score.O_WIN.getVal() * (depth + 1);
        } else if ((occurancesX = getOccurances(node, xHasTwo)) > 0 | (occurancesO = getOccurances(node, oHasTwo)) > 0) {

            int score = Score.DRAW.getVal();
            if (occurancesX > 0){
                score += Score.X_HAS_2.getVal() * occurancesX;
            }
            if (occurancesO > 0){
                score += Score.O_HAS_2.getVal() * occurancesO;
            }

            return score;
        } else {
            return Score.DRAW.getVal();
        }
    }

    /**
     * Depth limited minmax algorithm
     * @param node - current node
     * @param depth - depth to search
     * @param player - current player
     * @param visitedNodes - visited nodes
     */
    public int minmax(MinMaxNode node, int depth, Player player, List<MinMaxNode> visitedNodes) {
        return minmax(node, depth, player, visitedNodes, 0);
    }

    private int minmax(MinMaxNode node, int depth, Player player, List<MinMaxNode> visitedNodes, int score) {

        node.setScore(getScoreForNode(node, depth));
        visitedNodes.add(node);

        if (depth == 0 || node.getConnections().isEmpty()) {
            return score + getScoreForNode(node, depth);
        }

        Set<MinMaxNode> children = node.getConnections();
        if (player == Player.COMPUTER_X) {
            int bestVal = Integer.MIN_VALUE;
            for (MinMaxNode child : children) {
                int val = minmax(child, depth - 1, Player.USER_O, visitedNodes, score + getScoreForNode(node, depth));
                if (val > bestVal)
                    bestVal = val;
            }
            return bestVal;
        } else {
            int bestVal = Integer.MAX_VALUE;
            for (MinMaxNode child : children) {
                int val = minmax(child, depth - 1, Player.COMPUTER_X, visitedNodes, score + getScoreForNode(node, depth));
                if (val < bestVal)
                    bestVal = val;
            }
            return bestVal;
        }

    }

    private int getOccurances(MinMaxNode node, String pattern) {

        List<String> combinations = new ArrayList<String>();

        combinations.add(node.getTl() + node.getT() + node.getTr()); //top
        combinations.add(node.getBl() + node.getB() + node.getBr()); //bottom
        combinations.add(node.getTl() + node.getMl() + node.getBl()); //left
        combinations.add(node.getTr() + node.getMr() + node.getBr()); //right
        combinations.add(node.getT() + node.getM() + node.getB()); //middle_1
        combinations.add(node.getMl() + node.getM() + node.getMr()); //middle_2
        combinations.add(node.getTr() + node.getM() + node.getBl()); //diagonal_1
        combinations.add(node.getTl() + node.getM() + node.getBr()); //diagonal_2

        int r = 0;

        for (String combination : combinations) {
            if (combination.equals(pattern)) {
                r++;
            }
        }
        return r;
    }

}
