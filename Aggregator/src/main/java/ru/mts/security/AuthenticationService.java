package ru.mts.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.mts.entity.MyUser;
import ru.mts.service.AggregatorService;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final AggregatorService aggregatorService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        MyUser user = new MyUser(
                request.getNumBankAccount(),
                request.getPhoneNumber(),
                passwordEncoder.encode(request.getPassword())
        );

        aggregatorService.saveUser(user);
        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getPhoneNumber(), request.getPassword()));

        MyUser user = aggregatorService.findUserByPhoneNumber(request.getPhoneNumber());
        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }
}
