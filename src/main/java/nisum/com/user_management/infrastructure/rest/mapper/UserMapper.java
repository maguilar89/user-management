package nisum.com.user_management.infrastructure.rest.mapper;

import nisum.com.user_management.domain.User;
import nisum.com.user_management.infrastructure.rest.request.UserRequest;
import nisum.com.user_management.infrastructure.rest.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {PhoneMapper.class}
)
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "password", target = "password")
    @Mapping(source = "phones", target = "phones")
    User toDomain(UserRequest userRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "creationDate", target = "creationDate")
    @Mapping(source = "modifiedDate", target = "modifiedDate")
    @Mapping(source = "lastLogin", target = "lastLogin")
    @Mapping(source = "token", target = "token")
    @Mapping(source = "active", target = "active")
    UserResponse toResponse(User user);

}