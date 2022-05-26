package nexthink.starwars.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

import nexthink.starwars.data.result.Starship;

@SpringBootTest
public class StarshipServiceTest {

	@Autowired
	private StarshipService starshipService;

	@Test
	void personName_Han_Solo() {
		String name = "Millennium Falcon";
		String model = "YT-1300 light freighter";
		String manufacturer = "Corellian Engineering Corporation";
		Starship millenniumFalcoSwApiResult = starshipService.getStarshipsInfoByPersonName("Han Solo").get(0);
		boolean isMillenniumFalcon = false;
		if (name.equals(millenniumFalcoSwApiResult.getName()) &&
			model.equals(millenniumFalcoSwApiResult.getModel()) &&
			manufacturer.equals(millenniumFalcoSwApiResult.getManufacturer())) {
			isMillenniumFalcon = true;
		}
		assertTrue(isMillenniumFalcon);
	}

	@Test
	void personName_HAn_sOLo() {
		String name = "Millennium Falcon";
		String model = "YT-1300 light freighter";
		String manufacturer = "Corellian Engineering Corporation";
		Starship millenniumFalcoSwApiResult = starshipService.getStarshipsInfoByPersonName("HAn sOLo").get(0);
		boolean isMillenniumFalcon = false;
		if (name.equals(millenniumFalcoSwApiResult.getName()) &&
			model.equals(millenniumFalcoSwApiResult.getModel()) &&
			manufacturer.equals(millenniumFalcoSwApiResult.getManufacturer())) {
			isMillenniumFalcon = true;
		}
		assertTrue(isMillenniumFalcon);
	}

	@Test
	void personName_Yoda() {
		List<Starship> yodaStarshipsSwApiResult = starshipService.getStarshipsInfoByPersonName("Yoda");
		assertEquals(yodaStarshipsSwApiResult.size(), 0);
	}

	@Test
	void personName_ZzZ() {
		try {
			starshipService.getStarshipsInfoByPersonName("ZzZ");
		} catch (ResponseStatusException e) {
			boolean equalsErrorMessage = e.getReason().equals("No people with the name 'ZzZ' found");
			assertTrue(equalsErrorMessage);
		}
	}

	@Test
	void personName_Lu() {
		try {
			starshipService.getStarshipsInfoByPersonName("Lu");
		} catch (ResponseStatusException e) {
			boolean equalsErrorMessage = e.getReason()
					.equals("No people with the name 'Lu' found. You are likely to be looking for any of these: [Luke Skywalker, Luminara Unduli]");
			assertTrue(equalsErrorMessage);
		}
	}

	@Test
	void personName_Luk() {
		try {
			starshipService.getStarshipsInfoByPersonName("Luk");
		} catch (ResponseStatusException e) {
			boolean equalsErrorMessage = e.getReason()
					.equals("No people with the name 'Luk' found. You are likely to be looking for: Luke Skywalker");
			assertTrue(equalsErrorMessage);
		}
	}

}
