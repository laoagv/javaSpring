package spting_introduction;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyConfig1 {
    @Bean
    public Pet dogBean(){
        return new Dog();
    }
    @Bean
    public Person personBean(){
        return new Person(dogBean());
    }
}
