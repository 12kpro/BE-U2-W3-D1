package maurosimoni.BEU2W3D1.edifici;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maurosimoni.BEU2W3D1.citta.Citta;
import maurosimoni.BEU2W3D1.utils.CodeConverter;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "edifici")
@JsonIgnoreProperties({ "code"})
public class Edificio {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String indirizzo;
    @Convert(converter = CodeConverter.class)
    private String code;

    @ManyToOne(cascade = CascadeType.ALL)
    private Citta citta;

    public Edificio(String name, String indirizzo, Citta citta, String code) {
        this.name = name;
        this.indirizzo = indirizzo;
        this.citta = citta;
        this.code = code;
    }
}
