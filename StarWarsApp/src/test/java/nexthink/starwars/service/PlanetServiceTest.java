package nexthink.starwars.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.server.ResponseStatusException;

@SpringBootTest
public class PlanetServiceTest {

	@Autowired
	private PlanetService planetService;

	@Test
	void planetName_Tatooine() {
		List<String> residentsMockList = new ArrayList<String>();
		residentsMockList.add("Luke Skywalker");
		residentsMockList.add("C-3PO");
		residentsMockList.add("Darth Vader");
		residentsMockList.add("Owen Lars");
		residentsMockList.add("Beru Whitesun lars");
		residentsMockList.add("R5-D4");
		residentsMockList.add("Biggs Darklighter");
		residentsMockList.add("Anakin Skywalker");
		residentsMockList.add("Shmi Skywalker");
		residentsMockList.add("Cliegg Lars");
		List<String> residentsSwApiList = planetService.getResidentsNamesByPlanetName("Tatooine");
		boolean equalsLists = (residentsMockList.equals(residentsSwApiList));
		assertTrue(equalsLists);
	}

	@Test
	void planetName_taTooIne() {
		List<String> residentsMockList = new ArrayList<String>();
		residentsMockList.add("Luke Skywalker");
		residentsMockList.add("C-3PO");
		residentsMockList.add("Darth Vader");
		residentsMockList.add("Owen Lars");
		residentsMockList.add("Beru Whitesun lars");
		residentsMockList.add("R5-D4");
		residentsMockList.add("Biggs Darklighter");
		residentsMockList.add("Anakin Skywalker");
		residentsMockList.add("Shmi Skywalker");
		residentsMockList.add("Cliegg Lars");
		List<String> residentsSwApiList = planetService.getResidentsNamesByPlanetName("taTooIne");
		boolean equalsLists = (residentsMockList.equals(residentsSwApiList));
		assertTrue(equalsLists);
	}

	@Test
	void planetName_Mustafar() {
		List<String> residentsSwApiList = planetService.getResidentsNamesByPlanetName("Mustafar");
		assertEquals(residentsSwApiList.size(), 0);
	}

	@Test
	void planetName_ZzZ() {
		try {
			planetService.getResidentsNamesByPlanetName("ZzZ");
		} catch (ResponseStatusException e) {
			boolean equalsErrorMessage = e.getReason().equals("No planets with the name 'ZzZ' found");
			assertTrue(equalsErrorMessage);
		}
	}

	@Test
	void planetName_Na() {
		try {
			planetService.getResidentsNamesByPlanetName("Na");
		} catch (ResponseStatusException e) {
			boolean equalsErrorMessage = e.getReason().equals(
					"No planets with the name 'Na' found. You are likely to be looking for any of these: [Naboo, Nal Hutta]");
			assertTrue(equalsErrorMessage);
		}
	}

	@Test
	void planetName_Nab() {
		try {
			planetService.getResidentsNamesByPlanetName("Nab");
		} catch (ResponseStatusException e) {
			boolean equalsErrorMessage = e.getReason()
					.equals("No planets with the name 'Nab' found. You are likely to be looking for: Naboo");
			assertTrue(equalsErrorMessage);
		}
	}
}
