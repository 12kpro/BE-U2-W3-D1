package com.BEU2W2D3.gestioneprenotazioni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Citta {
    @Id
    @GeneratedValue
    private UUID id;
    private String nome;

    public Citta(String nome) {
        this.nome = nome;
    }
}
