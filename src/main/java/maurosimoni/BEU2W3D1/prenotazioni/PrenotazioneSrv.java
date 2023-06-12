package maurosimoni.BEU2W3D1.prenotazioni;

import maurosimoni.BEU2W3D1.exceptions.BadRequestException;
import maurosimoni.BEU2W3D1.exceptions.NotFoundException;
import maurosimoni.BEU2W3D1.postazioni.Postazione;
import maurosimoni.BEU2W3D1.postazioni.PostazioneSrv;
import maurosimoni.BEU2W3D1.prenotazioni.payload.PrenotazioneRegistrationPayload;
import maurosimoni.BEU2W3D1.users.User;
import maurosimoni.BEU2W3D1.users.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class PrenotazioneSrv {
    @Autowired
    private PrenotazioneRepo prenotazioneRepo;
    @Autowired
    private PostazioneSrv postazioneSrv;
    @Autowired
    private UsersService utenteSrv;
    public Prenotazione create(PrenotazioneRegistrationPayload p) throws BadRequestException {
        this.findByDataAndPostazione(p.getPostazione(), p.getData());
        this.findByUtenteAndData( p.getData(), p.getUtente());
//        if ( p.getData().isAfter(date.plusDays(2))){
//            throw new BadRequestException("data non valida");
//        }
        Postazione postazione = postazioneSrv.findById(p.getPostazione());
        User usr = utenteSrv.findById(p.getUtente());

        Prenotazione newPrenotazione = new Prenotazione(p.getData(),postazione,usr);

        return prenotazioneRepo.save(newPrenotazione);
    }
    public Page<Prenotazione> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return prenotazioneRepo.findAll(pageable);
    }
    public void findByDataAndPostazione(UUID postazione, LocalDate data) throws BadRequestException {
        prenotazioneRepo.findByDataAndPostazione_Id(data, postazione).ifPresent(value -> new BadRequestException("L'utente ha già una prenotazione attiva nella data richiesta"));
    }
    public void findByUtenteAndData(LocalDate data, UUID utente) throws BadRequestException {
        prenotazioneRepo.findByUtente_IdAndData(utente, data).ifPresent(value -> new BadRequestException("Postazione già occupata nella data richiesta!"));
    }
    public Prenotazione findById(UUID id) throws NotFoundException {
        return prenotazioneRepo.findById(id).orElseThrow(() -> new NotFoundException("Prenotazione non trovata!"));
    }
    public Prenotazione findByIdAndUpdate(UUID id, Prenotazione p) throws NotFoundException {
        Prenotazione found = this.findById(id);

        //found.setId(id);
        found.setData(p.getData());
        found.setUtente(p.getUtente());
        found.setPostazione(p.getPostazione());

        return prenotazioneRepo.save(found);
    }
    public void findByIdAndDelete(UUID id) {
        Prenotazione found = this.findById(id);
        prenotazioneRepo.delete(found);
    }

}
