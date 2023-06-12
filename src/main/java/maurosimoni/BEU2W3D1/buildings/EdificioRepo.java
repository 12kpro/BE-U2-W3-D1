package maurosimoni.BEU2W3D1.buildings;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EdificioRepo extends JpaRepository<Edificio, UUID> {
}
