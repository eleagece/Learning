package nexthink.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nexthink.starwars.service.PlanetService;

@RestController
public class PlanetController {

	@Autowired
	private PlanetService planetService;

	@GetMapping("/planets/residents-names-by-planet-name/{planetName}")
	@ResponseStatus(HttpStatus.OK)
	public List<String> getResidentsNamesByPlanetName(@PathVariable String planetName) {
		return planetService.getResidentsNamesByPlanetName(planetName);
	}

	@GetMapping("/planets/residents-names-by-planet-name/")
	@ResponseStatus(HttpStatus.OK)
	public Void getResidentsNamesByPlanetName() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A planet name should be passed");
	}

}
