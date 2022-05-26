package nexthink.starwars.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import nexthink.starwars.client.SwApiClient;
import nexthink.starwars.data.list.People;
import nexthink.starwars.data.result.Person;
import nexthink.starwars.data.result.Starship;
import nexthink.starwars.utils.Utils;

@Service
public class StarshipService {

	@Autowired
	private SwApiClient swApiClient;

	/**
	 * Search in 'https://swapi.dev/api/' the full information of the starships used
	 * by a person given in the String 'personName'.
	 * 
	 * @param personName - the searched person.
	 * @return a List of Starship used by the searched person.
	 */
	public List<Starship> getStarshipsInfoByPersonName(String personName) {
		// Call to 'https://swapi.dev/api/people/?search={personName}'.
		People people = this.swApiClient.getPeopleByPersonName(personName);
		List<String> peopleNameList = new ArrayList<>();
		for (Person personResult : people.getResults()) {
			peopleNameList.add(personResult.getName());
		}
		if (people.getCount() == 0) {
			// No people found for 'personName'.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No people with the name '" + personName + "' found");
		} else if (people.getCount() > 1) {
			// Not exact match for 'personName'. More than one result.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No people with the name '" + personName
					+ "' found. You are likely to be looking for any of these: " + peopleNameList.toString());
		} else if (people.getResults().get(0).getName().equalsIgnoreCase(personName)) {
			// Exact match for 'personName' -> search of starships used by the person.
			List<Starship> starshipList = new ArrayList<>();
			Person person = people.getResults().get(0);
			for (String starshipUrl : person.getStarships()) {
				String id = Utils.extractIdFromUrl(starshipUrl);
				// Call to 'https://swapi.dev/api/starships/{id}'.
				Starship starship = this.swApiClient.getStarshipByStarshipId(id);
				starshipList.add(starship);
			}
			return starshipList;
		} else {
			// Not exact match for 'personName'. Just one result.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No people with the name '" + personName
					+ "' found. You are likely to be looking for: " + peopleNameList.get(0));
		}
	}
}
