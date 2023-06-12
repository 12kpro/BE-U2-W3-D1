package maurosimoni.BEU2W3D1.citta;

import maurosimoni.BEU2W3D1.citta.Citta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface CittaRepo extends JpaRepository<Citta, UUID> {
    Optional<Citta> findByNameIgnoreCase(String nome);

}
