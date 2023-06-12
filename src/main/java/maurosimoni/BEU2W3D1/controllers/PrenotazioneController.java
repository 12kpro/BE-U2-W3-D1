package com.BEU2W2D3.gestioneprenotazioni.controllers;

import com.BEU2W2D3.gestioneprenotazioni.entities.Citta;
import com.BEU2W2D3.gestioneprenotazioni.entities.Prenotazione;
import com.BEU2W2D3.gestioneprenotazioni.payloads.CittaRegistrationPayload;
import com.BEU2W2D3.gestioneprenotazioni.payloads.PrenotazioneRegistrationPayload;
import com.BEU2W2D3.gestioneprenotazioni.services.CittaSrv;
import com.BEU2W2D3.gestioneprenotazioni.services.PostazioneSrv;
import com.BEU2W2D3.gestioneprenotazioni.services.PrenotazioneSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {
    @Autowired
    private PrenotazioneSrv prenotazioneSrv;
    @GetMapping("")
    public Page<Prenotazione> getPrenotazioni(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return prenotazioneSrv.find(page, size, sortBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione savePrenotazione(@RequestBody @Validated PrenotazioneRegistrationPayload body) {
        return prenotazioneSrv.create(body);
    }

    @GetMapping("/{prenotazioneId}")
    public Prenotazione getPrenotazione(@PathVariable UUID prenotazioneId) throws Exception {
        return prenotazioneSrv.findById(prenotazioneId);
    }

    @PutMapping("/{prenotazioneId}")
    public Prenotazione updatePrenotazione(@PathVariable UUID prenotazioneId, @RequestBody Prenotazione body) throws Exception {
        return prenotazioneSrv.findByIdAndUpdate(prenotazioneId, body);
    }

    @DeleteMapping("/{prenotazioneId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePrenotazione(@PathVariable UUID prenotazioneId) {
        prenotazioneSrv.findByIdAndDelete(prenotazioneId);
    }
}
