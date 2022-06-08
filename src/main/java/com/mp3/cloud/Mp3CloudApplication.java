package com.mp3.cloud;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Mp3CloudApplication implements CommandLineRunner{
    @Value("${info.root-dir}")
    private String rootPath;

    public static void main(String[] args) {
        SpringApplication.run(Mp3CloudApplication.class, args);
    }

        @Override
        public void run(String... args) throws Exception {
    }

}

