package com.example.first_application.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class controller {

	// Hello endpoint
    @GetMapping("/hello")
    public Map<String, String> hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        Map<String, String> response = new HashMap<>();
        response.put("greeting", "Hello, " + name);
        return response;
    }

	// Info endpoint
    @GetMapping("/info")
    public Map<String, Object> info(HttpServletRequest request) {
        Map<String, Object> response = new HashMap<>();
        
        // Request time formatted in ISO8601
        String time = Instant.now().toString();
        response.put("time", time);

        // Client IP Address
        String clientAddress = request.getLocalAddr();
        response.put("client_address", clientAddress);

        // Host name
        String hostName = request.getServerName();
        response.put("host_name", hostName);

        // Client Request Headers
        Map<String, String> headers = new HashMap<>();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            headers.put(headerName, request.getHeader(headerName));
        }
        response.put("headers", headers);

        return response;
    }
}

