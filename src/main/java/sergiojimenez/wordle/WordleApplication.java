package sergiojimenez.wordle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.core.Ordered;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@SpringBootApplication
@EnableAsync
public class WordleApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(WordleApplication.class, args);
	}

	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("forward:/index.html");
		registry.addViewController("/error").setViewName("forward:/");
		registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
	}

}