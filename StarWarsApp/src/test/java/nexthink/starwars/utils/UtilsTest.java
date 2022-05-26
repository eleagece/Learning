package nexthink.starwars.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UtilsTest {

	/**
	 * With final '/'.
	 */
	@Test
	void finalSlash() {
		assertEquals("1234", Utils.extractIdFromUrl("http://swapi.dev/1234/"));
	}

	/**
	 * With no final '/'.
	 */
	@Test
	void noFinalSlash() {
		assertEquals("1234", Utils.extractIdFromUrl("http://swapi.dev/1234"));
	}

	/**
	 * With no number id and final '/'.
	 */
	@Test
	void noNumberIdFinalSlash() {
		assertEquals("", Utils.extractIdFromUrl("http://swapi.dev/"));
	}

	/**
	 * With no number id and no final '/'.
	 */
	@Test
	void noNumberIdNoFinalSlash() {
		assertEquals("", Utils.extractIdFromUrl("http://swapi.dev"));
	}
	
	/**
	 * Not an url.
	 */
	@Test
	void notAnUrl() {
		assertEquals("", Utils.extractIdFromUrl("not-an-url-1234"));
	}
	
	/**
	 * Null string.
	 */
	@Test
	void nullString() {
		assertEquals("", Utils.extractIdFromUrl(null));
	}

}
