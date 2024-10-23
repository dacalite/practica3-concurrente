package com.ministeriomoagia.practica3.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    public void logEvent(String event) {
        logger.info("Event: " + event);
    }

    public void logError(String error) {
        logger.error("Error: " + error);
    }
}

