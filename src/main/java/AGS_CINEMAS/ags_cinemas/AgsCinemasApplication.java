package AGS_CINEMAS.ags_cinemas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AgsCinemasApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgsCinemasApplication.class, args);
		
		System.out.println("AGS CINEMAS IS UP ON PORT 8080");
	}

}
