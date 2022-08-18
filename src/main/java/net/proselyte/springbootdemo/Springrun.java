package net.proselyte.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SpringBootApplication
public class Springrun
{
    public static void main(String[] args)
    {
        SpringApplication.run(Springrun.class, args);
    }
}