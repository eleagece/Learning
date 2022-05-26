package nexthink.starwars.controller;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
public class PlanetControllerTest {

	@Autowired
    private WebApplicationContext webApplicationContext;
	private MockMvc mvc;

	@Test
	void planetName_Endor() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcRes = mvc.perform(get("/planets/residents-names-by-planet-name/Endor"))
				.andExpect(status().isOk())
				.andReturn();
		assertTrue(mvcRes.getResponse().getContentAsString().contains("Wicket Systri Warrick"));
		assertFalse(mvcRes.getResponse().getContentAsString().contains("Luke Skywalker"));
	}
	
	@Test
	void planetName_Empty() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        this.mvc.perform(get("/planets/residents-names-by-planet-name/"))
			.andExpect(status().isNotFound())
			.andExpect(status().reason("A planet name should be passed"));
	}
}
