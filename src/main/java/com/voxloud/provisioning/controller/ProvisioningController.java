package com.voxloud.provisioning.controller;

import com.voxloud.provisioning.service.ProvisioningService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/provisioning")
public class ProvisioningController {

    private final ProvisioningService provisioningService;

    public ProvisioningController(ProvisioningService provisioningService) {
        this.provisioningService = provisioningService;
    }

    @GetMapping(value = "/{macAddress}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getDeviceConfiguration(@PathVariable String macAddress) {
        String configuration = provisioningService.getDeviceConfiguration(macAddress);
        return ResponseEntity.ok(configuration);
    }
}