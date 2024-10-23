package com.ministeriomoagia.practica3.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "log_entries")
@Getter
@Setter
public class LogEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private String level;

    private LocalDateTime timestamp;

    // Getters y Setters

    public LogEntry() {
    }

    public LogEntry(String message, String level) {
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();
    }

    // Getters y Setters
}
