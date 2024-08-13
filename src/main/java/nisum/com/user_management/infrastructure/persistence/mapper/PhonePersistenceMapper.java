package nisum.com.user_management.infrastructure.persistence.mapper;

import nisum.com.user_management.domain.Phone;
import nisum.com.user_management.infrastructure.persistence.entity.PhoneEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface PhonePersistenceMapper {

    PhoneEntity toEntity(Phone phone);

    Phone toDomain(PhoneEntity phoneEntity);

    List<PhoneEntity> toEntity(List<Phone> phone);

}
