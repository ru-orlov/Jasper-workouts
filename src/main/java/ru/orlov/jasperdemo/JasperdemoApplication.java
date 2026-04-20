package ru.orlov.jasperdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JasperdemoApplication {

	public static void main(String[] args) {


        SpringApplication.run(JasperdemoApplication.class, args);

        System.setProperty(
                "javax.xml.parsers.DocumentBuilderFactory",
                "org.apache.xerces.jaxp.DocumentBuilderFactoryImpl"
        );
        org.springframework.boot.SpringApplication.run(JasperdemoApplication.class, args);
	}

}
