package nisum.com.user_management.infrastructure.rest.mapper;

import nisum.com.user_management.domain.Phone;
import nisum.com.user_management.infrastructure.rest.request.UserRequest;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PhoneMapper {

    Phone toDomain(UserRequest.PhoneRequest phoneRequest);

    UserRequest.PhoneRequest toResponse(Phone phone);

}
