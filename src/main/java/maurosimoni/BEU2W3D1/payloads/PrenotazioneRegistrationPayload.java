package com.BEU2W2D3.gestioneprenotazioni.payloads;

import com.BEU2W2D3.gestioneprenotazioni.validators.PrenotazioneDateConstraint;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
public class PrenotazioneRegistrationPayload {

    @NotNull(message = "La data è obbligatoria")
    @PrenotazioneDateConstraint
    private LocalDate data;
    @NotNull(message = "La postazione è obbligatoria")
    private UUID postazione;
    @NotNull(message = "L'utente è obbligatorio")
    private UUID utente;

    public PrenotazioneRegistrationPayload(@NotNull(message = "La data è obbligatoria") LocalDate data, @NotNull(message = "La postazione è obbligatoria") UUID postazione, @NotNull(message = "L'utente è obbligatorio") UUID utente) {
        this.data = data;
        this.postazione = postazione;
        this.utente = utente;
    }
}
