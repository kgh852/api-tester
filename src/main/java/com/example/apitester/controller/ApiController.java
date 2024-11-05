package com.example.apitester.controller;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/sendRequest")
    public ResponseEntity<String> sendGetRequest(@RequestParam String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("GET 요청 오류 발생: " + e.getMessage());
        }
    }

    @PostMapping("/sendRequest")
    public ResponseEntity<String> sendPostRequest(@RequestParam String url, @RequestBody String jsonBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("POST 요청 오류 발생: " + e.getMessage());
        }
    }

    @PutMapping("/sendRequest")
    public ResponseEntity<String> sendPutRequest(@RequestParam String url, @RequestBody String jsonBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, request, String.class);
            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("PUT 요청 오류 발생: " + e.getMessage());
        }
    }

    @DeleteMapping("/sendRequest")
    public ResponseEntity<String> sendDeleteRequest(@RequestParam String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(url);
            return ResponseEntity.ok("DELETE 요청 성공");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("DELETE 요청 오류 발생: " + e.getMessage());
        }
    }


    @GetMapping("/fetch")
    public ResponseEntity<String> fetchHtmlFromUrl(@RequestParam String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String htmlContent = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(htmlContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("URL을 가져오는 중 오류 발생: " + e.getMessage());
        }
    }
}

