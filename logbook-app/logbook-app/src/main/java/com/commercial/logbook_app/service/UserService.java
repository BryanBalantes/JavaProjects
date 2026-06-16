package com.commercial.logbook_app.service;

import com.commercial.logbook_app.dto.AuthenticatedUserDTO;
import com.commercial.logbook_app.dto.UserDTO;
import com.commercial.logbook_app.model.User;
import com.commercial.logbook_app.model.enums.LeaseStatus;
import com.commercial.logbook_app.repository.LeaseRepository;
import com.commercial.logbook_app.repository.UserRepository;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.commercial.logbook_app.response.CloudinaryResponse;
import com.commercial.logbook_app.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

  private UserRepository userRepository;

  /*
   * add this private FileStorageService fileStorageService;
   * after you add  private String profilePicLocation; in UserDTO and
   * User model
   * */
//  private FileStorageService fileStorageService;

  private CloudinaryService cloudinaryService;

  /*
   * This is for sending the email
   * */
  private EmailService emailService;

  /*
   * This is for password encoder
   * */
  private PasswordEncoder passwordEncoder;

  private LeaseRepository leaseRepository;

  /*
   * This is for the link location of the image
   * The logbook-app.user.profile-loc is a variable name
   * located in application.properties where its value is the link
   * location of the image.
   * */
//  @Value("${logbook-app.user.profile-loc}")
//  public String USER_IMG_LOCATION;
//
//  public UserService(
//      UserRepository userRepository,
//      FileStorageService fileStorageService,
//      EmailService emailService,
//      PasswordEncoder passwordEncoder) {
//    this.userRepository = userRepository;
//    this.fileStorageService = fileStorageService;
//    this.emailService = emailService;
//    this.passwordEncoder = passwordEncoder;
//  }

  public UserService(
          UserRepository userRepository,
          CloudinaryService cloudinaryService,
          EmailService emailService,
          PasswordEncoder passwordEncoder,
          LeaseRepository leaseRepository) {

    this.userRepository = userRepository;
    this.cloudinaryService = cloudinaryService;
    this.emailService = emailService;
    this.passwordEncoder = passwordEncoder;
    this.leaseRepository = leaseRepository;
  }

//  public List<UserDTO> getAll() {
//    return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
//  }

//  public List<UserDTO> getAll() {
//
//    List<UserDTO> users = userRepository.findAll()
//            .stream()
//            .map(UserDTO::new)
//            .toList();
//
//    for (UserDTO user : users) {
//
//      boolean hasActiveLease =
//              leaseRepository.existsByUser_IdAndStatus(
//                      user.getId(),
//                      LeaseStatus.ACTIVE
//              );
//
//      user.setActive(hasActiveLease);
//    }
//
//    return users;
//  }

  public List<UserDTO> getAll() {

    List<UserDTO> users = userRepository.findAll()
            .stream()
            .map(UserDTO::new)
            .toList();

    for (UserDTO user : users) {

      // ADMIN is always active
      if ("ADMIN".equalsIgnoreCase(user.getType())) {

        user.setActive(true);

      } else {

        // GENERAL depends on lease status
        boolean hasActiveLease =
                leaseRepository.existsByUser_IdAndStatus(
                        user.getId(),
                        LeaseStatus.ACTIVE
                );

        user.setActive(hasActiveLease);
      }
    }

    return users;
  }

  public long countActiveTenants() {

    return userRepository.findAll()
            .stream()

            // exclude ADMIN
            .filter(user ->
                    !"ADMIN".equalsIgnoreCase(user.getType())
            )

            // only users with ACTIVE lease
            .filter(user ->
                    leaseRepository.existsByUser_IdAndStatus(
                            user.getId(),
                            LeaseStatus.ACTIVE
                    )
            )

            .count();
  }

//  public UserDTO getById(int id) {
//    return new UserDTO(userRepository.findById(id).get());
//  }

  public UserDTO getById(int id) {

    UserDTO user = new UserDTO(
            userRepository.findById(id).get()
    );

    // ADMIN is always active
    if ("ADMIN".equalsIgnoreCase(user.getType())) {

      user.setActive(true);

    } else {

      boolean hasActiveLease =
              leaseRepository.existsByUser_IdAndStatus(
                      user.getId(),
                      LeaseStatus.ACTIVE
              );

      user.setActive(hasActiveLease);
    }

    return user;
  }

  public void create(UserDTO userDTO) {

    System.out.println("IMAGE: " + userDTO.getImage());

    if (userDTO.getImage() != null) {
      System.out.println("FILENAME: " +
              userDTO.getImage().getOriginalFilename());
    }

    User model = new User(userDTO);
    model.setPassword(passwordEncoder.encode(userDTO.getPassword()));

    /*
     * This is for storing images
     * This will save or upload the image first before the user. Because if
     * nagkaroon ng problem sa image hindi matutuloy yung pag save.
     * */
//    try {
//      String fileName = fileStorageService.store(userDTO.getImage(), USER_IMG_LOCATION);
//      model.setProfilePicLocation(fileName);
//    } catch (IOException e) {
//      throw new RuntimeException(e);
//    }

    try {
      if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {

        // validate file
        FileUploadUtil.assertAllowed(userDTO.getImage());

        // generate filename
        String fileName = FileUploadUtil.getFileName(userDTO.getImage().getOriginalFilename());

        // upload to Cloudinary
        CloudinaryResponse response =
                cloudinaryService.uploadFile(userDTO.getImage(), fileName);

        // save to DB
        model.setProfilePicUrl(response.getUrl());
        model.setCloudinaryProfilePicId(response.getPublicId());
      }

    } catch (Exception e) {
      throw new RuntimeException("Image upload failed: " + e.getMessage());
    }

    if (!userRepository.existsByEmailAddressEquals(userDTO.getEmailAddress())) {

      userRepository.save(model);

      emailService.sendSimpleEmail(
          userDTO.getEmailAddress(),
          "Welcome to Log Book App",
          "Start creating your logs/record today!");
    } else {
      userDTO.addError("Email Address is already used.");
    }
  }

  /*
      This is also part of the image upload
      This will retrieve the image base on the id of the user
  */
//  public byte[] getUserImage(int id) throws IOException {
//    return fileStorageService.get(getById(id).getProfilePicLocation(), USER_IMG_LOCATION);
//  }

//  public void update(UserDTO userDTO) {
//    User model = new User(userDTO);
//    userRepository.save(model);
//  }

  public void update(UserDTO userDTO) {
    User existing = userRepository.findById(userDTO.getId())
            .orElseThrow(() -> new RuntimeException("User not found"));

    // update basic fields
    existing.setFirstName(userDTO.getFirstName());
    existing.setLastName(userDTO.getLastName());
    existing.setMiddleName(userDTO.getMiddleName());
    existing.setAddress(userDTO.getAddress());
    existing.setContactNumber(userDTO.getContactNumber());
    existing.setEmailAddress(userDTO.getEmailAddress());

    try {
      if (userDTO.getImage() != null && !userDTO.getImage().isEmpty()) {

        // delete old image
        if (existing.getCloudinaryProfilePicId() != null) {
          cloudinaryService.deleteFile(existing.getCloudinaryProfilePicId());
        }

        FileUploadUtil.assertAllowed(userDTO.getImage());
        String fileName = FileUploadUtil.getFileName(userDTO.getImage().getOriginalFilename());

        CloudinaryResponse response =
                cloudinaryService.uploadFile(userDTO.getImage(), fileName);

        existing.setProfilePicUrl(response.getUrl());
        existing.setCloudinaryProfilePicId(response.getPublicId());
      }

    } catch (Exception e) {
      throw new RuntimeException("Update failed: " + e.getMessage());
    }

    userRepository.save(existing);
  }

  public void delete(int id) {
    userRepository.deleteById(id);
  }

  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = userRepository.findByEmailAddress(username);
    return new AuthenticatedUserDTO(
        user.getEmailAddress(), user.getPassword(), user.getType(), user.getFirstName());
  }

  public List<UserDTO> getActiveUsers() {

    return getAll()
            .stream()
            .filter(user -> user.isActive())
            .filter(user -> !"ADMIN".equalsIgnoreCase(user.getType()))
            .toList();
  }
}
