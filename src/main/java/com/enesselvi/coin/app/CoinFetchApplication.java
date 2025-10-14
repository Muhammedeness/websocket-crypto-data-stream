package com.enesselvi.coin.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.enesselvi.*")
public class CoinFetchApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoinFetchApplication.class, args);
	}

}
