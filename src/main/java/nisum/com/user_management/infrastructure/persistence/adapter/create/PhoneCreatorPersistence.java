package nisum.com.user_management.infrastructure.persistence.adapter.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nisum.com.user_management.domain.Phone;
import nisum.com.user_management.domain.port.create.PhoneCreatorRepository;
import nisum.com.user_management.infrastructure.persistence.mapper.PhonePersistenceMapper;
import nisum.com.user_management.infrastructure.persistence.repository.PhoneJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Repository
public class PhoneCreatorPersistence implements PhoneCreatorRepository {

    private final PhoneJpaRepository phoneJpaRepository;

    private final PhonePersistenceMapper phonePersistenceMapper;

    @Override
    public void save(List<Phone> phones) {
         phoneJpaRepository.saveAll(phonePersistenceMapper.toEntity(phones));
    }
}
