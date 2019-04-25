package com.example.backendinterviewtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by GreenNun on 2019-04-22.
 */
@Controller
@EnableFeignClients
@SpringBootApplication
public class BackendInterviewTaskApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendInterviewTaskApplication.class, args);
    }

    @RequestMapping("/")
    public String swagger() {
        return "redirect:/swagger-ui.html";
    }
}
