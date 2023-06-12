package com.BEU2W2D3.gestioneprenotazioni.repositories;

import com.BEU2W2D3.gestioneprenotazioni.entities.Citta;
import com.BEU2W2D3.gestioneprenotazioni.entities.Postazione;
import com.BEU2W2D3.gestioneprenotazioni.utils.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PostazioneRepo extends JpaRepository<Postazione, UUID> {
    List<Postazione> findByTipoAndEdificio_Citta_NameIgnoreCase(TipoPostazione tipo, String name);

    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, Citta citta);

}
