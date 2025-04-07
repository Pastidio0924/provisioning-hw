package com.voxloud.provisioning.configgenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.voxloud.provisioning.entity.Device;
import com.voxloud.provisioning.entity.DeviceType;
import org.springframework.stereotype.Component;

@Component
public class ConferenceConfigurationGenerator implements ConfigurationGenerator {
    
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    public String generateConfiguration(Device device, String domain, String port, String codecs) {
        try {
            ObjectNode config = objectMapper.createObjectNode();
            
            // Add base configuration
            config.put("username", device.getUsername());
            config.put("password", device.getPassword());
            config.put("domain", domain);
            config.put("port", port);
            config.set("codecs", objectMapper.readTree("[\"" + codecs.replace(",", "\",\"") + "\"]"));
            
            // Apply override fragment if exists
            if (device.getOverrideFragment() != null && !device.getOverrideFragment().isEmpty()) {
                ObjectNode overrideConfig = (ObjectNode) objectMapper.readTree(device.getOverrideFragment());
                overrideConfig.fields().forEachRemaining(entry -> config.set(entry.getKey(), entry.getValue()));
            }
            
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(config);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate conference configuration", e);
        }
    }
    
    @Override
    public boolean supports(Device device) {
        return device.getDeviceType() == DeviceType.CONFERENCE;
    }
} 