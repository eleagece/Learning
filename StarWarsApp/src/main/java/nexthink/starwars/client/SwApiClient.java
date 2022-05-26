package nexthink.starwars.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import nexthink.starwars.data.list.People;
import nexthink.starwars.data.list.Planets;
import nexthink.starwars.data.result.Person;
import nexthink.starwars.data.result.Starship;

@FeignClient(name = "swapi", url = "https://swapi.dev/api/")
public interface SwApiClient {

	/**
	 * Searches for the planet identified with the String 'planetName'.
	 * 
	 * @implNote - used in PlanetService
	 * @param planetName - the name of the planet to be searched.
	 * @return
	 */
	@GetMapping("planets/?search={planetName}")
	Planets getPlanetsByPlanetName(@PathVariable String planetName);

	/**
	 * Searches for the person identified by the String 'id'.
	 * 
	 * @implNote - used in PlanetService
	 * @param id - the id of the person to be searched.
	 * @return
	 */
	@GetMapping("people/{id}")
	Person getPersonByPersonId(@PathVariable String id);

	/**
	 * Searches for the person identified with the String 'personName'.
	 * 
	 * @implNote - used in StarshipService
	 * @param personName - the name of the person to be searched.
	 * @return
	 */
	@GetMapping("people/?search={personName}")
	People getPeopleByPersonName(@PathVariable String personName);

	/**
	 * Searches for the starship identified by the String 'id'.
	 * 
	 * @implNote - used in StarshipService
	 * @param id - the id of the starship to be searched.
	 * @return
	 */
	@GetMapping("starships/{id}")
	Starship getStarshipByStarshipId(@PathVariable String id);

}
