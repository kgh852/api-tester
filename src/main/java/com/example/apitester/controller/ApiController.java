package com.example.apitester.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ApiController {

    @GetMapping("/fetch")
    public ResponseEntity<String> fetchHtmlFromUrl(@RequestParam String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String htmlContent = restTemplate.getForObject(url, String.class);
            return ResponseEntity.ok(htmlContent);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching URL: " + e.getMessage());
        }
    }

    @PostMapping("/sendPost")
    public ResponseEntity<String> sendPostRequest(@RequestParam String url, @RequestBody String jsonBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
            String response = restTemplate.postForObject(url, request, String.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending POST request: " + e.getMessage());
        }
    }

    @PutMapping("/sendPut")
    public ResponseEntity<String> sendPutRequest(@RequestParam String url, @RequestBody String jsonBody) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> request = new HttpEntity<>(jsonBody, headers);
            restTemplate.put(url, request);
            return ResponseEntity.ok("PUT 요청이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending PUT request: " + e.getMessage());
        }
    }

    @DeleteMapping("/sendDelete")
    public ResponseEntity<String> sendDeleteRequest(@RequestParam String url) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.delete(url);
            return ResponseEntity.ok("DELETE 요청이 성공적으로 처리되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error sending DELETE request: " + e.getMessage());
        }
    }
}
