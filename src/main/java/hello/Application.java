package hello;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import hello.user.Person;
import hello.user.PersonRepository;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {

	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }
	
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
    
    @Bean
    public CommandLineRunner demo(PersonRepository repo) {
    	return (args) -> {
    		repo.save(Person.builder().name("Tim").password("Tim").build());
    		repo.save(Person.builder().name("Viktor").password("Viktor").build());
    	};
    }
}