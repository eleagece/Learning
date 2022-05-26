package nexthink.starwars.data.list;

import nexthink.starwars.data.result.Planet;

import java.util.List;

public class Planets {

	private int count;
	private String next;
	private String previous;
	private List<Planet> results;

	public Planets() {
	}

	public Planets(int count, String next, String previous, List<Planet> results) {
		this.count = count;
		this.next = next;
		this.previous = previous;
		this.results = results;
	}

	public int getCount() {
		return count;
	}

	public String getNext() {
		return next;
	}

	public String getPrevious() {
		return previous;
	}

	public List<Planet> getResults() {
		return results;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public void setNext(String next) {
		this.next = next;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	public void setResults(List<Planet> results) {
		this.results = results;
	}

}
