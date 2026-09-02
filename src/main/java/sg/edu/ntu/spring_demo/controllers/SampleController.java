package sg.edu.ntu.spring_demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sg.edu.ntu.spring_demo.SampleItem;

@RestController
public class SampleController {
    private static final Logger logger = LoggerFactory.getLogger(SampleController.class);

    @Value("${spring.application.name:Demo Spring Boot Application}")
    private String appName;

    @Value("${server.port:8081}")
    private String port;

    // Instantiate a new SampleItem
    @Autowired
    SampleItem item;

    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GetMapping("/greet")
    public String greet(
            @RequestParam(defaultValue = "World") String name,
            @RequestParam(defaultValue = "unknown") String role) {
        return "Hello " + name + "! You are a " + role + "!";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable int id) {
        return "User ID: " + id;
    }

    @GetMapping("/products")
    public String getProducts(@RequestParam(defaultValue = "all") String search) {
        return "You have searched for " + search;
    }

    @GetMapping("/products/{id}")
    public String getProductById(@PathVariable int id) {
        logger.info("Product requested with id {}", id);
        logger.warn("This is a test warning");
        logger.error("This is a test error");

        return "You have requested for product with id: " + id;
    }

    @GetMapping("/app-info")
    public String getAppInfo() {
        return "App " + appName + " is running on port: " + port;
    }

    @GetMapping("/item")
    public SampleItem getItem() {
        item.setId(1);
        item.setName("Apple");
        item.setPrice(1.99);
        item.setDesc("A red apple");
        return item;
    }
}
