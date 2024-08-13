package nisum.com.user_management.infrastructure.rest.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import nisum.com.user_management.infrastructure.rest.util.UserRequestUtil;

import java.util.List;

@Value
@Builder
public class UserRequest {

    @NotBlank
    String name;
    @Email(message = UserRequestUtil.MESSAGE_EMAIL, regexp = UserRequestUtil.REGULAR_EXPRESSION_EMAIL)
    @NotEmpty(message = UserRequestUtil.MESSAGE_EMAIL)
    String email;
    @NotBlank(message = UserRequestUtil.PASSWORD_EMPTY)
    @Pattern(regexp = UserRequestUtil.REGULAR_EXPRESSION_PASSWORD, message = UserRequestUtil.MESSAGE_EXPRESSION_PASSWORD)
    String password;
    List<PhoneRequest> phones;

    @Builder
    @Data
    public static class PhoneRequest {
        String number;
        @JsonProperty(UserRequestUtil.CITY_CODE)
        String cityCode;
        @JsonProperty(UserRequestUtil.COUNTY_CODE)
        String countryCode;
    }
}
