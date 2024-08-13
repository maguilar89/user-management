package nisum.com.user_management.domain.port.create;

import nisum.com.user_management.domain.Phone;

import java.util.List;

public interface PhoneCreatorRepository {

    void save(List<Phone> phones);

}
