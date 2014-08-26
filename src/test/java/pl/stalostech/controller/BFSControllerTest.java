package pl.stalostech.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

/**
 * Tests BFS controller
 * @author Bartosz Wysocki
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({"classpath:test-context.xml" })
public class BFSControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext wac;

    @Before
    public void init() {
        mockMvc = webAppContextSetup(wac).alwaysExpect(status().isOk()).build();
    }

    @Test
    public void testGetInitGraph() throws Exception {
        mockMvc.perform(get("/bfs").header("Accept", "application/json")).andExpect(content().contentType("application/json;charset=UTF-8"));
    }

    @Test
    public void testGetSolution() throws Exception {
        mockMvc.perform(get("/bfsSolution/A").header("Accept", "application/json"))
            .andExpect(content().contentType("application/json;charset=UTF-8"))
            .andExpect(content().string("[\"A\",\"B\",\"D\",\"G\",\"E\",\"F\",\"C\",\"H\"]"));
    }

}
