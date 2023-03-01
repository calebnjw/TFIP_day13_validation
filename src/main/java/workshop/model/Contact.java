package workshop.model;

import java.io.Serializable;
import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public class Contact implements Serializable {
  private String id;

  @NotNull(message = "Name cannot be empty.")
  @Size(min = 2, max = 64, message = "Name must be between 2 and 64 characters long. ")
  private String name;

  @Email(message = "Email is not valid.")
  private String email;

  @Size(min = 7, message = "Phone number must be at least 8 digits")
  private String phoneNumber;

  @Past(message = "Date of birth cannot be in the future.")
  @NotNull(message = "Date of birth cannot be empty.")
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private LocalDate dateOfBirth;

  @Min(value = 10, message = "Contact must be above 10 years old.")
  @Max(value = 120, message = "Contact must be below 120 years old.")
  private int age;

  public Contact() {
    this.id = this.generateId(8);
  }

  public Contact(String name, String email, String phoneNumber, LocalDate dateOfBirth) {
    this.id = this.generateId(8);
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
  }

  public Contact(String id, String name, String email, String phoneNumber, LocalDate dateOfBirth) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.dateOfBirth = dateOfBirth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    int calculatedAge = 0;
    if (dateOfBirth != null) {
      calculatedAge = Period.between(dateOfBirth, LocalDate.now()).getYears();
    }
    this.dateOfBirth = dateOfBirth;
    this.age = calculatedAge;
  }

  public int getAge() {
    return age;
  }

  private synchronized String generateId(int numOfChar) {
    SecureRandom sr = new SecureRandom();
    StringBuilder sb = new StringBuilder();
    while (sb.length() < numOfChar) {
      sb.append(Integer.toHexString(sr.nextInt()));
    }
    return sb.toString();
  }

}
