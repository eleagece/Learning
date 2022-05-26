package nexthink.starwars.utils;

public class Utils {

	/**
	 * Returns the id at the end of the url and checks if it is numeric. If not it
	 * returns an empty String.
	 * 
	 * @param url - the url.
	 * @return the id as a String.
	 */
	public static String extractIdFromUrl(String url) {
		if (url == null || url.chars().filter(ch -> ch == '/').count() == 0) {
			return "";
		} else {
			String[] urlSplitted = url.split("/");
			try {
				Integer.parseInt(urlSplitted[urlSplitted.length - 1]);
			} catch (NumberFormatException nfe) {
				return "";
			}
			return urlSplitted[urlSplitted.length - 1];
		}
	}

}
