package com.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProjectApplication {
	@Autowired
	private ServletWebServerApplicationContext webServerAppCtxt;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}
	@EventListener(ApplicationReadyEvent.class)
	public void onApplicationReadyEvent() {
		int port = webServerAppCtxt.getWebServer().getPort();
		String appUrl = "http://localhost:" + port;
		System.out.println("Application is running at: " + appUrl);
	}

}
