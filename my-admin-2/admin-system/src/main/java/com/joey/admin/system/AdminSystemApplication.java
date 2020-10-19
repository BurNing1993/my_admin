package com.joey.admin.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author Joey
 */
@SpringBootApplication
@EnableJpaAuditing
public class AdminSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminSystemApplication.class, args);
    }

}
