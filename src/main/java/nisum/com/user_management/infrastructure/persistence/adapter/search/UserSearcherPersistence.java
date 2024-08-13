package nisum.com.user_management.infrastructure.persistence.adapter.search;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nisum.com.user_management.domain.port.search.UserSearcherRepository;
import nisum.com.user_management.infrastructure.persistence.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Slf4j
@Repository
public class UserSearcherPersistence implements UserSearcherRepository {

    private final UserJpaRepository userJpaRepository;

    @Override
    public boolean existEmail(String email) {
        return userJpaRepository.existsByEmail(email);
    }
}
