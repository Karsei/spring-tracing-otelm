package kr.pe.karsei.springtracingotel.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping(value = "api1")
@RequiredArgsConstructor
public class TestController {
    private final RestTemplate restTemplate;

    @GetMapping("/hello")
    public String hello(@RequestHeader Map<String, String> headers) {
        headers.forEach((k, v) -> log.info("Hello - Request Header '{}' = {}", k, v));
        return "hello, world!";
    }

    @GetMapping("call")
    public String call(@RequestHeader Map<String, String> headers) {
        headers.forEach((k, v) -> log.info("Call - Request Header '{}' = {}", k, v));

        String url = "http://10.43.252.208/api1/hello";

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
