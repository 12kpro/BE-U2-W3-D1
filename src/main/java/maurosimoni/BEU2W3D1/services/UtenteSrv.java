package com.BEU2W2D3.gestioneprenotazioni.services;

import com.BEU2W2D3.gestioneprenotazioni.exceptions.BadRequestException;
import com.BEU2W2D3.gestioneprenotazioni.exceptions.NotFoundException;
import com.BEU2W2D3.gestioneprenotazioni.payloads.UtenteRegistrationPayload;
import com.BEU2W2D3.gestioneprenotazioni.repositories.UtenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtenteSrv {
    @Autowired
    private UtenteRepo utenteRepo;

    public Utente create(UtenteRegistrationPayload u) {
        utenteRepo.findByEmail(u.getEmail()).ifPresent(Utente -> {
            throw new BadRequestException("Email " + Utente.getEmail() + " già in uso!");
        });
        Utente newUtente = new Utente(u.getNome(), u.getCognome(), u.getEmail());
        return utenteRepo.save(newUtente);
    }

    public Page<Utente> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return utenteRepo.findAll(pageable);
    }

    public Utente findById(UUID id) throws NotFoundException {
        return utenteRepo.findById(id).orElseThrow(() -> new NotFoundException("Utente non trovato!"));
    }

    public Utente findByIdAndUpdate(UUID id, Utente u) throws NotFoundException {
        Utente found = this.findById(id);

        found.setId(id);
        found.setNome(u.getNome());
        found.setCognome(u.getCognome());
        found.setEmail(u.getEmail());

        return utenteRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Utente found = this.findById(id);
        utenteRepo.delete(found);
    }
}
