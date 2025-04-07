package com.voxloud.provisioning.service.impl;

import com.voxloud.provisioning.configgenerator.ConfigurationGenerator;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.exception.DeviceNotFoundException;
import com.voxloud.provisioning.repository.DeviceRepository;
import com.voxloud.provisioning.service.ProvisioningService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProvisioningServiceImpl implements ProvisioningService {

    private final DeviceRepository deviceRepository;
    private final List<ConfigurationGenerator> configurationGenerators;

    @Value("${provisioning.domain}")
    private String domain;

    @Value("${provisioning.port}")
    private String port;

    @Value("${provisioning.codecs}")
    private String codecs;

    public ProvisioningServiceImpl(DeviceRepository deviceRepository, List<ConfigurationGenerator> configurationGenerators) {
        this.deviceRepository = deviceRepository;
        this.configurationGenerators = configurationGenerators;
    }

    @Override
    public String getDeviceConfiguration(String macAddress) {
        Device device = deviceRepository.findByMacAddress(macAddress)
                .orElseThrow(() -> new DeviceNotFoundException("Device not found with MAC address: " + macAddress));

        return configurationGenerators.stream()
                .filter(generator -> generator.supports(device))
                .findFirst()
                .map(generator -> generator.generateConfiguration(device, domain, port, codecs))
                .orElseThrow(() -> new RuntimeException("No configuration generator found for device type: " + device.getDeviceType()));
    }
} 