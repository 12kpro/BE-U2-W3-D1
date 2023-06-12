package maurosimoni.BEU2W3D1.prenotazioni;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maurosimoni.BEU2W3D1.postazioni.Postazione;
import maurosimoni.BEU2W3D1.users.User;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prenotazioni")
public class Prenotazione {
    @Id
    @GeneratedValue
    private UUID id;
    private LocalDate data;
    @ManyToOne(cascade = CascadeType.ALL)
    private Postazione postazione;
    @ManyToOne(cascade = CascadeType.ALL)
    private User utente;

    public Prenotazione(LocalDate data, Postazione postazione, User utente) {
        this.data = data;
        this.postazione = postazione;
        this.utente = utente;
    }
}
