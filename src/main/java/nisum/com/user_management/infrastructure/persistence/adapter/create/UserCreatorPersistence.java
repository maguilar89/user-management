package nisum.com.user_management.infrastructure.persistence.adapter.create;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nisum.com.user_management.domain.User;
import nisum.com.user_management.domain.port.create.UserCreatorRepository;
import nisum.com.user_management.infrastructure.persistence.mapper.UserPersistenceMapper;
import nisum.com.user_management.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Slf4j
@Repository
public class UserCreatorPersistence implements UserCreatorRepository {

    private final UserJpaRepository userJpaRepository;
    private final UserPersistenceMapper mapper;

    @Override
    public User save(User user) {
        return mapper.toDomain(userJpaRepository.save(mapper.toEntity(user)));
    }
}
