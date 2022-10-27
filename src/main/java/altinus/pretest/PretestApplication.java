package altinus.pretest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class PretestApplication {

	public static void main(String[] args) {
		SpringApplication.run(PretestApplication.class, args);
	}

}
