package pl.stalostech.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Test;

/**
 * Tests DFS controller
 * @author Bartosz Wysocki
 */
public class MinMaxControllerTest extends AbstractControllerTest {

    @Test
    public void testGetInitGraph() throws Exception {
        mockMvc.perform(get("/minmax").header("Accept", "application/json")).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

}
