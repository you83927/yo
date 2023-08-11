package com.ispan.demo;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Project1ApplicationTests {

	@Test
	void contextLoads() {
		
		String string = UUID.randomUUID().toString();
		
		System.err.println(string);
	}

}
