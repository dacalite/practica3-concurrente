package com.ministeriomoagia.practica3.controllers;

import com.ministeriomoagia.practica3.annotations.RequiresAuth;
import com.ministeriomoagia.practica3.models.LogEntry;
import com.ministeriomoagia.practica3.repositories.LogEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("public/api/v1/logs")
public class LogController {

    @Autowired
    private LogEntryRepository logEntryRepository;

    @RequiresAuth
    @GetMapping
    public List<LogEntry> getLogs(@RequestHeader HttpHeaders headers) {
        return logEntryRepository.findAll();
    }
}
