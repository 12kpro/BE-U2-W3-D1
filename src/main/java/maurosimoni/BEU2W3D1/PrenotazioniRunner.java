package com.BEU2W2D3.gestioneprenotazioni;

import com.BEU2W2D3.gestioneprenotazioni.entities.Citta;
import com.BEU2W2D3.gestioneprenotazioni.entities.Edificio;
import com.BEU2W2D3.gestioneprenotazioni.payloads.CittaRegistrationPayload;
import com.BEU2W2D3.gestioneprenotazioni.payloads.UtenteRegistrationPayload;
import com.BEU2W2D3.gestioneprenotazioni.services.*;
import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;
import maurosimoni.BEU2W3D1.buildings.CodeConverter;
import maurosimoni.BEU2W3D1.buildings.Edificio;
import maurosimoni.BEU2W3D1.buildings.EdificioSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class PrenotazioniRunner implements CommandLineRunner {
    @Autowired
    UtenteSrv utenteSrv;
    @Autowired
    CittaSrv cittaSrv;
    @Autowired
    EdificioSrv edificioSrv;
    @Autowired
    PostazioneSrv postazioneSrv;
    @Autowired
    PrenotazioneSrv prenotazioneSrv;

//    @Autowired
//    CodeConverter codeConverter;
    Faker faker = new Faker(new Locale("it"));

    private Boolean auto = false;
    @Override
    public void run(String... args) throws Exception {

        if(auto){
            createUsers();
        }


        List<Edificio> edifici = edificioSrv.findAll();
        edifici.forEach(e -> {
            edificioSrv.findByIdAndUpdate(e.getId(),e);
        });

    }
    public void createUsers(){
        for (int i = 0; i < 3; i++) {
            try {

                String name = faker.name().firstName();
                String surname = faker.name().lastName();
                String email = faker.internet().emailAddress();
                UtenteRegistrationPayload user = new UtenteRegistrationPayload(name, surname, email);
                utenteSrv.create(user);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }




}
