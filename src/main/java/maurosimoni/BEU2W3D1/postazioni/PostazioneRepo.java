package maurosimoni.BEU2W3D1.postazioni;

import maurosimoni.BEU2W3D1.citta.Citta;
import maurosimoni.BEU2W3D1.postazioni.Postazione;
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
