package pl.stalostech.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;

/**
 * Tests BFS controller
 * @author Bartosz Wysocki
 */
public class BFSControllerTest extends AbstractControllerTest {

    @Test
    public void testGetInitGraph() throws Exception {
        mockMvc.perform(get("/bfs").header("Accept", "application/json")).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testGetSolution() throws Exception {
        mockMvc
            .perform(get("/bfsSolution/A").header("Accept", "application/json"))
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(
                content().string(
                    "[{\"NODE\":\"A\",\"PATH\":\"\"},{\"NODE\":\"B\",\"PATH\":"
                        + "\"AB\"},{\"NODE\":\"D\",\"PATH\":\"AD\"},{\"NODE\":\"G\",\"PATH\":\"AG\"},{\"NOD"
                        + "E\":\"E\",\"PATH\":\"BE\"},{\"NODE\":\"F\",\"PATH\":\"BF\"},{\"NODE\":\"C\",\"PAT"
                        + "H\":\"CF\"},{\"NODE\":\"H\",\"PATH\":\"CH\"}]"));
    }

}
