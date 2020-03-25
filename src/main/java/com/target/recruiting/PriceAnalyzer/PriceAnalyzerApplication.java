package com.target.recruiting.PriceAnalyzer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.google.gson.*;
import java.util.HashMap;

@SpringBootApplication
public class PriceAnalyzerApplication {
	public static void main(String[] args) {
		SpringApplication.run(PriceAnalyzerApplication.class, args);
	}
}
