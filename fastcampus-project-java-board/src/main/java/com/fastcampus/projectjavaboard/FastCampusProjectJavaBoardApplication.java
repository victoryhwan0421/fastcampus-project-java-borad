package com.fastcampus.projectjavaboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FastCampusProjectJavaBoardApplication {

    public static void main(String[] args) {
        SpringApplication.run(FastCampusProjectJavaBoardApplication.class, args);
    }

}
