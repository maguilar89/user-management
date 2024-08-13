package nisum.com.user_management.infrastructure.persistence.mapper;

import nisum.com.user_management.domain.User;
import nisum.com.user_management.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserPersistenceMapper {
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

}
