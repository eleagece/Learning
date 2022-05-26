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
public class StarshipControllerTest {
	
	@Autowired
    private WebApplicationContext webApplicationContext;
	private MockMvc mvc;

	@Test
	void personName_Lando_Calrissian() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		MvcResult mvcRes = mvc.perform(get("/starships/starships-info-by-person-name/Lando Calrissian"))
				.andExpect(status().isOk())
				.andReturn();
		assertTrue(mvcRes.getResponse().getContentAsString().contains("Millennium Falcon"));
		assertFalse(mvcRes.getResponse().getContentAsString().contains("Imperial shuttle"));
	}
	
	@Test
	void personName_Empty() throws Exception {
        this.mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mvc.perform(get("/starships/starships-info-by-person-name/"))
			.andExpect(status().isNotFound())
			.andExpect(status().reason("A person name should be passed"));
	}
}
