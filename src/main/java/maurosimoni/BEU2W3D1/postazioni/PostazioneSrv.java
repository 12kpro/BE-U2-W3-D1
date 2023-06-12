package maurosimoni.BEU2W3D1.postazioni;

import maurosimoni.BEU2W3D1.exceptions.BadRequestException;
import maurosimoni.BEU2W3D1.exceptions.NotFoundException;
import maurosimoni.BEU2W3D1.postazioni.payload.PostazioneRegistrationPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostazioneSrv {
    @Autowired
    private PostazioneRepo postazioneRepo;

    public Postazione create(PostazioneRegistrationPayload p) {
        postazioneRepo.findByDescrizione(p.getDescrizione()).ifPresent(Postazione -> {
            throw new BadRequestException("Postazione " + Postazione. + " già in presente!");
        });
        Postazione newPostazione = new Postazione(u.getNome(), u.getCognome(), u.getEmail());
        return postazioneRepo.save(newPostazione);
    }

    public Page<Postazione> find(int page, int size, String sortBy) {
        if (size < 0)
            size = 10;
        if (size > 100)
            size = 100;
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return postazioneRepo.findAll(pageable);
    }

    public Postazione findById(UUID id) throws NotFoundException {
        return postazioneRepo.findById(id).orElseThrow(() -> new NotFoundException("Postazione non trovato!"));
    }

    public Postazione findByIdAndUpdate(UUID id, Postazione u) throws NotFoundException {
        Postazione found = this.findById(id);

        found.setId(id);
        found.setNome(u.getNome());
        found.setCognome(u.getCognome());
        found.setEmail(u.getEmail());

        return postazioneRepo.save(found);
    }

    public void findByIdAndDelete(UUID id) throws NotFoundException {
        Postazione found = this.findById(id);
        postazioneRepo.delete(found);
    }

}
