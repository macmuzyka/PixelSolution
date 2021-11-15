package com.pixel.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * Created by Maciej Muzyka
 * on 11.11.2021
 * TODO:
 */

@RestController
@RequestMapping("/spring")
class SpringValueController {
    @Value("${string.test.value}")
    String testValue;

    TestObject to = new TestObject(true, "25", List.of("Error1", "Error2", "Unknown Error"));

    @GetMapping("/test")
    public ResponseEntity<?> value() {
        int two = 2;
        return ResponseEntity.ok(Integer.parseInt(testValue) > two);
    }

    @GetMapping("/time")
    public ResponseEntity<LocalDateTime> timestamp() {
        LocalDateTime ldt = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        return ResponseEntity.ok(ldt);
    }

    @GetMapping("/nested_object")
    public ResponseEntity<TestObject> getTestObject() {
        if (to != null) {
            return ResponseEntity.ok(to);
        } else {
            return null;
        }
    }
}
