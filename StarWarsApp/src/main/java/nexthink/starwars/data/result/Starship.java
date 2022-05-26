package nexthink.starwars.data.result;

import java.util.ArrayList;
import java.util.List;

public class Starship {

	private String name;
	private String model;
	private String manufacturer;
	private String cost_in_credits;
	private String length;
	private String max_atmosphering_speed;
	private String crew;
	private String passengers;
	private String cargo_capacity;
	private String consumables;
	private String hyperdrive_rating;
	private String MGLT;
	private String starship_class;
	private List<String> pilots = new ArrayList<>();
	private List<String> films = new ArrayList<>();
	private String created;
	private String edited;
	private String url;

	public Starship() {
	}

	public Starship(String name, String model, String manufacturer, String cost_in_credits, String length,
			String max_atmosphering_speed, String crew, String passengers, String cargo_capacity, String consumables,
			String hyperdrive_rating, String MGLT, String starship_class, List<String> pilots, List<String> films,
			String created, String edited, String url) {
		this.name = name;
		this.model = model;
		this.manufacturer = manufacturer;
		this.cost_in_credits = cost_in_credits;
		this.length = length;
		this.max_atmosphering_speed = max_atmosphering_speed;
		this.crew = crew;
		this.passengers = passengers;
		this.cargo_capacity = cargo_capacity;
		this.consumables = consumables;
		this.hyperdrive_rating = hyperdrive_rating;
		this.MGLT = MGLT;
		this.starship_class = starship_class;
		this.pilots = pilots;
		this.films = films;
		this.created = created;
		this.edited = edited;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public String getModel() {
		return model;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public String getCost_in_credits() {
		return cost_in_credits;
	}

	public String getLength() {
		return length;
	}

	public String getMax_atmosphering_speed() {
		return max_atmosphering_speed;
	}

	public String getCrew() {
		return crew;
	}

	public String getPassengers() {
		return passengers;
	}

	public String getCargo_capacity() {
		return cargo_capacity;
	}

	public List<String> getPilots() {
		return pilots;
	}

	public void setPilots(List<String> pilots) {
		this.pilots = pilots;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public String getConsumables() {
		return consumables;
	}

	public String getHyperdrive_rating() {
		return hyperdrive_rating;
	}

	public String getMGLT() {
		return MGLT;
	}

	public String getStarship_class() {
		return starship_class;
	}

	public String getCreated() {
		return created;
	}

	public String getEdited() {
		return edited;
	}

	public String getUrl() {
		return url;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public void setCost_in_credits(String cost_in_credits) {
		this.cost_in_credits = cost_in_credits;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public void setMax_atmosphering_speed(String max_atmosphering_speed) {
		this.max_atmosphering_speed = max_atmosphering_speed;
	}

	public void setCrew(String crew) {
		this.crew = crew;
	}

	public void setPassengers(String passengers) {
		this.passengers = passengers;
	}

	public void setCargo_capacity(String cargo_capacity) {
		this.cargo_capacity = cargo_capacity;
	}

	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}

	public void setHyperdrive_rating(String hyperdrive_rating) {
		this.hyperdrive_rating = hyperdrive_rating;
	}

	public void setMGLT(String MGLT) {
		this.MGLT = MGLT;
	}

	public void setStarship_class(String starship_class) {
		this.starship_class = starship_class;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public void setEdited(String edited) {
		this.edited = edited;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
