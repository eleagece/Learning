package nexthink.starwars;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StarWarsAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(StarWarsAppApplication.class, args);
	}

}
