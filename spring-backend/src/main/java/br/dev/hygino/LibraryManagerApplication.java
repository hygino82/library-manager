package br.dev.hygino;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class LibraryManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryManagerApplication.class, args);
	}

}
