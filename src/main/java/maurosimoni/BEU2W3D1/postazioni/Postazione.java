package maurosimoni.BEU2W3D1.postazioni;

import com.BEU2W2D3.gestioneprenotazioni.utils.TipoPostazione;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maurosimoni.BEU2W3D1.edifici.Edificio;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "postazioni")
public class Postazione {
    @Id
    @GeneratedValue
    private UUID id ;
    private String descrizione;
    private TipoPostazione tipo;
    private Integer occupanti;
    @ManyToOne
    private Edificio edificio;

    public Postazione(String descrizione, TipoPostazione tipo, Integer occupanti, Edificio edificio) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.occupanti = occupanti;
        this.edificio = edificio;
    }
}
