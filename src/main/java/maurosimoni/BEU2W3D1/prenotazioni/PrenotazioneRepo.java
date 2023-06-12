package maurosimoni.BEU2W3D1.prenotazioni;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PrenotazioneRepo extends JpaRepository<Prenotazione, UUID> {
     Optional<Prenotazione> findByUtente_IdAndData(UUID id, LocalDate data);

    Optional<Prenotazione> findByDataAndPostazione_Id(LocalDate data, UUID id);


}
