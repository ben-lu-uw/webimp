package com.bl.webimp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@SpringBootApplication
@RestController
public class Controller {

	public static void main(String[] args) {
		SpringApplication.run(Controller.class, args);

	}

	@GetMapping("/ck")
	public String ck(@RequestParam(value = "x", defaultValue = "0") int x,
						@RequestParam(value = "y", defaultValue = "0") int y) throws IOException {
		ChromaKey chromaKey = new ChromaKey();
		chromaKey.process(
				"C:\\Users\\avtea\\Desktop\\Input\\g.png",
				"C:\\Users\\avtea\\Desktop\\Output\\a.png", x, y);

		return String.format("Pixel at: %s %s!", x, y);

	}

}
