package maurosimoni.BEU2W3D1.postazioni.payload;

import com.BEU2W2D3.gestioneprenotazioni.entities.Edificio;
import com.BEU2W2D3.gestioneprenotazioni.utils.TipoPostazione;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PostazioneRegistrationPayload {
    @NotNull(message = "La descrizione è obbligatoria")
    @Size(min = 3, max = 30, message = "Descrizione min 3 caratteri, massimo 30")
    private String descrizione;
    @NotNull(message = "Il tipo è obbligatorio")
    private TipoPostazione tipo;
    @NotNull(message = "Il numero di occupanti è obbligatorio")
    private Integer occupanti;
    @NotNull(message = "L'edificio è obbligatorio")
    private Edificio edificio;

    public PostazioneRegistrationPayload(@NotNull(message = "La descrizione è obbligatoria") String descrizione, @NotNull(message = "Il tipo è obbligatorio") TipoPostazione tipo, @NotNull(message = "Il numero di occupanti è obbligatorio") Integer occupanti, @NotNull(message = "L'edificio è obbligatorio") Edificio edificio) {
        this.descrizione = descrizione;
        this.tipo = tipo;
        this.occupanti = occupanti;
        this.edificio = edificio;
    }
}
