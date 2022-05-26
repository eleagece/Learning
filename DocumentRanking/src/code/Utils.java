package code;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Utils {

	public String readFileToString(String dir, String filename) {
        StringBuilder contentBuilder = new StringBuilder(); 
        try (Stream<String> stream = Files.lines(Paths.get(dir + "/" + filename), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s).append("\n"));
        }
        catch (IOException e) {
            e.printStackTrace();
        } 
        return contentBuilder.toString();
	}

	public int countWords(String text) {
	    if (text == null || text.isEmpty()) {
	      return 0;
	    }
	    String[] words = text.split("\\s+");
	    return words.length;
	}

}