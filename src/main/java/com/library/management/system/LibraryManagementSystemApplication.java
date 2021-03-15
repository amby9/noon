package com.library.management.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration(exclude={SecurityAutoConfiguration.class})
@ComponentScan(basePackages = "com")
@EnableSwagger2
public class LibraryManagementSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(LibraryManagementSystemApplication.class, args);
    }

}
