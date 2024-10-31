package com.ministeriomoagia.practica3.services;

import com.ministeriomoagia.practica3.models.LogEntry;
import com.ministeriomoagia.practica3.repositories.LogEntryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggingService {
    private static final Logger logger = LoggerFactory.getLogger(LoggingService.class);

    @Autowired
    private LogEntryRepository logEntryRepository;

    public void logEvent(String event) {
        logger.info("Event: {}", event);
        saveLog(event, "INFO");
    }

    public void logError(String error) {
        logger.error("Error: {}", error);
        saveLog(error, "ERROR");
    }

    private void saveLog(String message, String level) {
        LogEntry logEntry = new LogEntry(message, level);
        logEntryRepository.save(logEntry);
    }

    // New method to clear all logs
    public void clearLogs() {
        logEntryRepository.deleteAll();
        logger.info("All logs have been cleared.");
    }
}
