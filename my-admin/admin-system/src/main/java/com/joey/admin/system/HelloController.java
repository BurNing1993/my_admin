package com.joey.admin.system;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Joey
 */
@Component
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello() {
        return "HELLO WORLD!";
    }
}
