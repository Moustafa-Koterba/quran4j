package quran4j.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = { "quran4j.library", "quran4j.admin" })
@EnableJpaRepositories(basePackages = "quran4j.library")
@EntityScan(basePackages = "quran4j.library")
public class AdminApplication {
	public static void main(String[] args) {
		System.out.println("Hello world 3");
		SpringApplication.run(AdminApplication.class, args);
	}
}
