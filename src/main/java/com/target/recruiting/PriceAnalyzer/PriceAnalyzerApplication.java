package com.target.recruiting.PriceAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.target.recruiting")
public class PriceAnalyzerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PriceAnalyzerApplication.class, args);
	}
}
