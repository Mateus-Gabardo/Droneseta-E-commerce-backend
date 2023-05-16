package com.api.dronesetaecommerce.controller.health;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api("API rest para a verificação da vida da aplicação.")
public class HealthController {
    @GetMapping("/health")
    public ResponseEntity<?> getHealth() {
        return ResponseEntity.ok("Health!");
    }
}
