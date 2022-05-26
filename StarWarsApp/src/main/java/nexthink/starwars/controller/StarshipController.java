package nexthink.starwars.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import nexthink.starwars.data.result.Starship;
import nexthink.starwars.service.StarshipService;

@RestController
public class StarshipController {

	@Autowired
	private StarshipService starshipService;

	@GetMapping("/starships/starships-info-by-person-name/{personName}")
	@ResponseStatus(HttpStatus.OK)
	public List<Starship> getStarshipsInfoByPersonName(@PathVariable String personName) {
		return starshipService.getStarshipsInfoByPersonName(personName);
	}

	@GetMapping("/starships/starships-info-by-person-name/")
	@ResponseStatus(HttpStatus.OK)
	public Void getStarshipsInfoByPersonName() {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "A person name should be passed");
	}
}
