
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UsersService usersService;
    @Autowired
    private PasswordEncoder bcrypt;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Validated UserRegistrationPayload body) {

        body.setPassword(bcrypt.encode(body.getPassword()));
        User createdUser = usersService.create(body);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthenticationSuccessfullPayload> login(@RequestBody maurosimoni.BEU2W2D5.users.payload.UserLoginPayload body)
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
