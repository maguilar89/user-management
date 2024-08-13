package nisum.com.user_management.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;
import lombok.With;

import java.util.UUID;

@Value
@Builder
public class Phone {
    @With
    UUID id;
    String number;
    String cityCode;
    String countryCode;
    @With
    UUID userId;

}
