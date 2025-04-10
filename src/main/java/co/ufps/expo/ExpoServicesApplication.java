package co.ufps.expo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigServer
@EnableTask
@Slf4j
public class ExpoServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExpoServicesApplication.class, args);
	}

	@Bean
	public CommandLineRunner demoTask() {
		return args -> {
			log.info("Ejecutando tarea de demostración con Spring Cloud Task");
			log.info("Tarea completada");
		};
	}
}
