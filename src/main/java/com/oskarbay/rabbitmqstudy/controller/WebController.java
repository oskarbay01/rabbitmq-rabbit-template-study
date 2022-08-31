package com.oskarbay.rabbitmqstudy.controller;

import com.oskarbay.rabbitmqstudy.domain.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class WebController {

    private final RabbitTemplate rabbitTemplate;

    @GetMapping("/test/{name}")
    public String testAPI(@PathVariable("name") String name) {
        Person person = new Person(1L, name);
        rabbitTemplate.convertAndSend("my-queue",person);

        return "Success";
    }
}
