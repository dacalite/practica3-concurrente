package com.ministeriomoagia.practica3.repositories;

import com.ministeriomoagia.practica3.models.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
