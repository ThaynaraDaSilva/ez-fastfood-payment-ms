package br.com.fiap.ez.fastfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
@ComponentScan(basePackages = 
	{
			"br.com.fiap.ez.fastfood",
			"br.com.fiap.ez.fastfood.adapters.in.listener",
			"br.com.fiap.ez.fastfood.infrastructure.config"
	})
public class ApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(ApiApplication.class, args);
	}
}
