package com.commercial.logbook_app.service;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.commercial.logbook_app.config.USER_TYPE;
import com.commercial.logbook_app.dto.UserDTO;
import com.commercial.logbook_app.model.User;
import com.commercial.logbook_app.repository.UserRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock private UserRepository userRepository;

  @Mock private FileStorageService fileStorageService;

  @Mock private EmailService emailService;

  @Mock private PasswordEncoder passwordEncoder;

  private UserService userService;

  @BeforeEach
  void setUp() {
    this.userService =
        new UserService(
            this.userRepository, this.fileStorageService, this.emailService, this.passwordEncoder);
  }

  @Test
  public void testGetAll() {
    // GIVEN
    // 1.  Prepare Test Data
    User user1 = new User();
    user1.setId(1);
    user1.setFirstName("Test1 First Name");
    user1.setLastName("Test1 Last Name");
    user1.setEmailAddress("test1@gmail.com");
    user1.setPassword("Test1 Password");
    user1.setProfilePicLocation("Test1 Profile Pic");
    user1.setType(USER_TYPE.GENERAL.getType());

    List<User> users = List.of(user1);

    when(userRepository.findAll()).thenReturn(users);

    // WHEN
    // 2.  Calling the method to test
    List<UserDTO> result = userService.getAll();

    // THEN
    // 3.  Validate the results
    verify(userRepository).findAll();
    assert result.size() == 1;
    assert result.getFirst().getId() == 1;
    assert result.getFirst().getFirstName().equals("Test1 First Name");
    assert result.getFirst().getLastName().equals("Test1 Last Name");
    assert result.getFirst().getEmailAddress().equals("test1@gmail.com");
    assert result.getFirst().getPassword() == null;
    assert result.getFirst().getProfilePicLocation().equals("Test1 Profile Pic");
    assert result.getFirst().getType().equals(USER_TYPE.GENERAL.getType());
  }
}
