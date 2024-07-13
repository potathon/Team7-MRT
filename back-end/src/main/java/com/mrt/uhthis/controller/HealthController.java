package com.mrt.uhthis.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthController {

    @GetMapping("/health")
    public ResponseEntity<HealthResponse> health() {
        return ResponseEntity.ok(HealthResponse.of("ok"));
    }

    // nested
    public record HealthResponse(
            String state
    ) {

        public static HealthResponse of(String state) {
            return new HealthResponse(state);
        }
    }
}
