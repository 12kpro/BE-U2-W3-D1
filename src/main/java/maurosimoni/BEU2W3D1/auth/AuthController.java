package maurosimoni.BEU2W3D1.auth;

import maurosimoni.BEU2W3D1.auth.JWTTools;
import maurosimoni.BEU2W3D1.auth.payloads.AuthenticationSuccessfullPayload;
import maurosimoni.BEU2W3D1.exceptions.NotFoundException;
import maurosimoni.BEU2W3D1.exceptions.UnauthorizedException;
import maurosimoni.BEU2W3D1.users.User;
import maurosimoni.BEU2W3D1.users.UsersService;
import maurosimoni.BEU2W3D1.users.payload.UserCreatePayload;
import maurosimoni.BEU2W3D1.users.payload.UserLoginPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsersService usersService;
    @Autowired
    private PasswordEncoder bcrypt;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Validated UserCreatePayload body) {

        body.setPassword(bcrypt.encode(body.getPassword()));
        User createdUser = usersService.create(body);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody UserLoginPayload body)
            throws NotFoundException {

        User user = usersService.findByUserName(body.getUserName());

        String plainPW = body.getPassword();
        String hashedPW = user.getPassword();

        if (!bcrypt.matches(plainPW, hashedPW))
            throw new UnauthorizedException("Credenziali non valide");

        String token = JWTTools.createToken(user);
        return new ResponseEntity<>(new AuthenticationSuccessfullPayload(token), HttpStatus.OK);
    }

}
