package com.commercial.logbook_app.repository;

import com.commercial.logbook_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
  User findByEmailAddress(String emailAddress);

  boolean existsByEmailAddressEquals(String emailAddress);
}
