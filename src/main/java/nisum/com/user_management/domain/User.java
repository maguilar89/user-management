package nisum.com.user_management.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Value
@Builder
public class User {

    @With
    UUID id;
    String name;
    String email;
    String password;
    List<Phone> phones;
    LocalDateTime modifiedDate;
    LocalDateTime creationDate;
    LocalDateTime lastLogin;
    @With
    String token;
    @With
    boolean active;

}
