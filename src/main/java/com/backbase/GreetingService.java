package com.backbase;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingService.class);

    @Value("${greeting.name:World}")
    private String name;

    public String greet() {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Greeting {}", name);
        }
        return "Hello " + name;
    }
}
