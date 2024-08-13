package nisum.com.user_management.infrastructure.persistence.repository;

import nisum.com.user_management.infrastructure.persistence.entity.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhoneJpaRepository extends JpaRepository<PhoneEntity, UUID> {
}
