package com.commercial.logbook_app.model;

import com.commercial.logbook_app.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(nullable = false, length = 20)
  private String firstName;

  @Column(nullable = false, length = 20)
  private String lastName;

  @Column(nullable = false, length = 20)
  private String middleName;

  @Column(nullable = false, length = 200)
  private String address;

  @Column(nullable = false, unique = true)
  private String emailAddress;

  @Column(nullable = false, length = 11)
  private String contactNumber;

  @Column(nullable = false)
  private String password;

  /*
   * add this private String profilePicLocation;
   * after you create a FileStorageService and if you gonna store
   * files like images.
   * add this in the UserDTO first before it here.
   * */
//  private String profilePicLocation;

  private String profilePicUrl;

  private String cloudinaryProfilePicId;

  /*
   * This is part of security authentication / authorization
   * */
  @Column(nullable = false)
  private String type;

//  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//  private List<Lease> leases;
//
//  @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
//  private List<TenantPaymentRecord> paymentRecords;

  @JsonIgnore
  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<Lease> leases;

  @JsonIgnore
  @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<TenantPaymentRecord> paymentRecords;

  public User() {}

  public User(UserDTO dto) {
    if (dto == null) {
      throw new IllegalArgumentException("UserDTO cannot be null");
    }
    this.firstName = dto.getFirstName();
    this.middleName = dto.getMiddleName();
    this.lastName = dto.getLastName();
//    this.profilePicLocation = dto.getProfilePicLocation();
    this.profilePicUrl = dto.getProfilePicUrl();
    this.cloudinaryProfilePicId = dto.getCloudinaryProfilePicId();
    this.address = dto.getAddress();
    this.emailAddress = dto.getEmailAddress();
    this.contactNumber = dto.getContactNumber();
    this.type = dto.getType();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getContactNumber() {
    return contactNumber;
  }

  public void setContactNumber(String contactNumber) {
    this.contactNumber = contactNumber;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

//  public String getProfilePicLocation() {
//    return profilePicLocation;
//  }
//
//  public void setProfilePicLocation(String profilePicLocation) {
//    this.profilePicLocation = profilePicLocation;
//  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getProfilePicUrl() {
    return profilePicUrl;
  }

  public void setProfilePicUrl(String profilePicUrl) {
    this.profilePicUrl = profilePicUrl;
  }

  public String getCloudinaryProfilePicId() {
    return cloudinaryProfilePicId;
  }

  public void setCloudinaryProfilePicId(String cloudinaryProfilePicId) {
    this.cloudinaryProfilePicId = cloudinaryProfilePicId;
  }

}
