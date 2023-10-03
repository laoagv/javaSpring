package aop;

import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("bookBean")
public class Book {
    @Value("Преступление  и наказание")
    private String name;

    public String getName() {
        return name;
    }
}
