package com.voxloud.provisioning.configgenerator;

import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.DeviceType;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class DeskConfigurationGenerator implements ConfigurationGenerator {
    
    @Override
    public String generateConfiguration(Device device, String domain, String port, String codecs) {
        Properties props = new Properties();
        
        // Add base configuration
        props.setProperty("username", device.getUsername());
        props.setProperty("password", device.getPassword());
        props.setProperty("domain", domain);
        props.setProperty("port", port);
        props.setProperty("codecs", codecs);
        
        // Apply override fragment if exists
        if (device.getOverrideFragment() != null && !device.getOverrideFragment().isEmpty()) {
            Properties overrideProps = new Properties();
            try {
                overrideProps.load(new java.io.StringReader(device.getOverrideFragment()));
                props.putAll(overrideProps);
            } catch (Exception e) {
                // Log error but continue with base configuration
            }
        }
        
        // Convert Properties to String
        StringBuilder result = new StringBuilder();
        props.forEach((key, value) -> result.append(key).append("=").append(value).append("\n"));
        return result.toString();
    }
    
    @Override
    public boolean supports(Device device) {
        return device.getDeviceType() == DeviceType.DESK;
    }
} 