package nexthink.starwars.data.result;

import java.util.ArrayList;
import java.util.List;

public class Person {

	private String name;
	private String height;
	private String mass;
	private String hair_color;
	private String skin_color;
	private String eye_color;
	private String birth_year;
	private String gender;
	private String homeworld;
	private List<String> films = new ArrayList<>();
	private List<String> species = new ArrayList<>();
	private List<String> vehicles = new ArrayList<>();
	private List<String> starships = new ArrayList<>();
	private String created;
	private String edited;
	private String url;

	public Person() {
	}

	public Person(String name, String height, String mass, String hair_color, String skin_color, String eye_color,
			String birth_year, String gender, String homeworld, List<String> films, List<String> species,
			List<String> vehicles, List<String> starships, String created, String edited, String url) {
		this.name = name;
		this.height = height;
		this.mass = mass;
		this.hair_color = hair_color;
		this.skin_color = skin_color;
		this.eye_color = eye_color;
		this.birth_year = birth_year;
		this.gender = gender;
		this.homeworld = homeworld;
		this.films = films;
		this.species = species;
		this.vehicles = vehicles;
		this.starships = starships;
		this.created = created;
		this.edited = edited;
		this.url = url;
	}

	public String getName() {
		return name;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
	}

	public List<String> getSpecies() {
		return species;
	}

	public void setSpecies(List<String> species) {
		this.species = species;
	}

	public List<String> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<String> vehicles) {
		this.vehicles = vehicles;
	}

	public List<String> getStarships() {
		return starships;
	}

	public void setStarships(List<String> starships) {
		this.starships = starships;
	}

	public String getHeight() {
		return height;
	}

	public String getMass() {
		return mass;
	}

	public String getHair_color() {
		return hair_color;
	}

	public String getSkin_color() {
		return skin_color;
	}

	public String getEye_color() {
		return eye_color;
	}

	public String getBirth_year() {
		return birth_year;
	}

	public String getGender() {
		return gender;
	}

	public String getHomeworld() {
		return homeworld;
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

	public void setHeight(String height) {
		this.height = height;
	}

	public void setMass(String mass) {
		this.mass = mass;
	}

	public void setHair_color(String hair_color) {
		this.hair_color = hair_color;
	}

	public void setSkin_color(String skin_color) {
		this.skin_color = skin_color;
	}

	public void setEye_color(String eye_color) {
		this.eye_color = eye_color;
	}

	public void setBirth_year(String birth_year) {
		this.birth_year = birth_year;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
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
