package nexthink.starwars.data.list;

import nexthink.starwars.data.result.Person;

import java.util.List;

public class People {

	private int count;
	private String next;
	private String previous;
	private List<Person> results;

	public People() {
	}

	public People(int count, String next, String previous, List<Person> results) {
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

	public List<Person> getResults() {
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

	public void setResults(List<Person> results) {
		this.results = results;
	}

}
