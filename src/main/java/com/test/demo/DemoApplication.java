package com.test.demo;

import com.test.demo.controllers.IntervalsController;
import com.test.demo.service.IntervalsService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.test.demo.configuration")
@ComponentScan(basePackageClasses = {IntervalsController.class, IntervalsService.class})
public class DemoApplication {


    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);

    }
}
