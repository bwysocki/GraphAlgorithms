package pl.stalostech.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;

/**
 * Tests DFS controller
 * @author Bartosz Wysocki
 */
public class DFSControllerTest extends AbstractControllerTest {

    @Test
    public void testGetInitGraph() throws Exception {
        mockMvc.perform(get("/dfs").header("Accept", "application/json")).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testGetSolution() throws Exception {
        mockMvc
            .perform(get("/dfsSolution/A").header("Accept", "application/json"))
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(
                content().string(
                    "[{\"NODE\":\"A\",\"PATH\":\"\"},{\"NODE\"" + ":\"B\",\"PATH\":\"AB\"},{\"NODE\":\"E\",\"PATH\":\"BE\"},{\"N"
                        + "ODE\":\"G\",\"PATH\":\"EG\"},{\"NODE\":\"F\",\"PATH\":\"BF\"}"
                        + ",{\"NODE\":\"C\",\"PATH\":\"CF\"},{\"NODE\":\"H\",\"PATH\"" + ":\"CH\"},{\"NODE\":\"D\",\"PATH\":\"DF\"}]"));
    }

}
