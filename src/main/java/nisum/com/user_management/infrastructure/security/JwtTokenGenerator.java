package nisum.com.user_management.infrastructure.security;

import lombok.RequiredArgsConstructor;
import nisum.com.user_management.domain.port.security.TokenGenerator;
import nisum.com.user_management.infrastructure.config.JwtTokenUtil;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JwtTokenGenerator implements TokenGenerator {

    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public String generate(String userName) {
        return jwtTokenUtil.generateToken(userName);
    }
}
