package nexthink.starwars.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nexthink.starwars.client.SwApiClient;
import nexthink.starwars.data.list.Planets;
import nexthink.starwars.data.result.Planet;
import nexthink.starwars.utils.Utils;

@Service
public class PlanetService {

	@Autowired
	private SwApiClient swApiClient;

	/**
	 * Search in 'https://swapi.dev/api/' the names of the people who reside in the
	 * planet given in the String 'planetName'.
	 * 
	 * @param planetName - the searched planet.
	 * @return a List of String with the names of the planet's residents.
	 */
	public List<String> getResidentsNamesByPlanetName(String planetName) {
		// Call to 'https://swapi.dev/api/planets/?search={planetName}'.
		Planets planets = this.swApiClient.getPlanetsByPlanetName(planetName);
		List<String> planetsNameList = new ArrayList<>();
		for (Planet planetResult : planets.getResults()) {
			planetsNameList.add(planetResult.getName());
		}
		if (planets.getCount() == 0) {
			// No planets found for 'planetName'.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND,
					"No planets with the name '" + planetName + "' found");
		} else if (planets.getCount() > 1) {
			// Not exact match for 'planetName'. More than one result.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No planets with the name '" + planetName
					+ "' found. You are likely to be looking for any of these: " + planetsNameList.toString());
		} else if (planets.getResults().get(0).getName().equalsIgnoreCase(planetName)) {
			// Exact match for 'planetName' -> search of residents in the planet.
			List<String> residentsNameList = new ArrayList<>();
			Planet planet = planets.getResults().get(0);
			for (String personUrl : planet.getResidents()) {
				String id = Utils.extractIdFromUrl(personUrl);
				// Call to 'https://swapi.dev/api/people/{id}'.
				String name = this.swApiClient.getPersonByPersonId(id).getName();
				residentsNameList.add(name);
			}
			return residentsNameList;
		} else {
			// Not exact match for 'planetName'. Just one result.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No planets with the name '" + planetName
					+ "' found. You are likely to be looking for: " + planetsNameList.get(0));
		}
	}

}
