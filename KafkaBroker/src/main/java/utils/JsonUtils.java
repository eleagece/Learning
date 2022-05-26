package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public final class JsonUtils {

	/**
	 * Takes the String 'jsonString' and tries to convert it into a 'JSONObject'.
	 * 
	 * @param jsonString - a string that supposedly has JSON structure.
	 * @return the transformed 'jsonString' into JSONObject.
	 * @throws ParseException
	 */
	public static Object fromString(String jsonString) throws ParseException {
		JSONParser parser = new JSONParser();
		return parser.parse(jsonString);
	}

	/**
	 * Takes a JSON file from a 'filePath' and converts it to a JSON Object.
	 * 
	 * @param filePath - the path to the JSON file.
	 * @return the JSON Object.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 */
	public static Object toJsonObject(String filePath) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		return parser.parse(new FileReader(filePath));
	}

	/**
	 * Converts to string the JSON file in 'filePath' only if its format is the
	 * expected by the 'jsonType' structure.
	 * 
	 * @param jsonType - the type of structure expected in the JSON file in
	 *                 'filePath'.
	 * @param filePath - the path the the JSON file.
	 * @return the string version of the JSON file.
	 * @throws Exception
	 */
	public static String toString(String jsonType, String filePath) throws Exception {
		Object jsonObject = toJsonObject(filePath);
		if (isValidJsonStructure(jsonType, jsonObject)) {
			return jsonObject.toString();
		} else {
			throw new Exception("Not a valid '" + jsonType + "' structure in JSON file");
		}
	}

	/**
	 * Checks if the JSON Object has a valid structure which is defined by
	 * 'jsonType'.
	 * 
	 * @param jsonType   - the type of structure expected in the JSON Object in
	 *                   'jsonObject'.
	 * @param jsonObject - the JSON Object.
	 * @return
	 */
	public static Boolean isValidJsonStructure(String jsonType, Object jsonObject) {
		try {
			if (jsonType.equals(Constants.JsonUtils.PERSONS_LIST)) {
				JSONArray jsonArray = (JSONArray) jsonObject;
				for (Object jsonPerson : jsonArray) {
					JSONObject person = (JSONObject) jsonPerson;
					if ((String) person.get("name") == null || (Number) person.get("id") == null
							|| (String) person.get("email") == null) {
						return false;
					}
				}
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Prints the Object 'jsonObject' if the String 'jsonType' matches the structure
	 * of the Object.
	 * 
	 * @param jsonType   - the expected format of the 'jsonObject' to print.
	 * @param jsonObject - the Object to print.
	 * @throws Exception
	 */
	public static void printJsonObject(String jsonType, Object jsonObject) throws Exception {
		try {
			if (jsonType.equals(Constants.JsonUtils.PERSONS_LIST)) {
				JSONArray jsonArray = (JSONArray) jsonObject;
				for (Object jsonPerson : jsonArray) {
					JSONObject person = (JSONObject) jsonPerson;
					String name = (String) person.get("name");
					String id = ((Number) person.get("id")).toString();
					String email = (String) person.get("email");
					System.out.println("  name: '" + name + "', id: '" + id + "', email: '" + email + "'");
				}
			} else {
				System.out.println("  Unexpected message of type '" + jsonType + "'");
			}
		} catch (Exception e) {
			throw new Exception("Could not print object because it is not formed as a '" + jsonType + "' type:" + e);
		}

	}

}
