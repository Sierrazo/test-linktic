package com.linktic.testlinktic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = {
        "com.linktic.testlinktic.repositories"
})
@EntityScan(basePackages = {"com.linktic.testlinktic"})
@SpringBootApplication
public class TestLinkticApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestLinkticApplication.class, args);
    }

}
