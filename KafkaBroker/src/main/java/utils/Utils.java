package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Properties;

public final class Utils {
	
	/**
	 * Gets the properties from the file '/src/main/resources/config.properties/config.properties'.
	 * @return - the properties.
	 * @throws IOException
	 */
	public static Properties getConfigProperties() throws IOException {
		Properties props = new Properties();
		InputStream input = Utils.class.getClassLoader().getResourceAsStream("config.properties");
		props.load(input);		
		return props;
	}
	
	/*
	 * Gets the current time in HH:MM:SS
	 */
	public static Formatter getCurrentTime() {
        Formatter format = new Formatter();
        Calendar gfg_calender = Calendar.getInstance();
        format = new Formatter();
        format.format("%tl:%tM:%tS", gfg_calender,
                       gfg_calender, gfg_calender);
        return format;
	}

}
