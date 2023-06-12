package com.BEU2W2D3.gestioneprenotazioni.controllers;

import com.BEU2W2D3.gestioneprenotazioni.payloads.UtenteRegistrationPayload;
import com.BEU2W2D3.gestioneprenotazioni.services.UtenteSrv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
@RestController
@RequestMapping("/utenti")
public class UtenteController {
    @Autowired
    private UtenteSrv usersService;

    @GetMapping("")
    public Page<Utente> getUsers(@RequestParam(defaultValue = "0") int page,
                                 @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String sortBy) {
        return usersService.find(page, size, sortBy);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Utente saveUser(@RequestBody @Validated UtenteRegistrationPayload body) {
        return usersService.create(body);
    }

    @GetMapping("/{userId}")
    public Utente getUser(@PathVariable UUID userId) throws Exception {
        return usersService.findById(userId);
    }

    @PutMapping("/{userId}")
    public Utente updateUser(@PathVariable UUID userId, @RequestBody Utente body) throws Exception {
        return usersService.findByIdAndUpdate(userId, body);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        usersService.findByIdAndDelete(userId);
    }
}
