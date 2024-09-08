package quran4j.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "quran4j.library", "quran4j.api" })
@EnableJpaRepositories(basePackages = "quran4j.library")
@EntityScan(basePackages = "quran4j.library")
public class ApiApplication {

	public static void main(String[] args) {
		System.out.println("hello world");
		SpringApplication.run(ApiApplication.class, args);
	}

}
