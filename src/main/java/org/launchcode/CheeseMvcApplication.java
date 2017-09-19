package org.launchcode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CheeseMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(CheeseMvcApplication.class, args);
		//note here's where something goes amiss with findByCat()
	}
}
