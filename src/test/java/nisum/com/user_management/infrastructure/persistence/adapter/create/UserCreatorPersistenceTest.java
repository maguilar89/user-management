package nisum.com.user_management.infrastructure.persistence.adapter.create;

import nisum.com.user_management.infrastructure.persistence.entity.UserEntity;
import nisum.com.user_management.infrastructure.persistence.mapper.UserPersistenceMapper;
import nisum.com.user_management.infrastructure.persistence.repository.UserJpaRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class UserCreatorPersistenceTest {

    @Autowired
    private UserJpaRepository userJpaRepository;
    @Mock
    private UserPersistenceMapper mapper;

    @BeforeEach
    void setUp() {
        new UserCreatorPersistence(userJpaRepository, mapper);
    }

    @Test
    void whenSaveUser_GivenValidParameter_ThenReturnUserSaved() {
        // Arrange
        EasyRandom easyRandom = new EasyRandom();
        UserEntity userEntity = userJpaRepository.save(easyRandom.nextObject(UserEntity.class));

        // Verify the interactions and assert the results
        assertNotNull(userEntity);
    }
}
