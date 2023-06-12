package maurosimoni.BEU2W3D1.postazioni;


import maurosimoni.BEU2W3D1.edifici.Edificio;
import maurosimoni.BEU2W3D1.utils.TipoPostazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Repository
public interface PostazioneRepo extends JpaRepository<Postazione, UUID> {
    Optional<Postazione> findByTipoAndEdificio(TipoPostazione tipo, Edificio edificio);

//    List<Postazione> findByTipoAndEdificio_Citta_NameIgnoreCase(TipoPostazione tipo, String name);
//
//    List<Postazione> findByTipoAndEdificio_Citta(TipoPostazione tipo, Citta citta);

}
