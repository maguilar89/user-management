package nisum.com.user_management.application.create;

import nisum.com.user_management.domain.Phone;
import nisum.com.user_management.domain.User;
import nisum.com.user_management.domain.exception.RecordAlreadyExistException;
import nisum.com.user_management.domain.port.create.PhoneCreatorRepository;
import nisum.com.user_management.domain.port.create.UserCreatorRepository;
import nisum.com.user_management.domain.port.search.UserSearcherRepository;
import nisum.com.user_management.domain.port.security.TokenGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserCreatorTest {

    @InjectMocks
    private UserCreator userCreator;
    @Mock
    private UserCreatorRepository userCreatorRepository;
    @Mock
    private PhoneCreatorRepository phoneCreatorRepository;
    @Mock
    private UserSearcherRepository userSearcherRepository;
    @Mock
    private TokenGenerator generateToken;

    @Test
    public void whenSaveUser_GivenValidParameter_ThenReturnUserSaved() {
        // Arrange
        UUID userId = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();

        List<Phone> phones = Arrays.asList(
                Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .countryCode("57")
                        .build(),
                Phone.builder()
                        .number("987654321")
                        .cityCode("02")
                        .countryCode("57")
                        .build()
        );

        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .phones(phones)
                .build();

        User expectedUser = User.builder()
                .id(userId)
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .creationDate(now)
                .modifiedDate(now)
                .lastLogin(now)
                .active(true)
                .phones(phones)
                .build();

        when(userCreatorRepository.save(any(User.class))).thenReturn(expectedUser);
        when(userSearcherRepository.existEmail(any())).thenReturn(false);
        when(generateToken.generate(any())).thenReturn("");

        // Act
        User createdUser = userCreator.create(user);

        // Assert
        assertEquals(expectedUser, createdUser);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userCreatorRepository).save(userCaptor.capture());
        User savedUser = userCaptor.getValue();

        assertEquals(user.getName(), savedUser.getName());
        assertEquals(user.getEmail(), savedUser.getEmail());
        assertEquals(user.getPassword(), savedUser.getPassword());
    }

    @Test
    public void whenSaveUser_GivenNotValid_ThenReturnFailure() {
        // Arrange
        List<Phone> phones = Arrays.asList(
                Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .countryCode("57")
                        .build(),
                Phone.builder()
                        .number("987654321")
                        .cityCode("02")
                        .countryCode("57")
                        .build()
        );

        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .phones(phones)
                .build();

        // Simulate an exception when saving the user
        when(userCreatorRepository.save(any(User.class))).thenThrow(new RuntimeException("Failed to save user"));

        // Act & Assert
        assertThrows(RuntimeException.class, () -> userCreator.create(user));

        // Verify that the user save method was called
        verify(userCreatorRepository).save(any(User.class));

        // Verify that the phone save method was never called
        verify(phoneCreatorRepository, never()).save(any(List.class));
    }

    @Test
    public void whenSaveUser_GivenDuplicateEmail_ThenReturnFailure() {
        // Arrange
        List<Phone> phones = Arrays.asList(
                Phone.builder()
                        .number("123456789")
                        .cityCode("01")
                        .countryCode("57")
                        .build(),
                Phone.builder()
                        .number("987654321")
                        .cityCode("02")
                        .countryCode("57")
                        .build()
        );

        User user = User.builder()
                .name("John Doe")
                .email("john.doe@example.com")
                .password("password123")
                .phones(phones)
                .build();

        // Simulate an exception when saving the user
        when(userSearcherRepository.existEmail(any())).thenReturn(true);

        // Act & Assert
        assertThrows(RecordAlreadyExistException.class, () -> userCreator.create(user));

        // Verify that the user save method was called
        verify(userCreatorRepository, never()).save(any(User.class));

        // Verify that the phone save method was never called
        verify(phoneCreatorRepository, never()).save(any(List.class));
    }
}
