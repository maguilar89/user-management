package nisum.com.user_management.application.create;

import lombok.RequiredArgsConstructor;
import nisum.com.user_management.domain.Phone;
import nisum.com.user_management.domain.User;
import nisum.com.user_management.domain.port.create.PhoneCreatorRepository;
import nisum.com.user_management.domain.port.create.UserCreatorRepository;
import nisum.com.user_management.domain.port.search.UserSearcherRepository;
import nisum.com.user_management.domain.port.security.TokenGenerator;
import nisum.com.user_management.domain.exception.RecordAlreadyExistException;
import nisum.com.user_management.infrastructure.rest.message.CustomMessageHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserCreator {

    private static final String MSG_EMAIL_ALREADY_EXIST = "MSG_EMAIL_ALREADY_EXIST";

    private final UserCreatorRepository userCreatorRepository;
    private final PhoneCreatorRepository phoneCreatorRepository;
    private final UserSearcherRepository userSearcherRepository;
    private final TokenGenerator generateToken;

    @Transactional
    public User create(User user) {
        UUID userId = UUID.randomUUID();
        checkIfEmailAlreadyExist(user.getEmail());
        String token = generateToken.generate(user.getName());
        User userToSave = user.withId(userId).withActive(true).withToken(token);
        User userSaved = userCreatorRepository.save(userToSave);
        savePhones(user.getPhones(), userId);
        return userSaved;
    }

    private void checkIfEmailAlreadyExist(String email) {

        if (userSearcherRepository.existEmail(email)) {
            throw new RecordAlreadyExistException(CustomMessageHandler.getInstance()
                    .resolve(MSG_EMAIL_ALREADY_EXIST));
        }
    }

    private void savePhones(List<Phone> phones, UUID userID) {
        phoneCreatorRepository.save(phones.stream()
                .map(phone -> phone.withUserId(userID).withId(UUID.randomUUID()))
                .collect(Collectors.toList()));
    }

}
