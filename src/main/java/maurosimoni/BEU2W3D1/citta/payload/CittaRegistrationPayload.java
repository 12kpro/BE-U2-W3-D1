package maurosimoni.BEU2W3D1.citta.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class CittaRegistrationPayload {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String nome;

    public CittaRegistrationPayload(@NotNull(message = "Il nome è obbligatorio") String nome) {
        this.nome = nome;
    }
}
