package nisum.com.user_management.domain.port.security;

public interface TokenGenerator {

    String generate(String userName);

}
