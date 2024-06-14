package com.lxxx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@SpringBootApplication
public class SegApplication {
    public static void main(String[] args) {
        SpringApplication.run(SegApplication.class, args);
    }
}
