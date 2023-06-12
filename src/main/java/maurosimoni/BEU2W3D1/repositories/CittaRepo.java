package com.BEU2W2D3.gestioneprenotazioni.repositories;

import com.BEU2W2D3.gestioneprenotazioni.entities.Citta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CittaRepo extends JpaRepository<Citta, UUID> {
    Optional<Citta> findByNameIgnoreCase(String nome);

}
