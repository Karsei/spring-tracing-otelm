package kr.pe.karsei.springtracingotel.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "api1")
@RequiredArgsConstructor
public class TestController {
    private final RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello, world!";
    }

    @GetMapping("call")
    public String call() {
        String url = "http://localhost:7001/api1/hello";

        StringBuilder sb = new StringBuilder();
        try {
            sb.append(restTemplate.getForObject(url, String.class));
        }
        catch (HttpStatusCodeException e) {
            throw e;
        }

        return String.format("call - %s", sb);
    }
}
