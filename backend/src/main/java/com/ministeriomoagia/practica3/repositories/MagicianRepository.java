package com.ministeriomoagia.practica3.repositories;

import com.ministeriomoagia.practica3.models.Magician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MagicianRepository extends JpaRepository<Magician, Long> {

    @Query("SELECT m FROM Magician m WHERE m.name = ?1")
    Optional<Magician> findMagicianByName(String name);
}
