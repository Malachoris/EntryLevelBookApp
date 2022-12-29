package com.demodemoDematic.entryLevelBookStore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class EntryLevelBookStoreApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(EntryLevelBookStoreApplication.class, args);

		// Just wanted to print all beans because had some problems SpringBoot reading them previously
		//Arrays.stream(context.getBeanDefinitionNames()).forEach(System.out::println);
	}
}
