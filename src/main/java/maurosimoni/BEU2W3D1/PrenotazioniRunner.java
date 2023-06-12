


import com.github.javafaker.Faker;
import lombok.extern.slf4j.Slf4j;

import maurosimoni.BEU2W3D1.citta.CittaSrv;
import maurosimoni.BEU2W3D1.edifici.Edificio;
import maurosimoni.BEU2W3D1.edifici.EdificioSrv;
import maurosimoni.BEU2W3D1.postazioni.PostazioneSrv;
import maurosimoni.BEU2W3D1.prenotazioni.PrenotazioneSrv;
import maurosimoni.BEU2W3D1.users.UsersService;
import maurosimoni.BEU2W3D1.users.payload.UserCreatePayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;

@Slf4j
@Component
public class PrenotazioniRunner implements CommandLineRunner {
    @Autowired
    UsersService utenteSrv;
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
                String username = faker.name().username();
                String email = faker.internet().emailAddress();
                String password = "1234";
                UserCreatePayload user = new UserCreatePayload(name, surname, username, email, password);
                utenteSrv.create(user);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }




}
