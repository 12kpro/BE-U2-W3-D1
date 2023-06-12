package maurosimoni.BEU2W3D1.edifici;

import maurosimoni.BEU2W3D1.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EdificioSrv {

    @Autowired
    private EdificioRepo edificioRepo;

    public void create(Edificio e) {
        edificioRepo.save(e);
    }
    public void createFromList(List<Edificio> e) {
        edificioRepo.saveAll(e);
    }
    public Edificio findById(UUID id) {
        return edificioRepo.findById(id).orElseThrow(() -> new NotFoundException("Edificio non trovato!"));
    }
    public Edificio findByIdAndUpdate(UUID id, Edificio e) throws NotFoundException {
        Edificio found = this.findById(id);

        found.setId(id);
        found.setName(e.getName());
        found.setIndirizzo(e.getIndirizzo());
        found.setCitta(e.getCitta());
        found.setCode(e.getCode());

        return EdificioRepo.save(found);
    }
    public List<Edificio> findAll(){
        return edificioRepo.findAll();
    }

    public void findByIdAndDelete(UUID id) {
        Edificio found = this.findById(id);
        edificioRepo.delete(found);
    }
}
