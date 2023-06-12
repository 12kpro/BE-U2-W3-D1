package com.BEU2W2D3.gestioneprenotazioni.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UtenteRegistrationPayload {
    @NotNull(message = "Il nome è obbligatorio")
    @Size(min = 3, max = 30, message = "Nome min 3 caratteri, massimo 30")
    String nome;
    @NotNull(message = "Il cognome è obbligatorio")
    String cognome;
    @Email(message = "Non hai inserito un indirizzo email valido")
    String email;

    public UtenteRegistrationPayload(@NotNull(message = "Il nome è obbligatorio") String nome, @NotNull(message = "Il cognome è obbligatorio") String cognome, String email) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
    }
}
