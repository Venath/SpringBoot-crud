package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student john = new Student(
                    "John",
                    "Doe",
                    LocalDate.of(2000, 7, 18)
            );

            Student venath = new Student(
                    "venath",
                    "venath@gmail.com",
                    LocalDate.of(2001, 7, 18)
            );

            // Save the student to the database
            repository.saveAll(
                    List.of(john, venath )
            );
        };
    }
}
