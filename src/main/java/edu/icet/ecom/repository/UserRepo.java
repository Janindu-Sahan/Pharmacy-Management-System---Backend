package edu.icet.ecom.repository;

import edu.icet.ecom.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepo extends JpaRepository<UserEntity, Long> {
}

