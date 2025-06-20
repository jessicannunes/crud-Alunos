package io.jessnunes.alunos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class AlunosApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlunosApplication.class, args);
	}

}
