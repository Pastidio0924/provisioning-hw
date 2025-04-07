package com.voxloud.provisioning.configgenerator;

import com.voxloud.provisioning.entity.Device;

public interface ConfigurationGenerator {
    String generateConfiguration(Device device, String domain, String port, String codecs);
    boolean supports(Device device);
} 